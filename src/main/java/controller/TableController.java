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

/**
 * @author ismail
 */
public class TableController extends Controller {

    public static List getTables(Request req, Response res) {
        setConnection(Long.parseLong(req.params(":project_id")));
        List<Map> list = new ArrayList<Map>();

        TargetDatabaseService targetDatabaseService = serviceProvider.getTargetDatabaseService();
        List<String> tables = targetDatabaseService.getTables();
        for (String table : tables) {
            Map<String, String> tableItem = new HashMap<String, String>();
            tableItem.put("key", table);
            tableItem.put("tablevalue", table);
            list.add(tableItem);

        }
        return list;
    }

    public static List getColumnsFromTable(Request req, Response res) {
        setConnection(Long.parseLong(req.params(":project_id")));

        TargetDatabaseService targetDatabaseService = serviceProvider.getTargetDatabaseService();

        String tableName = req.params(":tablename");
        return targetDatabaseService.getColumns(tableName);
    }

}
