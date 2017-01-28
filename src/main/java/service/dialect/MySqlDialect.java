/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dialect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismail
 */
public class MySqlDialect extends DatabaseDialect{
    Connection connection;

    public final void createConnection(){
        try {
            String url = "jdbc:mysql://" + this.credentials.get("DATABASE_URL") + ":3306/" + this.credentials.get("DATABASE_NAME");
            System.out.println(url);
            System.out.println(this.credentials.get("DATABASE_USERNAME"));
            System.out.println(this.credentials.get("DATABASE_PASSWORD"));
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(url, this.credentials.get("DATABASE_USERNAME"), this.credentials.get("DATABASE_PASSWORD"));
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | NullPointerException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public String getType() {
        return "MYSQL";
    }

    @Override
    public List<String> getTables() {
        DatabaseMetaData md = null;
        try {
            List<String> tables = new ArrayList<String>();
            md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                System.out.println(rs.getString(3));
                tables.add(rs.getString(3));
            }
            return tables;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDialect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Map> getColumns(String tablename) {
        List<Map> list = new ArrayList<Map>();
        try {

            Statement stmt = null;
            String query = "select * from " + tablename + " LIMIT 1;";
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    Map<String, String> tables = new HashMap<String, String>();
                    tables.put("column_name", rsmd.getColumnName(i));
                    tables.put("column_type", rsmd.getColumnTypeName(i));
                    tables.put("nullable", Integer.toString(rsmd.isNullable(i)));
                    list.add(tables);
                }
                return list;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return null;
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
        this.createConnection();
    }
}
