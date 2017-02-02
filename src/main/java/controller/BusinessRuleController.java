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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import service.TargetDatabaseFactory;
import service.TargetDatabaseService;

/**
 * Created by ismail on 1/25/2017.
 */
public class BusinessRuleController extends Controller {

    private static final Logger logger = LogManager.getLogger(BusinessRuleController.class.getName());

    public static List<Map<String, String>> generateBusinessRules(Request req, Response res) {
        String rules = req.params(":rules");
        logger.debug("Received rules:" + rules);
        List<String> seperatedRules = Arrays.asList(rules.split(","));
        BusinessRuleParser parser = BusinessRuleParser.getInstance();
        List<Map<String, String>> results = new ArrayList();
        seperatedRules.forEach((rule) -> {
            System.out.println("rule:" + rule);
            results.add(parser.parse(Long.parseLong(rule)));
        });
        return results;
    }
    
    public static List<Map<String, String>> generateAllBusinessRules(Request req, Response res) {
        Session openSession = HibernateUtil.getSessionFactory().openSession();
        ProjectModel project = (ProjectModel) openSession.get(ProjectModel.class, req.params(":project_id"));
        Set businessRules = project.getBusinessRules();
        BusinessRuleParser parser = BusinessRuleParser.getInstance();
        List<Map<String, String>> results = new ArrayList();
        for(Object businessRule : businessRules){
            BusinessRuleModel currentBusinessRule = (BusinessRuleModel) businessRule;
            results.add(parser.parse(currentBusinessRule.getId()));
        }
        return results;
    }
    
    public static List<Map<String, String>> removeBusinessRule(Request req, Response res) {

        String rules = req.params(":rules");
        logger.debug("Received rules:" + rules);
        List<String> seperatedRules = Arrays.asList(rules.split(","));
        List<Map<String, String>> results = new ArrayList();
        seperatedRules.forEach((rule) -> {
            System.out.println("rule:" + rule);
            Session openSession = HibernateUtil.getSessionFactory().openSession();
            BusinessRuleModel businessRule = (BusinessRuleModel) openSession.get(BusinessRuleModel.class, Long.parseLong(rule));
            Map<String,String> result = new HashMap();
            TargetDatabaseService targetDatabase = TargetDatabaseFactory.getInstance()
                    .getTargetDatabase(String.valueOf(businessRule.getProject().getId()));
            result.put("rule",String.valueOf(businessRule.getId()));
            result.put("status", 
                    String.valueOf(targetDatabase.removeBusinessRule(businessRule)));
            results.add(result);
        });
        return results;
    }
    
    public static List<Map<String, String>> removeAllBusinessRules(Request req, Response res) {
        Session openSession = HibernateUtil.getSessionFactory().openSession();
        ProjectModel project = (ProjectModel) openSession.get(ProjectModel.class, req.params(":project_id"));
        Set businessRules = project.getBusinessRules();
        List<Map<String, String>> results = new ArrayList();
        for(Object businessRule : businessRules){
            BusinessRuleModel currentBusinessRule = (BusinessRuleModel) businessRule;
            TargetDatabaseService targetDatabase = TargetDatabaseFactory.getInstance()
                    .getTargetDatabase(String.valueOf(currentBusinessRule.getProject().getId()));
            Map<String,String> result = new HashMap();
            result.put(
                    "rule",
                    String.valueOf(currentBusinessRule.getId())
            );
            result.put(
                    "status", 
                    String.valueOf(targetDatabase.removeBusinessRule(currentBusinessRule))
            );
            results.add(result);
        }
        return results;
    }

}
