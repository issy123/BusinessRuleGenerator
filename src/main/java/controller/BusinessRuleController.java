package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.util.*;
import model.BusinessRuleModel;
import org.hibernate.Session;
import template.BusinessRuleParser;
import util.HibernateUtil;

/**
 * Created by ismail on 1/25/2017.
 */
public class BusinessRuleController {

    public static Map generateBusinessRule(Request req, Response res) {
        Long businessRuleId = Long.parseLong(req.params(":business_rule_id"));
        
        System.out.println(req.body());
        
        JsonElement jelement = new JsonParser().parse(req.body());
        JsonObject  jobject = jelement.getAsJsonObject();
        JsonArray rules = jobject.getAsJsonArray("rules");
        
        BusinessRuleParser parser = BusinessRuleParser.getInstance();
        
        ArrayList<Boolean> results = new ArrayList();
        for(int i = 0; i < rules.size();i++){
            results.add(parser.parse(rules.get(i).getAsLong()));
        }
        return new HashMap();
    }

}
