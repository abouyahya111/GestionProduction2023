package util;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CheckBoxComboBoxCategorieRenderer implements ListCellRenderer<CheckBoxCatgorieItem> {
  
    public Component getListCellRendererComponent(JList<? extends CheckBoxCatgorieItem> list, CheckBoxCatgorieItem value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
    	
    	if(value!=null)
    	{
    		 JCheckBox checkBox = new JCheckBox(value.getText());
    	        checkBox.setSelected(value.isSelected());
    	        checkBox.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
    	        checkBox.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
    	        return checkBox;
    	}
    	return null;
    }
}