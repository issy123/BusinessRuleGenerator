package model;
// Generated Jan 24, 2017 3:08:17 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * BusinessRuleType generated by hbm2java
 */
public class BusinessRuleType  implements java.io.Serializable {


     private long id;
     private Category category;
     private String code;
     private String name;
     private String description;
     private Set businessRules = new HashSet(0);

    public BusinessRuleType() {
    }

	
    public BusinessRuleType(long id, Category category, String code, String name, String description) {
        this.id = id;
        this.category = category;
        this.code = code;
        this.name = name;
        this.description = description;
    }
    public BusinessRuleType(long id, Category category, String code, String name, String description, Set businessRules) {
       this.id = id;
       this.category = category;
       this.code = code;
       this.name = name;
       this.description = description;
       this.businessRules = businessRules;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Set getBusinessRules() {
        return this.businessRules;
    }
    
    public void setBusinessRules(Set businessRules) {
        this.businessRules = businessRules;
    }




}

