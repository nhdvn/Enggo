package SQLServerConnection;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Object.Vocab;
public class VocabModel {
    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public VocabModel() {
        connection = jdbcController.ConnectionData();
    }

    public List<Vocab> GetVocabList(String topic) throws SQLException {
        List<Vocab> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql_vocab = "select * from Voc_lesson, Vocabulary, topic where " +
                "Voc_lesson.voc_id = Vocabulary.voc_id and Voc_lesson.topic_id = topic.id " +
                "and topic.name = '" + topic + "' ";
        Log.i("xxxx", sql_vocab);
        ResultSet Vocab = statement.executeQuery(sql_vocab);
        while (Vocab.next()) {
            list.add(new Vocab(Vocab.getString("voc"), Vocab.getString("meaning"),
                    Vocab.getString("sentences"), Vocab.getString("listen")));
        }

        connection.close();
        return list;
    }

    public void InsertVocab(Vocab voc, String Topic) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select count(*) cnt from Vocabulary");
        rs.next();
        int numVoc = rs.getInt("cnt");
        String insertVoc = "INSERT Vocabulary\n" +
                "VALUES  ( '" + String.valueOf(numVoc) + "' , -- voc_id - char(15)\n" +
                "          '" + voc.get_meaning() + "' , -- meaning - text\n" +
                "          '" + voc.get_name() + "' , -- voc - char(30)\n" +
                "          '' , -- listen - char(50)\n" +
                "          '' , -- img - char(50)\n" +
                "          '" + voc.get_sentence() + "'  -- sentences - char(200)\n" +
                "        )\n" +
                "INSERT INTO dbo.Voc_lesson SELECT " +
                "'" + String.valueOf(numVoc) + "'" + ",'LE0001', id FROM dbo.topic WHERE topic.name = '" + Topic.trim() + "' ";
        Log.i("xxxxxx", insertVoc);
        statement.execute(insertVoc);
    }
}
