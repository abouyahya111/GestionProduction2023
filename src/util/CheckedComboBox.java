package util;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.accessibility.Accessible;
import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

 

public class CheckedComboBox<E extends CheckableItem> extends JComboBox<E>{  private boolean keepOpen;
private transient ActionListener listener;

protected CheckedComboBox() {
  super();
}

public CheckedComboBox(ComboBoxModel<E> model) {
  super(model);
}
// protected CheckedComboBox(E[] m) {
//   super(m);
// }

@Override public Dimension getPreferredSize() {
  return new Dimension(200, 20);
}

@SuppressWarnings("serial")
@Override public void updateUI() {
  setRenderer(null);
  removeActionListener(listener);
  super.updateUI();
  listener = new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    if ((e.getModifiers() & AWTEvent.MOUSE_EVENT_MASK) != 0) {
	      updateItem(getSelectedIndex());
	      keepOpen = true;
	    }
	  }
};
  setRenderer(new CheckBoxCellRenderer<>());
  addActionListener(listener);
  getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "checkbox-select");
  getActionMap().put("checkbox-select", new AbstractAction() {
    @Override public void actionPerformed(ActionEvent e) {
      Accessible a = getAccessibleContext().getAccessibleChild(0);
      if (a instanceof ComboPopup) {
        updateItem(((ComboPopup) a).getList().getSelectedIndex());
      }
    }
  });
}

protected void updateItem(int index) {
  if (isPopupVisible()) {
    E item = getItemAt(index);
    item.setSelected(!item.isSelected());
    // item.selected ^= true;
    // ComboBoxModel m = getModel();
    // if (m instanceof CheckableComboBoxModel) {
    //   ((CheckableComboBoxModel) m).fireContentsChanged(index);
    // }
    // removeItemAt(index);
    // insertItemAt(item, index);
    setSelectedIndex(-1);
    setSelectedItem(item);
  }
}

@Override public void setPopupVisible(boolean v) {
  if (keepOpen) {
    keepOpen = false;
  } else {
    super.setPopupVisible(v);
  }
}
}
 

 

