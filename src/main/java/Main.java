import config.Config;
import config.Router;

public class Main {

    public static void main(String[] args) {
        Router.getInstance().listen();
        Config.start();

    }

}
