package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameOfLiveWindow extends JFrame implements GameOfLive, WindowStateListener {

    private final GridPanel gridPanel;
    private volatile boolean maximized = false;
    private volatile boolean initialized = false;

    public GameOfLiveWindow(int cellSize) {
        super("Conways Game of Life");

        addWindowStateListener(this);
        addComponentListener(new FrameComponentListener());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridPanel = new GridPanel(cellSize);
        setContentPane(gridPanel);

        System.out.println("set visible...");
        setVisible(true);

        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public void initialize() {
    }

    @Override
    public void loop(int waitTimeInMillis) {
        while (!initialized) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }

        while (gridPanel.getGrid().isAlive()) {
            gridPanel.getGrid().nextGeneration();

            repaint();

            try {
                Thread.sleep(waitTimeInMillis);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        System.out.println("windowStateChanged(" + e + ")");

        maximized = (e.getNewState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH;
    }

    private class FrameComponentListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            System.out.println("componentResized(" + e + ")");

            Dimension size = getSize();
            Insets inset = getInsets();

            gridPanel.setSize(new Dimension(size.width - inset.left - inset.right, size.height - inset.top - inset.bottom));

            if (maximized) {
                gridPanel.initialize();
                initialized = true;
            }
        }
    }


}
