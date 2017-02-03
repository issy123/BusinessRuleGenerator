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
        String projectId = req.params(":project_id");
        if(testConnection(projectId)){
            result.put("success", "succeeded");
        }else{
            result.put("success", "failed");
        }

        
        return result;
    }
}
