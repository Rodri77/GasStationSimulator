/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.controller;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import gasstation.model.InvalidCombustibleTypeException;
import gasstation.model.SimulationMain;
import gasstation.model.Surtidor;
import gasstation.view.DataInputPanel;
import gasstation.view.InitialPanel;
import gasstation.view.ReportPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Rodrigo Arze
 */
public class GasStationSimulator extends JFrame implements ActionListener {

    public final CardLayout layout;
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    private final InitialPanel panel = new InitialPanel(this.getBounds());
    private final DataInputPanel inputPane = new DataInputPanel();
    private ReportPane table;
    private SimulationMain simulation;

    public GasStationSimulator() throws InvalidCombustibleTypeException {
        super("Gas Station Simulator");
        layout = new CardLayout();
        super.setLayout(layout);
        int height = SCREEN_SIZE.height * 3 / 4;
        int width = SCREEN_SIZE.width * 3 / 5;

        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.getButton().addActionListener(this);
        inputPane.getRunButton().addActionListener(this);

        pack();
        add(panel, "Button Panel");
        add(inputPane, "Input Panel");

        setLocationRelativeTo(null);
        layout.first(this.getContentPane());
    }

    public static void main(String[] args) throws InvalidCombustibleTypeException {
        GasStationSimulator frame = new GasStationSimulator();
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(inputPane.getRunButton())) {
            try {
                SimulationMain simulation = new SimulationMain(inputPane.getHoursToSimulateFieldValue(),
                        inputPane.getGasDispensersFieldValue(), inputPane.getGasolineDispensersFieldValue(), inputPane.getDieselDispensersFieldValue());
                Surtidor surtidor = simulation.getSurtidor();
                surtidor.setHoursToSimulate(inputPane.getHoursToSimulateFieldValue());
                surtidor.initiateGenerator();
                table = new ReportPane(surtidor.getAllDispensers());
                add(table, "Report table");
                Thread.sleep(1000);
                simulation.load();
                simulation.start();
                table.getTable().updateUI();
            } catch (InvalidCombustibleTypeException ex) {
                Logger.getLogger(GasStationSimulator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(GasStationSimulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        layout.next(this.getContentPane());
    }
}
