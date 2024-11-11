package store;

import java.io.IOException;
import store.controller.MainController;
import store.global.config.Config;

public class Application {

    public static void main(String[] args) throws IOException {
        Config config = new Config();
        MainController controller = config.mainController();
        controller.run();
    }
}
