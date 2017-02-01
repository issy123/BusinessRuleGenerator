/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template.templates;

import model.BusinessRuleModel;
import model.CompareRuleModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import template.Template;
import template.TemplateReader;

import java.util.HashMap;

/**
 * @author ismail
 */
public class TupleCompareRule extends Template {

    private static final Logger logger = LogManager.getLogger(TupleCompareRule.class.getName());

    @Override
    public String parseTemplate(BusinessRuleModel rule, Session session) {
        CompareRuleModel compareRule;
        compareRule = (CompareRuleModel) session.get(
                CompareRuleModel.class,
                rule.getId()
        );
        String filename = rule.getProject().getDatabaseType().toLowerCase() + "/TupleCompareRule.sql";

        logger.debug("reading file: " + filename);
        String template = TemplateReader.getInstance().readFile(filename);
        logger.debug("read file: " + filename);

        HashMap<String, String> hmap = new HashMap<>();
        /*Adding elements to HashMap*/
        hmap.put("{error_message}", rule.getErrorMessage());
        hmap.put("{table_name}", rule.getTableName());
        hmap.put("{column_name}", rule.getColumnName());
        hmap.put("{id}", Long.toString(rule.getId()));
        hmap.put("{column_type}", rule.getColumnType());
        hmap.put("column_name2}", compareRule.getColumnName2());
        hmap.put("{column_type2}", compareRule.getColumnType2());
        hmap.put("{comparison}", compareRule.getComparison());

        String parsedTemplate;
        for (HashMap.Entry<String, String> placeholder : hmap.entrySet()) {
            parsedTemplate = template.replace(placeholder.getKey(), placeholder.getValue());
            template = parsedTemplate;
        }
        return template;
    }

    @Override
    public String code() {
        return "TCMP";
    }
}
