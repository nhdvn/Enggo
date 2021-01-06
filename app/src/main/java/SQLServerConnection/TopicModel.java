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

    public TopicModel() {
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    public List<Topic> getTopiclist() throws SQLException {
        List<Topic> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from topic";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String sql_vocab = "select * from Voc_lesson, Vocabulary where " +
                    "Voc_lesson.voc_id = Vocabulary.voc_id and topic_id = " + rs.getString("id");
            ResultSet Vocab = statement.executeQuery(sql_vocab);
            List<Vocab> vocabs = new ArrayList<>();
            while(Vocab.next()){
                vocabs.add(new Vocab(Vocab.getString("voc"),Vocab.getString("meaning"),
                        Vocab.getString("sentences"), Vocab.getString("listen") ));
            }
            list.add(new Topic(rs.getString("name"), vocabs));
        }
        connection.close();// Đóng kết nối
        return list;
    }

}
