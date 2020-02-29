package game;

import java.io.PrintWriter;
import java.util.Random;

public class LifeGrid {
    public final int width;
    public final int height;

    private boolean[][] grid;

    public LifeGrid(int width, int height) {
        this.width = width;
        this.height = height;

        grid = new boolean[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = false;
            }
        }
    }

    public void initialize() {
        int min = width * height / 4;
        Random random = new Random(1l);

        int numberOfLifeCells = min + random.nextInt(min);

        for (int i = 0; i < numberOfLifeCells; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            grid[x][y] = true;
        }
    }

    public void nextGeneration() {
        boolean[][] newGrid = new boolean[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int neighbours = getAliveNeighbours(x, y);

                if (grid[x][y] && (neighbours == 2 || neighbours == 3)) {
                    newGrid[x][y] = true;
                } else if (!grid[x][y] && neighbours == 3) {
                    newGrid[x][y] = true;
                } else {
                    newGrid[x][y] = false;
                }
            }
        }

        grid = newGrid;
    }

    private int getAliveNeighbours(int x, int y) {
        int aliveNeighbours = 0;
        for (int ax = x - 1; ax < x + 2; ax++) {
            for (int ay = y - 1; ay < y + 2; ay++) {
                if (!(ax == x && ay == y) && isAlive(ax, ay)) {
                    aliveNeighbours++;
                }
            }
        }

        return aliveNeighbours;
    }

    private boolean isAlive(int x, int y) {
        if (x < 0) x += width;
        if (x >= width) x -= width;
        if (y < 0) y += height;
        if (y >= height) y -= height;

        return grid[x][y];
    }

    public boolean isAlive() {
        boolean isAlive = false;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                isAlive |= grid[x][y];
            }
        }
        return isAlive;
    }

    public boolean get(int x, int y) {
        return grid[x][y];
    }
}
