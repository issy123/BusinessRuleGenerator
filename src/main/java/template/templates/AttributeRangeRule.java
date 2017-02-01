/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template.templates;

import model.AttributeRangeRuleModel;
import model.BusinessRuleModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import service.ServiceProvider;
import template.Template;
import template.TemplateReader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ismail
 */
public class AttributeRangeRule implements Template {

    private static final Logger logger = LogManager.getLogger(AttributeRangeRule.class.getName());
    @Override
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
        if (ServiceProvider.getInstance()
                .getTargetDatabaseService()
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

    public String parseTemplate(BusinessRuleModel rule, Session session) {
        AttributeRangeRuleModel rangeRule;
        rangeRule = (AttributeRangeRuleModel) session.get(
                AttributeRangeRuleModel.class,
                rule.getId()
        );
        logger.debug("reading file");
        String template = TemplateReader.getInstance().readFile(
                rule.getProject().getDatabaseType().toLowerCase() + "/AttributeRangeRule.sql"
        );
        logger.debug("read file");
        HashMap<String, String> hmap = new HashMap<>();
        /*Adding elements to HashMap*/
        hmap.put("{error_message}", rule.getErrorMessage());
        hmap.put("{table_name}", rule.getTableName());
        hmap.put("{column_name}", rule.getColumnName());
        hmap.put("{range_type}", rangeRule.getRangeType());
        hmap.put("{min}", rangeRule.getMin());
        hmap.put("{max}", rangeRule.getMax());

        String parsedTemplate;
        for (HashMap.Entry<String, String> placeholder : hmap.entrySet()) {
            parsedTemplate = template.replace(placeholder.getKey(), placeholder.getValue());
            template = parsedTemplate;
        }
        return template;
    }

    @Override
    public String code() {
        return "ARNG";
    }
}
