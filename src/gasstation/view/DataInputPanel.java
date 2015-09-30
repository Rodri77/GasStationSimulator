/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Rodrigo
 */
public class DataInputPanel extends JPanel {

    private static final Component rigidArea = Box.createRigidArea(new Dimension(0, 40));
    private static final int LABEL_FONT_SIZE = 20;
    private final ImageIcon background;
    private final InputTextField hoursToSimulateField = new InputTextField();
    private final InputTextField gasDispensersField = new InputTextField();
    private final InputTextField gasolineDispensersField = new InputTextField();
    private final InputTextField dieselDispensersField = new InputTextField();

    private final JButton button;
    private final JLabel hoursToSimulateLabel;
    private final JLabel numberOfGasDispensers;
    private final JLabel numberOfGasolineDispensers;
    private final JLabel numberOfDieselDispensers;

    public DataInputPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        EmptyBorder panelBorder = new EmptyBorder(100, 200, 100, 100);
        setBorder(panelBorder);

        button = new JButton("Run");
        button.setMaximumSize(new Dimension(100, 50));

        hoursToSimulateLabel = new JLabel("Hours to simulate");
        numberOfGasDispensers = new JLabel("Number of gas dispensers");
        numberOfGasolineDispensers = new JLabel("Number of gasoline dispensers");
        numberOfDieselDispensers = new JLabel("Number of diesel dispensers");

        numberOfGasDispensers.setPreferredSize(new Dimension(100, 10));
        numberOfGasolineDispensers.setPreferredSize(new Dimension(100, 10));
        numberOfDieselDispensers.setPreferredSize(new Dimension(100, 10));
        hoursToSimulateField.setToolTipText("Enter the number of hours you want to simulate");
        background = new ImageIcon("C:\\Users\\Rodrigo\\Desktop\\GAS_STATION_SIMULATOR\\Codigo fuente\\GasStationSimulator\\GasStation\\resources\\LLuviaAzul.jpg");
//        background = new ImageIcon("resources\\LLuviaAzul.jpg");
        setFontAndForeground();
        addAllComponents();
    }

    @Override
    public void paint(Graphics g) {
        setOpaque(false);
        g.drawImage(background.getImage(), HEIGHT, WIDTH, null);
        super.paint(g);
    }

    private void addAllComponents() {
        add(hoursToSimulateLabel);
        add(rigidArea);
        add(hoursToSimulateField);
        add(rigidArea);
        add(numberOfGasDispensers);
        add(rigidArea);
        add(gasDispensersField);
        add(rigidArea);
        add(numberOfGasolineDispensers);
        add(rigidArea);
        add(gasolineDispensersField);
        add(rigidArea);
        add(numberOfDieselDispensers);
        add(rigidArea);
        add(dieselDispensersField);
        add(rigidArea);
        add(button);
    }

    private void setFontAndForeground() {
        hoursToSimulateLabel.setFont(new Font("Serif", Font.PLAIN, LABEL_FONT_SIZE));
        hoursToSimulateLabel.setForeground(Color.BLACK);
        numberOfGasDispensers.setFont(new Font("Serif", Font.PLAIN, LABEL_FONT_SIZE));
        numberOfGasDispensers.setForeground(Color.BLACK);
        numberOfGasolineDispensers.setFont(new Font("Serif", Font.PLAIN, LABEL_FONT_SIZE));
        numberOfGasolineDispensers.setForeground(Color.BLACK);
        numberOfDieselDispensers.setFont(new Font("Serif", Font.PLAIN, LABEL_FONT_SIZE));
        numberOfDieselDispensers.setForeground(Color.BLACK);
    }

    public JButton getRunButton() {
        return button;
    }

    public int getHoursToSimulateFieldValue() {
        if (hoursToSimulateField.getValue() == null) {
            return 1;
        } else {
            return (int) hoursToSimulateField.getValue();
        }
    }

    public int getGasDispensersFieldValue() {
        if (gasDispensersField.getValue() == null) {
            return 1;
        } else {
            return (int) gasDispensersField.getValue();
        }
    }

    public int getGasolineDispensersFieldValue() {
        if (gasolineDispensersField.getValue() == null) {
            return 1;
        } else {
            return (int) gasolineDispensersField.getValue();
        }
    }

    public int getDieselDispensersFieldValue() {
        if (dieselDispensersField.getValue() == null) {
            return 1;
        } else {
            return (int) dieselDispensersField.getValue();
        }
    }

}
