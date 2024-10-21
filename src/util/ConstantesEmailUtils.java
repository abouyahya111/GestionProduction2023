package util;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

public class ConstantesEmailUtils {

	private static  String mailSessionUser = "systeme.production2016@gmail.com";
	private static  String mailSessionPass = "!kal!0687*";
	private static final String administratorGroup = "ADMINISTRATOR_GROUP";
	private static final String userGroup = "USER_GROUP";
	private static final String administratorPrivilege = "ADMINISTRATOR";
	private static final String userPrivilege = "USER";
	private static int MAX_NBR_ATTEMPT = 3;
	private static int CURRENT_NBR_ATTEMPT = 0;
	private static boolean enableSearchOnEntry=true;

	public static void setLabelColor(JLabel jLabel, Color color) {
		jLabel.setForeground(color);
	}

	public static int getMAX_NBR_ATTEMPT() {
		return MAX_NBR_ATTEMPT;
	}

	public static void setMAX_NBR_ATTEMPT(int mAX_NBR_ATTEMPT) {
		MAX_NBR_ATTEMPT = mAX_NBR_ATTEMPT;
	}

	public static int getCURRENT_NBR_ATTEMPT() {
		return CURRENT_NBR_ATTEMPT;
	}

	public static void setCURRENT_NBR_ATTEMPT(int cURRENT_NBR_ATTEMPT) {
		CURRENT_NBR_ATTEMPT = cURRENT_NBR_ATTEMPT;
	}

	public static String getAdministratorgroup() {
		return administratorGroup;
	}

	public static String getUsergroup() {
		return userGroup;
	}

	public static String getAdministratorprivilege() {
		return administratorPrivilege;
	}

	public static String getUserprivilege() {
		return userPrivilege;
	}


	public static boolean getIsEnableSearchOnEntry() {
		return enableSearchOnEntry;
	}

	public static void setIsEnableSearchOnEntry(boolean enableSearchOnEntry) {
		ConstantesEmailUtils.enableSearchOnEntry = enableSearchOnEntry;
	}

	public static String getMailSessionUser() {
		return mailSessionUser;
	}

	public static void setMailSessionUser(String mailSessionUser) {
		ConstantesEmailUtils.mailSessionUser = mailSessionUser;
	}

	public static String getMailSessionPass() {
		return mailSessionPass;
	}

	public static void setMailSessionPass(String mailSessionPass) {
		ConstantesEmailUtils.mailSessionPass = mailSessionPass;
	}

	public static String convertToString(Boolean field) {

		if (field instanceof Boolean) {
			if (field)
				return "Oui";
			else
				return "Non";
		} else
			return "Non Renseigné";

	}
	
	public static Matcher regexToCheck(String pattern, String field) {
		Pattern fullNamePattern = Pattern.compile(pattern);
		Matcher regexMatcher = fullNamePattern.matcher(field);

		return regexMatcher;

	}

}
