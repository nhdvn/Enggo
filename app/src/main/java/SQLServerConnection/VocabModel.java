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

    public VocabModel()
    {
        connection = jdbcController.ConnectionData();
    }

    public List<Vocab> GetVocabList(String topic) throws SQLException
    {
        List<Vocab> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql_vocab = "select * from Voc_lesson, Vocabulary, topic where " +
                "Voc_lesson.voc_id = Vocabulary.voc_id and Voc_lesson.topic_id = topic.id " +
                "and topic.name = '" + topic + "' ";
        Log.i("xxxx", sql_vocab);
        ResultSet Vocab = statement.executeQuery(sql_vocab);
        while (Vocab.next())
        {
            list.add(new Vocab(Vocab.getString("voc"),Vocab.getString("meaning"),
                    Vocab.getString("sentences"), Vocab.getString("listen") ));
        }

        connection.close();
        return list;
    }
}
