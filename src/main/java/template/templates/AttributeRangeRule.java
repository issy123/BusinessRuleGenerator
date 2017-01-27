/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template.templates;

import java.util.HashMap;
import model.AttributeRangeRuleModel;
import model.BusinessRuleModel;
import org.hibernate.Session;
import template.TemplateReader;
import template.Template;

/**
 *
 * @author ismail
 */
public class AttributeRangeRule  implements Template{
    public boolean parse(BusinessRuleModel rule, Session session){
        AttributeRangeRuleModel rangeRule = (AttributeRangeRuleModel) session.get(AttributeRangeRuleModel.class, rule.getId());
        System.out.println("reading file");
        String template = TemplateReader.getInstance().readFile("oracle/AttributeRangeRule.sql");
        System.out.println("readed file");
        HashMap<String, String> hmap = new HashMap<String, String>();
        /*Adding elements to HashMap*/
        hmap.put("{error_message}", rule.getErrorMessage());
        hmap.put("{table_name}", rule.getTableName());
        hmap.put("{column_name}", rule.getColumnName());
        hmap.put("{range_type}", rangeRule.getRangeType());
        hmap.put("{min}", rangeRule.getMin());
        hmap.put("{max}", rangeRule.getMax());

        String result = "";
        for(HashMap.Entry<String, String> placeholder : hmap.entrySet())
        {
            result = template.replace(placeholder.getKey(), placeholder.getValue());
            template = result;
        }
        System.out.println(template);
        return true;
    }
    
    public String code(){
        return "ARNG";
    }
}
