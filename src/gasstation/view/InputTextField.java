/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.view;

import java.awt.Dimension;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Rodrigo
 */
public class InputTextField extends JFormattedTextField {

    private static final NumberFormatter FORMATTER = new NumberFormatter();
    private final int value;

    public InputTextField() {
        super(FORMATTER);
        FORMATTER.setAllowsInvalid(false);
        FORMATTER.setMaximum(24);
        setColumns(2);
        setMaximumSize(new Dimension(150, 25));
        value = 0;
    }

}
