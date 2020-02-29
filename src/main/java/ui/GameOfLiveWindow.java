package ui;

import game.LifeGrid;

import javax.swing.*;
import java.awt.*;

public class GameOfLiveWindow extends JFrame implements GameOfLive {

    private final GridPanel gridPanel;

    public GameOfLiveWindow() {
        super("Conways Game of Life");

        gridPanel = new GridPanel();
        setContentPane(gridPanel);

        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        Insets inset = getInsets();
        gridPanel.setSize(new Dimension(getSize().width - inset.left - inset.right, getSize().height - inset.top - inset.bottom));
        System.out.println(getSize());
        System.out.println(gridPanel.getSize());
    }

    public void initialize() {
        gridPanel.initialize();
    }

    @Override
    public void loop() {
        while (gridPanel.getGrid().isAlive()) {
            gridPanel.getGrid().nextGeneration();

            repaint();

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
            }
        }
    }
}
