/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template;

import model.BusinessRuleModel;
import org.hibernate.Session;

/**
 *
 * @author ismail
 */
public interface Template {
    public boolean parse(BusinessRuleModel rule, Session session);
    public String code();
}