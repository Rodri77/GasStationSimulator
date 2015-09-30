/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.model;

import gasstation.events.DispenserEvent;
import gasstation.listeners.DispenserListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public final class ReportTableModel extends DefaultTableModel implements DispenserListener {

    private static final String[] HEADERS = {"Dispenser", "Tipo", "Autos Atendidos", "Combustible Restante"};
    private static final int COLUMN_ONE = 0;
    private static final int COLUMN_TWO = 1;
    private static final int COLUMN_THREE = 2;
    private static final int COLUMN_FOUR = 3;

    private static int rowValue;
    private final List<Dispenser> dispensers;
    private String quantityFormatted;

    public ReportTableModel(List<Dispenser> dispensers) throws InvalidCombustibleTypeException {
        super();
        rowValue = 0;
        this.dispensers = dispensers;
        setNumRows(dispensers.size());
        setColumnIdentifiers(HEADERS);
        fillTable();
    }

    public void fillTable() {
        for (Dispenser dispenser : dispensers) {
            setValueAt(dispenser.getDispenserName(), rowValue, COLUMN_ONE);
            setValueAt(dispenser.getType(), rowValue, COLUMN_TWO);
            setValueAt(dispenser.getCarsAttended(), rowValue, COLUMN_THREE);
            quantityFormatted = String.format("%.2f", dispenser.getCombustibleQuantity());
            setValueAt(quantityFormatted, rowValue, COLUMN_FOUR);
            rowValue++;
        }
    }

    public List<Dispenser> getDispensers() {
        return dispensers;
    }

    public void updateRowContent(Dispenser dispenser) {
        int rowToUpdate = dispenser.getCode() - 1;
        setValueAt(dispenser.getCarsAttended(), rowToUpdate, COLUMN_THREE);
        quantityFormatted = String.format("%.2f", dispenser.getCombustibleQuantity());
        setValueAt(quantityFormatted, rowToUpdate, COLUMN_FOUR);
    }

    @Override
    public void onCarAttended(DispenserEvent event) {
        Dispenser dispenser = (Dispenser) event.getSource();
        updateRowContent(dispenser);
//        fireTableDataChanged();
    }

}
