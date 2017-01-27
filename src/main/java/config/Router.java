/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import controller.BusinessRuleController;
import controller.TableController;
import spark.Request;
import spark.Response;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;
import static util.JsonUtil.json;

/**
 *
 * @author ismail
 */
public class Router {

    private static final Router instance = new Router();

    private Router() {
    }

    public static Router getInstance() {
        return instance;
    }

    public void listen() {
        
        before((request, response) -> response.type("application/json"));
        get("/:project_id/tables", TableController::getTables, json());
        get("/:project_id/table/:tablename/columns", TableController::getColumnsFromTable, json());
        post("/:project_id/generate/:business_rule_id", BusinessRuleController::generateBusinessRule, json());
    }
}
