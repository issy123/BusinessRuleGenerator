/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.connection.TargetConnection;
import spark.Request;
import spark.Response;

/**
 *
 * @author ismail
 */
public class TableController {

    public static Map GetTables(Request req, Response res) {
        String Type = "";

        List<Map> list = new ArrayList<Map>();
        Map<String, List<Map>> test = new HashMap<String, List<Map>>();

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
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        test.put("tabellen", list);
        return test;
    }

    public static Map getColumnsFromTable(Request req, Response res) {
        String tabelnaam = req.params(":tablename");
        String Type = "";

        List<Map> list = new ArrayList<Map>();
        Map<String, List<Map>> test = new HashMap<String, List<Map>>();

        try {

            Connection connection = TargetConnection.getInstance().getConnection();
            Statement stmt = null;
            String query = "select * from " + tabelnaam + " WHERE ROWNUM = 1";
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    Map<String, String> tables = new HashMap<String, String>();
                    tables.put("KOLOMNAAM", rsmd.getColumnName(i));
                    tables.put("KOLOMTYPE", rsmd.getColumnTypeName(i));
                    tables.put("nullable", Integer.toString(rsmd.isNullable(i)));
                    list.add(tables);
                }
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

        test.put("kolommen", list);
        return test;
    }
}
