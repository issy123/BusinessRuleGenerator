/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template;

import model.BusinessRuleModel;
import org.hibernate.Session;

import java.util.Map;

/**
 * @author ismail
 */
public interface Template {

    Map<String, String> parse(BusinessRuleModel rule, Session session);

    String code();
}
