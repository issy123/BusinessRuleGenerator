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
public class OracleDialect extends DatabaseDialect {

    private static final Logger logger = LogManager.getLogger(OracleDialect.class.getName());

    private Connection connection;

    @Override
    public void onCredentialsReceived() {
        this.createConnection();
    }

    public boolean createConnection() {
        try {
            String url = "jdbc:oracle:thin:@//" + this.credentials.get("DATABASE_URL");
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            this.connection = DriverManager.getConnection(url, this.credentials.get("DATABASE_USERNAME"), this.credentials.get("DATABASE_PASSWORD"));
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | NullPointerException e1) {
            logger.error("Failed to create connection ", e1);
        }
        return false;
    }

    @Override
    public String getType() {
        return "ORACLE";
    }

    @Override
    public List<String> getTables() {
        logger.debug("GETTING TABLES " + credentials);
        List<String> tables = new ArrayList<>();
        try {
            Statement stmt = null;
            String query = "SELECT table_name FROM user_tables";
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    tables.add(rs.getString("table_name"));
                }
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
        return tables;
    }

    @Override
    public List<Map> getColumns(String tablename) {
        List<Map> list = new ArrayList<>();
        try {

            Statement stmt = null;
            String query = "select * from " + tablename + " WHERE ROWNUM = 1";
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
    public ArrayList<String> getBusinessRules() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            logger.error(ex);
        }
        return false;
    }

    @Override
    public boolean removeBusinessRule(BusinessRuleModel businessRule) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM USER_TRIGGERS " +
                "WHERE TABLE_OWNER = '" + credentials.get("DATABASE_NAME") + "' AND" +
                "TRIGGER_NAME = 'BRG_" + businessRule.getBusinessRuleType().getCode() + "_" + businessRule.getTableName() + "_" + businessRule.getId() + "_TRG'" +
                        ""
            );
            while(rs.next()){
                //Retrieve by column name
                String triggerName  = rs.getString("TRIGGER_NAME");

                //Display values
                System.out.print("TRIGGER_NAME: " + triggerName);
                return this.dropTrigger(triggerName);
             }
             rs.close();
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }
    
    public boolean dropTrigger(String triggerName){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "DROP TRIGGER " + triggerName + ";"
            );
            stmt = connection.createStatement();
            rs = stmt.executeQuery(
                    "SELECT * FROM USER_TRIGGERS " +
                    "WHERE TABLE_OWNER = '" + credentials.get("DATABASE_NAME") + "' AND" +
                    "TRIGGER_NAME = '" + triggerName + "'"
            );
            if(rs.first()){
                System.out.println("trigger name still exists");
                return false;
            }
            
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

}
