/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dialect;

import model.BusinessRuleModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ismail
 */
public class MySqlDialect extends DatabaseDialect {

    private static final Logger logger = LogManager.getLogger(MySqlDialect.class.getName());

    Connection connection;

    public final boolean createConnection() {
        try {
            String url = "jdbc:mysql://" + this.credentials.get("DATABASE_URL") + ":3306/" + this.credentials.get("DATABASE_NAME");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(url, this.credentials.get("DATABASE_USERNAME"), this.credentials.get("DATABASE_PASSWORD"));
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | NullPointerException e1) {
            logger.error("Failed to create connection ", e1);
        }
        return false;
    }

    @Override
    public String getType() {
        return "MYSQL";
    }

    @Override
    public List<String> getTables() {
        DatabaseMetaData md;
        try {
            List<String> tables = new ArrayList<>();
            md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                System.out.println(rs.getString(3));
                tables.add(rs.getString(3));
            }
            return tables;
        } catch (SQLException ex) {
            logger.error("Failed to get tables ", ex);
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
                logger.error(e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } catch (SQLException ex) {
           logger.error(ex);
        }
        return list;
    }

    @Override
    public boolean insertBusinessRule(String businessRule) {
        try {
            connection.createStatement().execute(businessRule);
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    @Override
    public List<String> getBusinessRules() {
        return new ArrayList();
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
            logger.error("Failed to close connection", ex);
        }
        return false;
    }
    
    @Override
    public boolean removeBusinessRule(BusinessRuleModel businessRule) {
        return false;
    }
}
