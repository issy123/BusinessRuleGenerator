/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template.templates;

import config.Router;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BusinessRuleModel;
import org.hibernate.Session;
import template.Template;

/**
 *
 * @author ismail
 */
public class AttributeListRule  implements Template{
    @Override
    public boolean parse(BusinessRuleModel rule, Session session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String code(){
        return "ALIS";
    }

}
