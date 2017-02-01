/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dialect;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BusinessRuleModel;

/**
 * @author ismail
 */
public class MsSqlDialect extends DatabaseDialect {

    Connection connection;

    public final boolean createConnection() {
        try {
            String url = "jdbc:sqlserver://" + this.credentials.get("DATABASE_URL") + ":1433;"
                    + "DatabaseName=" + this.credentials.get("DATABASE_NAME") + ";"
                    + "user=" + this.credentials.get("DATABASE_USERNAME") + ";"
                    + "password=" + this.credentials.get("DATABASE_PASSWORD") + ";";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            this.connection = DriverManager.getConnection(url);
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | NullPointerException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    public String getType() {
        return "MSSQL";
    }

    @Override
    public List<String> getTables() {
        DatabaseMetaData md = null;
        try {
            List<String> tables = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES AS data WHERE TABLE_TYPE = 'BASE TABLE'");

            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
            return tables;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDialect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Map> getColumns(String tablename) {
        List<Map> list = new ArrayList<>();
        try {

            Statement stmt = null;
            String query = "select * from " + tablename + " LIMIT 1;";
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    Map<String, String> tables = new HashMap<>();
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
        return list;
    }

    @Override
    public boolean insertBusinessRule(String businessRule) {
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

    @Override
    public boolean testConnection() {
        return this.createConnection();
    }
    
    @Override
    public boolean closeConnection() {
        try {
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OracleDialect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean removeBusinessRule(BusinessRuleModel businessRule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
