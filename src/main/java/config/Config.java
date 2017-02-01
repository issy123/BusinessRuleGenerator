package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import template.BusinessRuleParser;
import template.templates.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author ismail
 */
public class Config {
    private static final Logger logger = LogManager.getLogger(Config.class.getName());
    public static void start() {
        logger.debug("Configuring");
        BusinessRuleParser businessRuleParser = BusinessRuleParser.getInstance();
        businessRuleParser.register(new AttributeCompareRule());
        businessRuleParser.register(new AttributeListRule());
        businessRuleParser.register(new AttributeOtherRule());
        businessRuleParser.register(new AttributeRangeRule());
        businessRuleParser.register(new EntityOtherRule());
        businessRuleParser.register(new InterEntityCompareRule());
        businessRuleParser.register(new ModifyRule());
        businessRuleParser.register(new TupleCompareRule());
        businessRuleParser.register(new TupleOtherRule());
        logger.debug("Configuration complete");
    }
}
