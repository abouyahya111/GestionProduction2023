package util;


public class CheckBoxSousCatgorieItem {

	   private String sousCategorie;
	    private boolean selected;

	    public CheckBoxSousCatgorieItem(String sousCategorie) {
	        this.sousCategorie = sousCategorie;
	    }

	    public String getSousCategorie() {
	        return sousCategorie;
	    }

	    public boolean isSelected() {
	        return selected;
	    }

	    public void setSelected(boolean selected) {
	        this.selected = selected;
	    }
	
}
