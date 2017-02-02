/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import service.TargetDatabaseFactory;
import service.TargetDatabaseService;

/**
 * @author ismail
 */
public class ConnectionController extends Controller {

    public static Map<String, String> testConnection(Request req, Response res) {
        Map<String, String> result = new HashMap();
        
        TargetDatabaseService targetDatabaseService = TargetDatabaseFactory.getInstance()
                                                        .getTargetDatabase(req.params(":project_id"));
        if(!targetDatabaseService.testConnection()){
            result.put("success", "false");
            return result;
        }
        
        return result;
    }
}
