/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.AttributeListRuleItemModel;
import model.AttributeListRuleModel;
import model.BusinessRuleModel;
import org.hibernate.Session;
import template.Template;
import template.TemplateReader;

/**
 * @author ismail
 */
public class AttributeListRule extends Template {

    @Override
    public String parseTemplate(BusinessRuleModel rule, Session session) {
        AttributeListRuleModel listRule;
        listRule = (AttributeListRuleModel) session.get(
                AttributeListRuleModel.class,
                rule.getId()
        );
        String template = TemplateReader.getInstance().readFile(
                rule.getProject().getDatabaseType().toLowerCase() + "/AttributeOtherBeforeRule.sql"
            );
        HashMap<String, String> hmap = new HashMap<>();
        /*Adding elements to HashMap*/
        hmap.put("{id}", String.valueOf(rule.getId()));
        hmap.put("{error_message}", rule.getErrorMessage().replace("'", "''"));
        hmap.put("{list_type}", listRule.getListType());
        Set listRuleItems = listRule.getAttributeListRuleItems();
        String listItems = "";
        for(Object listRuleItem : listRuleItems){
            AttributeListRuleItemModel currentListRuleItem = (AttributeListRuleItemModel) listRuleItem;
            listItems += "'" + currentListRuleItem.getValue() + "',";
        }
        hmap.put("{list_items}", listItems);

        String parsedTemplate;
        for (HashMap.Entry<String, String> placeholder : hmap.entrySet()) {
            parsedTemplate = template.replace(placeholder.getKey(), placeholder.getValue());
            template = parsedTemplate;
        }
        return template;
    }

    @Override
    public String code() {
        return "ALIS";
    }

}
