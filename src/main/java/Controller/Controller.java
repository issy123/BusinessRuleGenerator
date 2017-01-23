package Controller;

import model.TargetConnection;
import spark.Request;
import spark.Response;

import java.sql.*;
import java.util.*;

/**
 * Created by Administrator on 1/25/2017.
 */
public class Controller {

    public static Map haaltabellenop(Request req, Response res) {
        String Type = "";

        List<Map> list = new ArrayList<Map>();
        Map<String, List<Map>> test = new HashMap<String, List<Map> >();

        try {
            TargetConnection targetConnection = TargetConnection.getInstance();
            Connection connection = targetConnection.getConnection();
            Statement stmt = null;
            String query = "SELECT table_name FROM user_tables";
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Type = rs.getString("table_name");
                    Map<String, String> tables = new HashMap<String, String>();
                    tables.put("TABLENAME", Type);
                    tables.put("KOLOMMEN", Type);
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

    public static Map haalkolommenvantabel(Request req, Response res) {
        String tabelnaam = req.params(":tabelnaam");
        String Type = "";

        List<Map> list = new ArrayList<Map>();
        Map<String, List<Map>> test = new HashMap<String, List<Map> >();

        try {


            TargetConnection targetConnection = TargetConnection.getInstance();
            Connection connection = targetConnection.getConnection();
            Statement stmt = null;
            String query = "DESCRIBE " + tabelnaam;
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Type = rs.getString("table_name");
                    Map<String, String> tables = new HashMap<String, String>();
                    tables.put("KOLOMNAAMWAARDE", Type);
                    tables.put("KOLOMNAAM", Type);
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

        test.put("kolommen", list);
        return test;
    }

}

