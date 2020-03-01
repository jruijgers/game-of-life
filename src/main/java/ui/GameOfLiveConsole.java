package ui;

import game.LifeGrid;

import java.io.Console;

public class GameOfLiveConsole implements GameOfLive {

    private final LifeGrid grid;
    private final Console console;

    public GameOfLiveConsole(LifeGrid grid) {
        this.grid = grid;
        this.console = System.console();;
    }

    @Override
    public void loop(int waitTimeInMillis) {
        show();

        waitForInput(console);

        while (grid.isAlive()) {
            grid.nextGeneration();
            show();

            waitForInput(console);
        }

        console.printf("Done!");
    }

    @Override
    public void initialize() {

    }

    private void show() {
        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                if (grid.get(x, y)) {
                    console.writer().print("██");
                } else {
                    console.writer().print("  ");
                }
            }
            console.writer().println();
        }
    }

    private void waitForInput(Console console) {
        console.printf("Press enter to continue");
        console.readLine();
    }
}
