/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template.templates;

import java.util.HashMap;
import model.BusinessRuleModel;
import org.hibernate.Session;
import template.Template;

import java.util.Map;
import model.OtherRuleModel;
import template.TemplateReader;

/**
 * @author ismail
 */
public class EntityOtherRule extends Template {

    @Override
    public String parseTemplate(BusinessRuleModel rule, Session session) {
        OtherRuleModel otherRule;
        otherRule = (OtherRuleModel) session.get(
                OtherRuleModel.class,
                rule.getId()
        );
        String template = TemplateReader.getInstance().readFile(
                rule.getProject().getDatabaseType().toLowerCase() + "/EntityOtherRule.sql"
            );
        HashMap<String, String> hmap = new HashMap<>();
        /*Adding elements to HashMap*/
        hmap.put("{id}", String.valueOf(rule.getId()));
        hmap.put("{error_message}", rule.getErrorMessage());
        hmap.put("{table_name}", rule.getTableName());
        hmap.put("{event}", otherRule.getBeforeOrAfter());
        hmap.put("{action}", otherRule.getInsertUpdateDelete());
        hmap.put("{sql_code}", otherRule.getSqlCode());

        String parsedTemplate;
        for (HashMap.Entry<String, String> placeholder : hmap.entrySet()) {
            parsedTemplate = template.replace(placeholder.getKey(), placeholder.getValue());
            template = parsedTemplate;
        }
        return template;
    }

    @Override
    public String code() {
        return "EOTH";
    }
}
