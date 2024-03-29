package model;
// Generated Feb 1, 2017 1:00:28 PM by Hibernate Tools 4.3.1



/**
 * UserHasProject generated by hbm2java
 */
public class UserHasProjectModel  implements java.io.Serializable {


     private UserHasProjectIdModel id;
     private ProjectModel project;
     private UserModel user;
     private String lvl;

    public UserHasProjectModel() {
    }

    public UserHasProjectModel(UserHasProjectIdModel id, ProjectModel project, UserModel user, String lvl) {
       this.id = id;
       this.project = project;
       this.user = user;
       this.lvl = lvl;
    }
   
    public UserHasProjectIdModel getId() {
        return this.id;
    }
    
    public void setId(UserHasProjectIdModel id) {
        this.id = id;
    }
    public ProjectModel getProject() {
        return this.project;
    }
    
    public void setProject(ProjectModel project) {
        this.project = project;
    }
    public UserModel getUser() {
        return this.user;
    }
    
    public void setUser(UserModel user) {
        this.user = user;
    }
    public String getLvl() {
        return this.lvl;
    }
    
    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    @Override
    public String toString() {
        return "UserHasProjectModel{" + "id=" + id + ", project=" + project + ", user=" + user + ", lvl=" + lvl + '}';
    }

}


