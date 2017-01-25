package config;

import service.connection.TargetConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ismail
 */
public class Config {
    public static void bla(){
        TargetConnection targetConnection = TargetConnection.getInstance();
        targetConnection.setCredentials("oracle","ondora02.hu.nl:8521/cursus02.hu.nl","tosad_2016_2b_team3_target","tosad_2016_2b_team3_target");
    }
}
