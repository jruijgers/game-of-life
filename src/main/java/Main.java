import game.LifeGrid;
import ui.GameOfLive;
import ui.GameOfLiveWindow;

public class Main {
    public static void main(String[] args) {
        GameOfLive ui = new GameOfLiveWindow();

        ui.initialize();
        ui.loop();
    }

}
