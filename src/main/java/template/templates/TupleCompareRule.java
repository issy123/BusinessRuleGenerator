/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template.templates;

import model.BusinessRuleModel;
import org.hibernate.Session;
import template.Template;

/**
 *
 * @author ismail
 */
public class TupleCompareRule  implements Template{
    public boolean parse(BusinessRuleModel rule, Session session){
        return true;
    }
    public String code(){
        return "TCMP";
    }
}
