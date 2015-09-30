/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.view;

import gasstation.controller.GasStationSimulator;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Rodrigo Arze
 */
public class InitialPanel extends JPanel {

    private final ImageIcon background;
    private final JButton startButton;

    public InitialPanel(Rectangle bounds) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        int width = (int) (GasStationSimulator.SCREEN_SIZE.getWidth() * 2 / 4);
        int height = (int) (GasStationSimulator.SCREEN_SIZE.getHeight() * 2 / 4);
        EmptyBorder buttonBorder = new EmptyBorder(0, 0, 50, 0);
        background = new ImageIcon("C:\\Users\\Rodrigo\\Desktop\\GAS_STATION_SIMULATOR\\Codigo fuente\\GasStationSimulator\\GasStation\\resources\\GasStationSimulator.jpg");
//        background = new ImageIcon("resources\\GasStationSimulator.jpg");
        add(Box.createRigidArea(new Dimension(0, 500)));

        startButton = new JButton("Start!");
        startButton.setMaximumSize(new Dimension(100, 50));
        startButton.setBounds(width, height, 100, 200);
        startButton.setPreferredSize(new Dimension(100, 200));
        startButton.setAlignmentY(Container.BOTTOM_ALIGNMENT);
        startButton.setAlignmentX(Container.CENTER_ALIGNMENT);

        setBorder(buttonBorder);
        add(startButton);
    }

    public JButton getButton() {
        return startButton;
    }

    @Override
    public void paint(Graphics g) {
        setOpaque(false);
        g.drawImage(background.getImage(), HEIGHT, WIDTH, null);
        super.paint(g);
    }

}
