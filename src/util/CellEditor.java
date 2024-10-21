package util;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class CellEditor extends JTextField implements TableCellEditor {


public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    //        final JTextField ec = (JTextField) editorComponent;
    //
    //        ec.setText((String) value);
    //
    //        // selectAll, so that whatever the user types replaces what we just put there
    //        ec.selectAll();
    //
    //        SwingUtilities.invokeLater(new Runnable() {
    //
    //            public void run() {
    //                // make the component take the keyboard focus, so the backspace key works
    //                ec.requestFocus();
    //
    //                SwingUtilities.invokeLater(new Runnable() {
    //
    //                    public void run() {
    //                        // at this point the user has typed something into the cell and we
    //                        // want the caret to be AFTER that character, so that the next one
    //                        // comes in on the RHS
    //                        ec.setCaretPosition(ec.getText().length());
    //                    }
    //                });
    //            }
    //        });
    //        return editorComponent;


    throw new UnsupportedOperationException("Not supported yet.");
}

public Object getCellEditorValue() {
    throw new UnsupportedOperationException("Not supported yet.");
}

public boolean isCellEditable(EventObject anEvent) {
    throw new UnsupportedOperationException("Not supported yet.");
}

public boolean shouldSelectCell(EventObject anEvent) {
    throw new UnsupportedOperationException("Not supported yet.");
}

public boolean stopCellEditing() {
    throw new UnsupportedOperationException("Not supported yet.");
}

public void cancelCellEditing() {
    throw new UnsupportedOperationException("Not supported yet.");
}

public void addCellEditorListener(CellEditorListener l) {
    throw new UnsupportedOperationException("Not supported yet.");
}

public void removeCellEditorListener(CellEditorListener l) {
    throw new UnsupportedOperationException("Not supported yet.");
}
}