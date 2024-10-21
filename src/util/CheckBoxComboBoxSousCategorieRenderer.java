package util;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CheckBoxComboBoxSousCategorieRenderer implements ListCellRenderer<CheckBoxSousCatgorieItem> {
  
    public Component getListCellRendererComponent(JList<? extends CheckBoxSousCatgorieItem> list, CheckBoxSousCatgorieItem value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        JCheckBox checkBox = new JCheckBox(value.getSousCategorie());
        checkBox.setSelected(value.isSelected());
        checkBox.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        checkBox.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
        return checkBox;
    }
}