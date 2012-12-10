package gps.manager;

import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.ProximityListener;
import javax.microedition.location.QualifiedCoordinates;

import data.manager.DataManager;

import sms.manager.SMSManagerReceiver;

/**
 * @author Neo-Nevz
 * 
 */
public class GPSManager implements LocationListener, ProximityListener {

	public static Criteria criteria;
	public static LocationProvider provider;
	public static Location location;
	public static QualifiedCoordinates coordinates;

	// Data Location
	public static double longitude;
	public static double latitude;
	public static double altitude;
	public static double horizontalacc;
	public static double verticalacc;

	// message parameter
	private static String phoneNumber = DataManager.noSender();
	private static String port = ":11108";

	public GPSManager() {

	}

	public static void datalocation() {

		new Thread() {
			public void run() {
				try {
					criteria = new Criteria();
					provider = LocationProvider.getInstance(criteria);
					location = provider.getLocation(20);
					coordinates = location.getQualifiedCoordinates();

					// get position
					latitude = /* 2.111100; */coordinates.getLatitude();
					longitude = /* 2.34544; */coordinates.getLongitude();

					// Parsing To double
					String slatitude = String.valueOf(latitude);
					String slongitude = String.valueOf(longitude);

					// Data Location
					final String dataLocation = slatitude + "#" + slongitude + "#";

					// Send Data Map Location
					if (latitude != 0.0 || longitude != 0.0) {
						new Thread() {
							public void run() {
								SMSManagerReceiver.sender(phoneNumber, dataLocation, port);
							};
						}.start();
					}

				} catch (LocationException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	public void locationUpdated(LocationProvider arg0, Location arg1) {
		// TODO Auto-generated method stub

	}

	public void providerStateChanged(LocationProvider arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void monitoringStateChanged(boolean arg0) {
		// TODO Auto-generated method stub

	}

	public void proximityEvent(Coordinates arg0, Location arg1) {
		// TODO Auto-generated method stub

	}
}
