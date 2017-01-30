package template;

import model.BusinessRuleModel;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ismail on 1/22/2017.
 */
public class BusinessRuleParser {

    /**
     * The singleton object.
     */
    private static final BusinessRuleParser instance = new BusinessRuleParser();

    /**
     * A list of commands.
     */
    private final ArrayList<Template> templates = new ArrayList<>();

    private BusinessRuleParser() {
        /*
         * Empty
         */
    }

    public static BusinessRuleParser getInstance() {
        return instance;
    }

    public void register(Template template) {
        templates.add(template);
    }

    public Map<String, String> parse(Long businessRuleId) {
        Session openSession = HibernateUtil.getSessionFactory().openSession();
        BusinessRuleModel rule = (BusinessRuleModel) openSession.get(BusinessRuleModel.class, businessRuleId);
        String code = rule.getBusinessRuleType().getCode();
        for (Template template : templates) {
            if (template.code().equals(code)) {
                return template.parse(rule, openSession);
            }
        }
        System.out.println("Unsupported business rule type: " + code);
        Map result = new HashMap();
        result.put("success", "false");
        result.put("message", "Unsupported business rule type: " + code);
        return result;
    }
}
