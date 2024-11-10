package store.view;

import static store.view.ConsoleMessage.WELCOME_MESSAGE;

public class OutputView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
