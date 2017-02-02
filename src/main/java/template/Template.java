/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template;

import model.BusinessRuleModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;
import service.TargetDatabaseFactory;

/**
 * @author ismail
 */
public abstract class Template {

    private static final Logger logger = LogManager.getLogger(Template.class.getName());

    public Map<String, String> parse(BusinessRuleModel rule, Session session) {
        String template = parseTemplate(rule, session);
        Map<String, String> result = new HashMap<>();
        if (template.equals("")) {
            result.put("success", "false");
            result.put("message", "Failed to parse business rule");
            logger.warn("Failed to parse business rule: " + rule.getId());
            return result;
        }
        System.out.println(template);
        System.out.println("PROJECTID");
        System.out.println(String.valueOf(rule.getProject().getId()));
        System.out.println("PROJECTID");
        if (TargetDatabaseFactory.getInstance().getTargetDatabase(String.valueOf(rule.getProject().getId()))
                .insertBusinessRule(template)) {
            result.put("success", "true");
            result.put("message", "Business rule created");
            logger.debug("Business rule: " + rule.getId() + " created");
            return result;
        }
        result.put("success", "false");
        result.put("message", "Creating business rule on target database failed");
        logger.warn("Creating business rule: " + rule.getId() + " on target database failed");
        return result;
    }

    public abstract String parseTemplate(BusinessRuleModel rule, Session session);

    public abstract String code();
}
