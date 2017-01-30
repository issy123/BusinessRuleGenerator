package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;
import template.BusinessRuleParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ismail on 1/25/2017.
 */
public class BusinessRuleController extends Controller {

    public static Map generateBusinessRule(Request req, Response res) {
        if(
                !setConnection(Long.parseLong(req.params(":project_id")))
        ){
            return new HashMap();
        }

        System.out.println("Received body:");
        System.out.println(req.body());

        JsonElement jelement = new JsonParser().parse(req.body());
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray rules = jobject.getAsJsonArray("rules");

        BusinessRuleParser parser = BusinessRuleParser.getInstance();
        ArrayList<Map<String, String>> results = new ArrayList();
        for (int i = 0; i < rules.size(); i++) {
            results.add(parser.parse(rules.get(i).getAsLong()));
        }
        return new HashMap();
    }

}
