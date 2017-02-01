/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.BusinessRuleModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.dialect.DatabaseDialect;
import service.dialect.MsSqlDialect;
import service.dialect.MySqlDialect;
import service.dialect.OracleDialect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ismail
 */
public class TargetDatabaseService {
    private static final Logger logger = LogManager.getLogger(TargetDatabaseService.class.getName());

    private final ArrayList<DatabaseDialect> dialects = new ArrayList<>();
    DatabaseDialect dialect;
    Map<String, String> credentials = new HashMap();

    public TargetDatabaseService() {
        this.register(new MySqlDialect());
        this.register(new OracleDialect());
        this.register(new MsSqlDialect());
    }

    public void setCredentials(String type, String url, String databaseName, String username, String password) {

        this.credentials.put("DATABASE_URL", url);
        this.credentials.put("DATABASE_NAME", databaseName);
        this.credentials.put("DATABASE_USERNAME", username);
        this.credentials.put("DATABASE_PASSWORD", password);
        try {
            this.setDialect(type);
        } catch (Exception ex) {
            logger.error(ex);
        }
        this.dialect.setCredentials(this.credentials);
    }

    public void register(DatabaseDialect dialect) {
        dialects.add(dialect);
    }

    private void setDialect(String type) throws Exception {
        for (DatabaseDialect currentDialect : dialects) {
            if (currentDialect.getType().equals(type)) {
                this.dialect = currentDialect;
                return;
            }
        }
        Exception e = new Exception("Dialect '" + type + "' is not supported");
        logger.error(e);
        throw e;
    }

    public List<String> getTables() {
        return this.dialect.getTables();
    }

    public List<Map> getColumns(String tablename) {
        return this.dialect.getColumns(tablename);
    }
    
    public boolean testConnection() {
        return this.dialect.testConnection();
    }
    
    public boolean closeConnection() {
        return this.dialect.closeConnection();
    }

    public boolean insertBusinessRule(String businessRule) {
        return this.dialect.insertBusinessRule(businessRule);
    }

    public boolean removeBusinessRule(BusinessRuleModel businessRule) {
        return this.dialect.removeBusinessRule(businessRule);
    }

    public void getBusinessRules() {
        this.dialect.getBusinessRules();
    }
}
