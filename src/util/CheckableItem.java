package util;

public class CheckableItem { 
	public final String text;
private boolean selected;

public CheckableItem(String text, boolean selected) {
  this.text = text;
  this.selected = selected;
}

public boolean isSelected() {
  return selected;
}

public void setSelected(boolean selected) {
  this.selected = selected;
}

@Override public String toString() {
  return text;
}}
