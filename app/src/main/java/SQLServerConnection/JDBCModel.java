package SQLServerConnection;


import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 8/18/2017.
 */
@SuppressLint("NewApi")
public class JDBCModel {

    public Connection getConnectionOf()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JDBCObject objEntity = new JDBCObject("enggo.c8l5kpzedbpc.ap-southeast-1.rds.amazonaws.com", "admin", "Saxsax11", "elsafake", "1433");
        Connection objConn = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String sConnURL = "jdbc:jtds:sqlserver://" + objEntity.getsServerName() + ":" + objEntity.getsPort() + ";" +
                            "databaseName=" + objEntity.getsDatabase() + ";encrypt=False;sslProtocol=TLSv1.2";

            objConn = DriverManager.getConnection(sConnURL, objEntity.getsUserId(), objEntity.getsPwd());
        }
        catch (SQLException se) {
            Log.e("Error", se.getMessage());
        }
        catch (ClassNotFoundException e) {
            Log.e("Error", e.getMessage());
        }
        catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return objConn;
    }
}


