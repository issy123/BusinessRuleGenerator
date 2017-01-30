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

/**
 * @author ismail
 */
public class ConnectionController extends Controller {

    public static Map<String, String> testConnection(Request req, Response res) {
        Map<String, String> result = new HashMap();
        if(
                !setConnection(Long.parseLong(req.params(":project_id")))
        ){
            result.put("success", "false");
            return result;
        }
        result.put("success", String.valueOf(serviceProvider.getTargetDatabaseService().testConnection()));
        return result;
    }
}
