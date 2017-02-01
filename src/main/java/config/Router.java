/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import controller.BusinessRuleController;
import controller.TableController;
import controller.ConnectionController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static spark.Spark.*;
import static util.JsonUtil.json;

/**
 * @author ismail
 */
public class Router {
    private static final Router instance = new Router();
    private static final Logger logger = LogManager.getLogger(Router.class.getName());

    private Router() {
    }

    public static Router getInstance() {
        return instance;
    }

    public void listen() {
        logger.debug("Listening");
        before((request, response) -> response.type("application/json"));
        get("/:project_id/tables", TableController::getTables, json());
        get("/:project_id/test_connection", ConnectionController::testConnection, json());
        get("/:project_id/table/:tablename/columns", TableController::getColumnsFromTable, json());
        post("/:project_id/generate", BusinessRuleController::generateBusinessRule, json());
        get("/:project_id/generate/all", BusinessRuleController::generateAllBusinessRules, json());
        post("/:project_id/remove", BusinessRuleController::removeBusinessRule, json());
        get("/:project_id/remove/all", BusinessRuleController::removeAllBusinessRules, json());
    }
}
