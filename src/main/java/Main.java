import ui.GameOfLive;
import ui.GameOfLiveWindow;

public class Main {
    public static void main(String[] args) {
        GameOfLive ui = new GameOfLiveWindow(3);

        ui.initialize();
        ui.loop(150);
    }

}
