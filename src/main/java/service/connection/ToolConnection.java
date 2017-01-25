package service.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ismail on 1/22/2017.
 */
public class ToolConnection {

    private static final ToolConnection instance = new ToolConnection();
    private String dbDriver;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private Connection connection;
    private ToolConnection(){}
    public static ToolConnection getInstance(){
        return instance;
    }
    public void setCredentials(String type, String url, String username, String password){
        switch(type){
            case "oracle":
                this.dbDriver = "oracle.jdbc.driver.OracleDriver";
                this.dbUrl = "jdbc:oracle:thin:@//" + url;
                break;
            case "mysql":
                this.dbDriver = "oracle.jdbc.driver.OracleDriver";
                this.dbUrl = url;
                break;
        }
        this.dbUsername = username;
        this.dbPassword = password;
    }
    public final Connection getConnection(){
        try {
            Class.forName(this.dbDriver).newInstance();
            this.connection = DriverManager.getConnection(this.dbUrl, this.dbUsername, this.dbPassword);
            return this.connection;
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }

}
