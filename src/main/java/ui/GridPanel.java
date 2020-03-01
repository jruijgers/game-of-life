package ui;

import game.LifeGrid;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private LifeGrid grid;
    private boolean initialized = false;

    private final int gridSize;

    public GridPanel(int gridSize) {
        this.gridSize = gridSize;

        setBackground(Color.MAGENTA);
    }

    public void initialize() {
        grid = new LifeGrid(getSize().width / gridSize, getSize().height / gridSize);
        grid.initialize();

        initialized = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!initialized) return;

//        int gridSize = Math.min(getSize().width / grid.width, getSize().height / grid.height);
        int blockSize = gridSize > 2 ? Math.max(1, gridSize - 1) : gridSize;

        for (int x = 0; x < grid.width; x++) {
            for (int y = 0; y < grid.height; y++) {
                if (grid.get(x, y)) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x * gridSize, y * gridSize, blockSize, blockSize);
            }
        }
    }

    public LifeGrid getGrid() {
        return grid;
    }
}
