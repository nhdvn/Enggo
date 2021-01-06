package SQLServerConnection;
import SQLServerConnection.JDBCModel;

import java.sql.Connection;

/**
 * Created by Administrator on 8/18/2017.
 */

public class JDBCController {
    JDBCModel JdbcModel = new JDBCModel();

    public Connection ConnectionData() {
        return JdbcModel.getConnectionOf();
    }
}
