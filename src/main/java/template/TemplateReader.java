/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author ismail
 */
public class TemplateReader {

    private static final Logger logger = LogManager.getLogger(TemplateReader.class.getName());

    public static final TemplateReader instance = new TemplateReader();

    private TemplateReader() {
    }

    public static TemplateReader getInstance() {
        return instance;
    }

    public String readFile(String templatePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(classLoader.getResource("template/" + templatePath).toURI())), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException ex) {
            logger.error("Failed ro read: " + templatePath, ex);
        }
        return text;
    }
}
