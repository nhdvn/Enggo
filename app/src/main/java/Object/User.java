package Object;

/**
 * Created by Akinosora on 16/11/2017.
 */

public class User {
    private String _username;
    private String _pass;

    public User(String username, String pass) {
        this._username = username;
        this._pass = pass;
    }

    public String getPass() {
        return this._pass;
    }

    public void setPass(String pass) {
        this._pass = pass;
    }

    public String getName() {
        return _username;
    }

    public void setName(String name) {
        _username = name;
    }
}
