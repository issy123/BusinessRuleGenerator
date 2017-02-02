/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template.templates;

import model.BusinessRuleModel;
import model.OtherRuleModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import template.Template;
import template.TemplateReader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ismail
 */
public class AttributeOtherRule implements Template {
    private static final Logger logger = LogManager.getLogger(AttributeOtherRule.class.getName());
    @Override
    public Map<String, String> parse(BusinessRuleModel rule, Session session) {

        return null;
    }

    public String parseTemplate(BusinessRuleModel rule, Session session) {
        OtherRuleModel otherRule;
        otherRule = (OtherRuleModel) session.get(
                OtherRuleModel.class,
                rule.getId()
        );
        System.out.println("reading file");
        String template;
//        if(rangeRule.get){
            template = TemplateReader.getInstance().readFile(
                rule.getProject().getDatabaseType().toLowerCase() + "/AttributeOtherRule.sql"
            );
            
//        }
        System.out.println("readed file");
        HashMap<String, String> hmap = new HashMap<>();
        /*Adding elements to HashMap*/
        hmap.put("{error_message}", rule.getErrorMessage());
        hmap.put("{table_name}", rule.getTableName());
        hmap.put("{column_name}", rule.getColumnName());
//        hmap.put("{range_type}", otherRule.getRangeType());
//        hmap.put("{min}", otherRule.getMin());
//        hmap.put("{max}", otherRule.getMax());

        String parsedTemplate;
        for (HashMap.Entry<String, String> placeholder : hmap.entrySet()) {
            parsedTemplate = template.replace(placeholder.getKey(), placeholder.getValue());
            template = parsedTemplate;
        }
        return template;
    }
    @Override
    public String code() {
        return "AOTH";
    }
}
