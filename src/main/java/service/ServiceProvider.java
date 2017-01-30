/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 * @author ismail
 */
public class ServiceProvider {

    public static ServiceProvider instance = new ServiceProvider();
    TargetDatabaseService targetDatabaseService = new TargetDatabaseService();

    private ServiceProvider() {

    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public TargetDatabaseService getTargetDatabaseService() {
        return this.targetDatabaseService;
    }
}
