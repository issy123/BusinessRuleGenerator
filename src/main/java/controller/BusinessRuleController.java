package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.BusinessRuleModel;
import model.ProjectModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import spark.Request;
import spark.Response;
import template.BusinessRuleParser;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ismail on 1/25/2017.
 */
public class BusinessRuleController extends Controller {

    private static final Logger logger = LogManager.getLogger(BusinessRuleController.class.getName());

    public static List<Map<String, String>> generateBusinessRule(Request req, Response res) {
        if(
                !setConnection(Long.parseLong(req.params(":project_id")))
        ){
            return new ArrayList();
        }

        logger.debug("Received body:" + req.body());

        JsonElement jelement = new JsonParser().parse(req.body());
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray rules = jobject.getAsJsonArray("rules");

        BusinessRuleParser parser = BusinessRuleParser.getInstance();
        ArrayList<Map<String, String>> results = new ArrayList();
        for (int i = 0; i < rules.size(); i++) {
            results.add(parser.parse(rules.get(i).getAsLong()));
        }
        serviceProvider.getTargetDatabaseService().closeConnection();
        return results;
    }
    
    public static List<Map<String, String>> generateAllBusinessRules(Request req, Response res) {
        if(
                !setConnection(Long.parseLong(req.params(":project_id")))
        ){
            return new ArrayList();
        }
        Session openSession = HibernateUtil.getSessionFactory().openSession();
        ProjectModel project = (ProjectModel) openSession.get(ProjectModel.class, req.params(":project_id"));
        Set businessRules = project.getBusinessRules();
        BusinessRuleParser parser = BusinessRuleParser.getInstance();
        ArrayList<Map<String, String>> results = new ArrayList();
        for(Object businessRule : businessRules){
            BusinessRuleModel currentBusinessRule = (BusinessRuleModel) businessRule;
            results.add(parser.parse(currentBusinessRule.getId()));
        }
        serviceProvider.getTargetDatabaseService().closeConnection();
        return results;
    }
    
    public static List<Map<String, String>> removeBusinessRule(Request req, Response res) {
        if(
                !setConnection(Long.parseLong(req.params(":project_id")))
        ){
            return new ArrayList();
        }

        logger.debug("Received body:" + req.body());

        JsonElement jelement = new JsonParser().parse(req.body());
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray rules = jobject.getAsJsonArray("rules");

        BusinessRuleParser parser = BusinessRuleParser.getInstance();
        ArrayList<Map<String, String>> results = new ArrayList();
        for (int i = 0; i < rules.size(); i++) {
            results.add(parser.parse(rules.get(i).getAsLong()));
        }
        serviceProvider.getTargetDatabaseService().closeConnection();
        return results;
    }
    
    public static List<Map<String, String>> removeAllBusinessRules(Request req, Response res) {
        if(
                !setConnection(Long.parseLong(req.params(":project_id")))
        ){
            return new ArrayList();
        }
        Session openSession = HibernateUtil.getSessionFactory().openSession();
        ProjectModel project = (ProjectModel) openSession.get(ProjectModel.class, req.params(":project_id"));
        Set businessRules = project.getBusinessRules();
        BusinessRuleParser parser = BusinessRuleParser.getInstance();
        ArrayList<Map<String, String>> results = new ArrayList();
        for(Object businessRule : businessRules){
            BusinessRuleModel currentBusinessRule = (BusinessRuleModel) businessRule;
            results.add(parser.parse(currentBusinessRule.getId()));
        }
        
        serviceProvider.getTargetDatabaseService().closeConnection();
        
        return results;
    }

}
