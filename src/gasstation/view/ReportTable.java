/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.view;

import gasstation.model.Dispenser;
import javax.swing.JTable;
import gasstation.model.ReportTableModel;
import java.awt.Dimension;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public final class ReportTable extends JTable {

    private final List<Dispenser> currentList;
    private final ReportTableModel model;

    public ReportTable(ReportTableModel model) {
        super(model);
        this.model = model;
        currentList = model.getDispensers();
        setMaximumSize(new Dimension(200, 200));
        addAllListeners();
    }

    public void addAllListeners() {
        for (Dispenser dispenser : currentList) {
            dispenser.addDispenserListener(model);
        }
    }

}
