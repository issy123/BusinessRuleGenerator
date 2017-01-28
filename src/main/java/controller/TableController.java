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
import service.TargetConnection;
import service.TargetDatabaseService;
import spark.Request;
import spark.Response;

/**
 *
 * @author ismail
 */
public class TableController extends Controller{

    public static List getTables(Request req, Response res) {
        setConnection(Long.parseLong(req.params(":project_id")));
        List<Map> list = new ArrayList<Map>();
        
        TargetDatabaseService targetDatabaseService = serviceProvider.getTargetDatabaseService();
        List<String> tables = targetDatabaseService.getTables();
        for(String table : tables){
            Map<String, String> tableItem = new HashMap<String, String>();
            tableItem.put("key", table);
            tableItem.put("tablevalue", table);
            list.add(tableItem);
            
        }
        return list;
    }

    public static Map getColumnsFromTable(Request req, Response res) {
        setConnection(Long.parseLong(req.params(":project_id")));
        String tableName = req.params(":tablename");

        Map<String, List<Map>> test = new HashMap<String, List<Map>>();
        TargetDatabaseService targetDatabaseService = serviceProvider.getTargetDatabaseService();
        

        test.put("kolommen", targetDatabaseService.getColumns(tableName));
        return test;
    }

}
