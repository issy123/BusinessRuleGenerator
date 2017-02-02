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
import model.ProjectModel;
import org.hibernate.Session;
import service.TargetDatabaseFactory;
import service.TargetDatabaseService;
import util.HibernateUtil;

/**
 * @author ismail
 */
public class ConnectionController extends Controller {

    public static Map<String, String> testConnection(Request req, Response res) {
        Map<String, String> result = new HashMap();
        String projectId = req.params(":project_id");
        System.out.println("testing project: " + projectId);
        if(testConnection(projectId)){
            result.put("success", "succeeded");
        }
        result.put("success", "failed");

        
        return result;
    }
}
