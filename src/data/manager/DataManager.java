package data.manager;

import java.util.Hashtable;

/**
 * @author Neo-Nevz
 *
 */
public class DataManager {

	private DataManager() {
		// TODO Auto-generated constructor stub
	}

	// Phone Number Sending Info
	public static String noSender() {
		String noToSender = null;
		Hashtable noSender = RMS.load("dataReceiver");
		if (noSender != null) {
			noToSender = noSender.get("noSender").toString();
		} else {
			noToSender = "+6285728103256";
		}
		return noToSender;
	}
}
