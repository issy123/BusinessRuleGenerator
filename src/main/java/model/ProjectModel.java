package model;
// Generated Feb 1, 2017 1:00:28 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Project generated by hbm2java
 */
public class ProjectModel  implements java.io.Serializable {


     private long id;
     private String databaseUrl;
     private String databaseUsername;
     private String databasePassword;
     private String databaseName;
     private String databaseType;
     private String name;
     private Integer databasePort;
     private Set userHasProjects = new HashSet(0);
     private Set businessRules = new HashSet(0);

    public ProjectModel() {
    }

	
    public ProjectModel(long id, String databaseUrl, String databaseUsername, String databasePassword, String databaseName, String databaseType, String name) {
        this.id = id;
        this.databaseUrl = databaseUrl;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
        this.databaseName = databaseName;
        this.databaseType = databaseType;
        this.name = name;
    }
    public ProjectModel(long id, String databaseUrl, String databaseUsername, String databasePassword, String databaseName, String databaseType, String name, Integer databasePort, Set userHasProjects, Set businessRules) {
       this.id = id;
       this.databaseUrl = databaseUrl;
       this.databaseUsername = databaseUsername;
       this.databasePassword = databasePassword;
       this.databaseName = databaseName;
       this.databaseType = databaseType;
       this.name = name;
       this.databasePort = databasePort;
       this.userHasProjects = userHasProjects;
       this.businessRules = businessRules;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getDatabaseUrl() {
        return this.databaseUrl;
    }
    
    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }
    public String getDatabaseUsername() {
        return this.databaseUsername;
    }
    
    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }
    public String getDatabasePassword() {
        return this.databasePassword;
    }
    
    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }
    public String getDatabaseName() {
        return this.databaseName;
    }
    
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    public String getDatabaseType() {
        return this.databaseType;
    }
    
    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Integer getDatabasePort() {
        return this.databasePort;
    }
    
    public void setDatabasePort(Integer databasePort) {
        this.databasePort = databasePort;
    }
    public Set getUserHasProjects() {
        return this.userHasProjects;
    }
    
    public void setUserHasProjects(Set userHasProjects) {
        this.userHasProjects = userHasProjects;
    }
    public Set getBusinessRules() {
        return this.businessRules;
    }
    
    public void setBusinessRules(Set businessRules) {
        this.businessRules = businessRules;
    }

    @Override
    public String toString() {
        return "ProjectModel{" + "id=" + id + ", databaseUrl=" + databaseUrl + ", databaseUsername=" + databaseUsername + ", databasePassword=" + databasePassword + ", databaseName=" + databaseName + ", databaseType=" + databaseType + ", name=" + name + ", databasePort=" + databasePort + '}';
    }


}


