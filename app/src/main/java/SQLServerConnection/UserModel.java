package SQLServerConnection;

import Object.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akinosora on 16/11/2017.
 */

public class UserModel {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public UserModel()
    {
        connection = jdbcController.ConnectionData();
    }

    public boolean checkUser(User user) throws SQLException
    {
        String sql = "select * from usr where username = '" + user.getName()+ "'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (!rs.next()) {
            connection.close();
            return false;
        }

        boolean x =  rs.getString("pass").trim().equals(user.getPass());
        connection.close();
        return x;
    }

    public List<User> GetUserList() throws SQLException
    {
        List<User> list = new ArrayList<>();
        Statement statement = connection.createStatement(); // Tạo đối tượng Statement.
        String sql = "select * from usr";
        ResultSet rs = statement.executeQuery(sql); // Thực thi câu lệnh SQL. Mọi kết quả trả về sẽ được lưu trong ResultSet

        while (rs.next()) {
            list.add(new User(rs.getString("username"), rs.getString("pass"))); // Đọc dữ liệu từ ResultSet
        }
        connection.close();
        return list;
    }

    public boolean Insert(User objUser) throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select count(*) num from usr");
        rs.next();
        int numUser = rs.getInt("num");
        String sql = "insert into usr(id ,username, pass) values('"+ String.valueOf(numUser) + "', '" +
                        objUser.getName() + "', '" + objUser.getPass()+ "')";

        if (statement.executeUpdate(sql) > 0)
        {
            connection.close();
            return true;
        }
        else connection.close();

        return false;
    }

    public boolean Update(User objUser) throws SQLException
    {
        Statement statement = connection.createStatement();
        String sql = "Update usr set username = " + objUser.getName() + " where pass = " + objUser.getPass();

        if (statement.executeUpdate(sql) > 0)
        {
            connection.close();
            return true;
        }
        else connection.close();

        return false;
    }

    public boolean Delete(User objUser) throws SQLException
    {
        Statement statement = connection.createStatement();
        String sql = "delete from usr where pass = " + objUser.getPass();

        if (statement.executeUpdate(sql) > 0)
        {
            connection.close();
            return true;
        }
        else connection.close();

        return false;
    }
}
