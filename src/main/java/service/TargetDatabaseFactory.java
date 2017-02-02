/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.HashMap;
import java.util.Map;
import model.ProjectModel;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author ismail
 */
public class TargetDatabaseFactory {
    
    private static TargetDatabaseFactory instance = new TargetDatabaseFactory();
    
    private TargetDatabaseFactory(){}
    public static TargetDatabaseFactory getInstance(){
        return instance;
    }
    public Map<String, TargetDatabaseService> targetDatabases = new HashMap();
    public TargetDatabaseService getTargetDatabase(String projectId){
        if(targetDatabases.get(projectId) != null){
            return targetDatabases.get(projectId);
        }
        TargetDatabaseService targetDatabase = new TargetDatabaseService();
        Session openSession = HibernateUtil.getSessionFactory().openSession();
        ProjectModel project = (ProjectModel) openSession.get(ProjectModel.class, Long.parseLong(projectId));
        targetDatabase.setCredentials(
                project.getDatabaseType(),
                project.getDatabaseUrl(),
                project.getDatabaseName(),
                project.getDatabaseUsername(),
                project.getDatabasePassword()
        );
        targetDatabases.put(projectId, targetDatabase);
        return targetDatabases.get(projectId);
    }
}
