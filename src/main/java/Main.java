import config.Config;
import config.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if(logger.isDebugEnabled()){
            logger.debug("Debug mode is enabled");
        }
        logger.info("Application is launching");

        Config.start();
        Router.getInstance().listen();

    }

}
