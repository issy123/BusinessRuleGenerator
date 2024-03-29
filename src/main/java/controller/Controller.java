/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ProjectModel;
import org.hibernate.Session;
import service.TargetDatabaseFactory;
import service.TargetDatabaseService;
import util.HibernateUtil;

/**
 * @author ismail
 */
public abstract class Controller {
    public static boolean testConnection(String projectId){
        if(!projectId.matches("[0-9]+")){
            return false;
        }
        Session openSession = HibernateUtil.getSessionFactory().openSession();
        ProjectModel project = (ProjectModel) openSession.get(ProjectModel.class, Long.parseLong(projectId));
        if(project == null){
            return false;
        }
                TargetDatabaseService targetDatabaseService = TargetDatabaseFactory.getInstance()
                                                        .getTargetDatabase(projectId);
        if(targetDatabaseService.testConnection()){
            return true;
        }
        return false;
    }
}
