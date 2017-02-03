package template;

import model.BusinessRuleModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ismail on 1/22/2017.
 */
public class BusinessRuleParser {

    private static final Logger logger = LogManager.getLogger(BusinessRuleParser.class.getName());

    /**
     * The singleton object.
     */
    private static final BusinessRuleParser instance = new BusinessRuleParser();

    /**
     * A list of commands.
     */
    private final List<Template> templates = new ArrayList<>();

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
        System.out.println(businessRuleId);
        for (Template template : templates) {
            if (template.code().equals(code)) {
                System.out.println("CODE: " + code);
                return template.parse(rule, openSession);
            }
        }
        logger.warn("Unsupported business rule type: " + code);

        Map<String, String> result = new HashMap<>();
        result.put("success", "false");
        result.put("message", "Unsupported business rule type: " + code);
        return result;
    }
}
