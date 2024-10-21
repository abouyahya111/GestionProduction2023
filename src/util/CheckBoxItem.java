package util;


public class CheckBoxItem {

	   private String conditionOffre;
	    private boolean selected;

	    public CheckBoxItem(String conditionOffre) {
	        this.conditionOffre = conditionOffre;
	    }

	    public String getConditionOffre() {
	        return conditionOffre;
	    }

	    public boolean isSelected() {
	        return selected;
	    }

	    public void setSelected(boolean selected) {
	        this.selected = selected;
	    }
	
}
