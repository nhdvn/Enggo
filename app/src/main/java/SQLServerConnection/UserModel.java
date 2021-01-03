package SQLServerConnection;
import android.util.Log;

import androidx.annotation.Nullable;

import Object.User;

import SQLServerConnection.JDBCController;

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

    public UserModel() {
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    public boolean checkUser(User user) throws SQLException {
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
    public List<User> getuserlist() throws SQLException {
        List<User> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from usr";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new User(rs.getString("username"), rs.getString("pass")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }

    public boolean Insert(User objUser) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        ResultSet rs = statement.executeQuery("select count(*) num from usr");
        rs.next();
        int numUser = rs.getInt("num");
        String sql = "insert into usr(id ,username, pass) values('"+ String.valueOf(numUser) + "', '" +
                        objUser.getName() + "', '" + objUser.getPass()+ "')";

        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else {
            connection.close();
            return false;
        }
    }

    public boolean Update(User objUser) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "Update usr set username = " + objUser.getName() + " where pass = " + objUser.getPass();
        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else
            connection.close();
        return false;
    }

    public boolean Delete(User objUser) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "delete from usr where pass = " + objUser.getPass();
        if (statement.executeUpdate(sql) > 0){
            connection.close();
            return true;
        }

        else
            connection.close();
        return false;
    }

}
