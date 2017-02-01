/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ProjectModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import service.ServiceProvider;
import util.HibernateUtil;

/**
 * @author ismail
 */
public abstract class Controller {

    private static final Logger logger = LogManager.getLogger(Controller.class.getName());

    public static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    static boolean setConnection(long projectId) {
        Session openSession = HibernateUtil.getSessionFactory().openSession();
        ProjectModel project = (ProjectModel) openSession.get(ProjectModel.class, projectId);
        if(project == null){
            return false;
        }
        logger.debug(project);
        serviceProvider.getTargetDatabaseService().setCredentials(
                project.getDatabaseType(),
                project.getDatabaseUrl(),
                project.getDatabaseName(),
                project.getDatabaseUsername(),
                project.getDatabasePassword()
        );
        return true;
    }
}
