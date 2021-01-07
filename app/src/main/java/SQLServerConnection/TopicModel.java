package SQLServerConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Object.Topic;
import Object.Vocab;

public class TopicModel {
    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public TopicModel()
    {
        connection = jdbcController.ConnectionData();
    }

    public List<String> GetTopicList() throws SQLException
    {
        List<String> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from topic";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next())
        {
            String sql_vocab = "select * from Voc_lesson, Vocabulary where " +
                    "Voc_lesson.voc_id = Vocabulary.voc_id and topic_id = " + rs.getString("id");
            list.add(rs.getString("name"));
        }

        connection.close();
        return list;
    }
}
