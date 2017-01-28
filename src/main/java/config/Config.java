package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import service.TargetConnection;
import template.BusinessRuleParser;
import template.templates.*;

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
    public static void start(){
        String url = "jdbc:mysql://h2652979.stratoserver.net:3306/tosad";
        String username = "root";
        String password = "test123";

        System.out.println("Connecting database...");
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        TargetConnection targetConnection = TargetConnection.getInstance();
        targetConnection.setCredentials("oracle","ondora02.hu.nl:8521/cursus02.hu.nl","tosad_2016_2b_team3_target","tosad_2016_2b_team3_target");
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
    }
}
