/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dialect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.BusinessRuleModel;

/**
 * @author ismail
 */
public abstract class DatabaseDialect {

    Map<String, String> credentials = new HashMap<>();

    public void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
        this.onCredentialsReceived();
    }

    public abstract boolean createConnection();
    
    public abstract boolean testConnection();
    
    
    public abstract boolean closeConnection();
    
    public abstract void onCredentialsReceived();

    public abstract String getType();

    public abstract List<String> getTables();

    public abstract List<Map> getColumns(String tablename);

    public abstract boolean insertBusinessRule(String businessRule);

    public abstract boolean removeBusinessRule(BusinessRuleModel businessRule);

    public abstract List<String> getBusinessRules();
}
