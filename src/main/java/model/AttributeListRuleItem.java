package model;
// Generated Jan 24, 2017 3:08:17 PM by Hibernate Tools 4.3.1



/**
 * AttributeListRuleItem generated by hbm2java
 */
public class AttributeListRuleItem  implements java.io.Serializable {


     private long id;
     private AttributeListRule attributeListRule;
     private String value;

    public AttributeListRuleItem() {
    }

    public AttributeListRuleItem(long id, AttributeListRule attributeListRule, String value) {
       this.id = id;
       this.attributeListRule = attributeListRule;
       this.value = value;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public AttributeListRule getAttributeListRule() {
        return this.attributeListRule;
    }
    
    public void setAttributeListRule(AttributeListRule attributeListRule) {
        this.attributeListRule = attributeListRule;
    }
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }




}

