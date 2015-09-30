/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.view;

import gasstation.listeners.DispenserListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import gasstation.model.Dispenser;
import gasstation.model.InvalidCombustibleTypeException;
import gasstation.model.ReportTableModel;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.PAGE_AXIS;
import javax.swing.JScrollBar;

/**
 *
 * @author Rodrigo
 */
public final class ReportPane extends JPanel {

    private final List<DispenserListener> listeners;
    private final ImageIcon background;
    private final JLabel label;
    private final ReportTable table;

    public ReportPane(List<Dispenser> dispensers) throws InvalidCombustibleTypeException {
        super();
        setLayout(new BoxLayout(this, PAGE_AXIS));
        listeners = new ArrayList<>();
        EmptyBorder panelBorder = new EmptyBorder(50, 50, 50, 50);
        setBorder(panelBorder);
        background = new ImageIcon("C:\\Users\\Rodrigo\\Desktop\\GAS_STATION_SIMULATOR\\Codigo fuente\\GasStationSimulator\\GasStation\\resources\\LLuviaAzul.jpg");
//        background = new ImageIcon("resources\\LLuviaAzul.jpg");
        ReportTableModel model = new ReportTableModel(dispensers);
        table = new ReportTable(model);
        table.setMaximumSize(new Dimension(300, 300));
        table.setEnabled(false);
        table.setToolTipText("Report of the Dispensers activity");
        table.add(new JScrollBar());

        label = new JLabel("Report of cars attended per dispenser");
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setForeground(Color.BLACK);

        JScrollPane tableScroll = new JScrollPane(table);

        add(label);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(tableScroll);
        add(Box.createRigidArea(new Dimension(0, 220)));
    }

    public ReportTable getTable() {
        return table;
    }

    @Override
    public void paint(Graphics g) {
        setOpaque(false);
        g.drawImage(background.getImage(), HEIGHT, WIDTH, null);
        super.paint(g);
    }
}
