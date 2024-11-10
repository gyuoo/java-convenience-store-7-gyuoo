package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private OutputView outputView;

    public String readLine() {
        return Console.readLine();
    }

    public String askForProductsToPurchase() {
        outputView.printEnterProductsToPurchase();
        return readLine();
    }
}
