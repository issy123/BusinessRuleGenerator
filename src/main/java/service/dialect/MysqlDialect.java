/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dialect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ismail
 */
public class MysqlDialect extends DatabaseDialect{
    Connection connection;
    public MysqlDialect() {
//        createConnection();
    }

    public final Connection getConnection(){
        try {
            String url = "jdbc:oracle:thin:@//" + this.credentials.get("DATABASE_URL");
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            this.connection = DriverManager.getConnection(url, this.credentials.get("DATABASE_USERNAME"), this.credentials.get("DATABASE_PASSWORD"));
            return this.connection;
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | NullPointerException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public String getType() {
        return "MYSQL";
    }

    @Override
    public List<String> getTables() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Map> getColumns(String tablename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertBusinessRule(String businessRule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getBusinessRules() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCredentialsReceived() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
