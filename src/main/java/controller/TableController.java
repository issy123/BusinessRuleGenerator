/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.TargetDatabaseService;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.TargetDatabaseFactory;

/**
 * @author ismail
 */
public class TableController extends Controller {

    public static List getTables(Request req, Response res) {
        List<Map> list = new ArrayList<>();
        if(!testConnection(req.params(":project_id"))){
            return list;
        }
        TargetDatabaseService targetDatabaseService = TargetDatabaseFactory.getInstance()
                                                        .getTargetDatabase(req.params(":project_id"));
        List<String> tables = targetDatabaseService.getTables();
        for (String table : tables) {
            Map<String, String> tableItem = new HashMap<>();
            tableItem.put("key", table);
            tableItem.put("tablevalue", table);
            list.add(tableItem);

        }
        return list;
    }

    public static List getColumnsFromTable(Request req, Response res) {
        List<Map> columns = new ArrayList();
        if(!testConnection(req.params(":project_id"))){
            return columns;
        }
        TargetDatabaseService targetDatabaseService = TargetDatabaseFactory.getInstance()
                                                        .getTargetDatabase(req.params(":project_id"));

        String tableName = req.params(":tablename");
        columns = targetDatabaseService.getColumns(tableName);
        return columns;
    }
    public static Map getColumnFromTable(Request req, Response res) {

        TargetDatabaseService targetDatabaseService = TargetDatabaseFactory.getInstance()
                                                        .getTargetDatabase(req.params(":project_id"));

        String tableName = req.params(":tablename");
        List<Map> columns = targetDatabaseService.getColumns(tableName);
        for(Map column : columns){
            if (column.get("column_name").equals(req.params(":column_name"))){
                return column;
            }
            
        }
        return new HashMap();
    }

}
