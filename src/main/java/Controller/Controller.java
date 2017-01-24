package Controller;

import com.mscharhag.sparkdemo.TargetConnection;
import spark.Request;
import spark.Response;

import java.sql.*;
import java.util.*;

/**
 * Created by Administrator on 1/25/2017.
 */
public class Controller {

    public static Map GetTables(Request req, Response res) {
        String Type = "";

        List<Map> list = new ArrayList<Map>();
        Map<String, List<Map>> test = new HashMap<String, List<Map> >();

        try {
            Connection connection = TargetConnection.getInstance().getConnection();
            Statement stmt = null;
            String query = "SELECT table_name FROM user_tables";
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Type = rs.getString("table_name");
                    Map<String, String> tables = new HashMap<String, String>();
                    tables.put("key", Type);
                    tables.put("tablevalue", Type);
                    list.add(tables);
                }
            } catch (SQLException e ) {
                e.getErrorCode();
            } finally {
                if (stmt != null) { stmt.close(); }
            }
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }

        test.put("tabellen", list);
        return test;
    }

    public static Map getColumnsFromTable(Request req, Response res) {
        String tabelnaam = req.params(":tablename");
        String Type = "";

        List<Map> list = new ArrayList<Map>();
        Map<String, List<Map>> test = new HashMap<String, List<Map> >();

        try {

            Connection connection = TargetConnection.getInstance().getConnection();
            Statement stmt = null;
            String query = "select * from " + tabelnaam + " WHERE ROWNUM = 1";
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                for(int i = 1; i <= rsmd.getColumnCount();i++){
                    Map<String, String> tables = new HashMap<String, String>();
                    tables.put("KOLOMNAAM", rsmd.getColumnName(i));
                    tables.put("KOLOMTYPE", rsmd.getColumnTypeName(i));
                    tables.put("nullable", Integer.toString(rsmd.isNullable(i)));
                    list.add(tables);
                }
            } catch (SQLException e ) {
                System.out.println(e.getMessage());
            } finally {
                if (stmt != null) { stmt.close(); }
            }
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }

        test.put("kolommen", list);
        return test;
    }
    public static Map generateBusinessRule(Request req, Response res) {
        return new HashMap();
    }

}

