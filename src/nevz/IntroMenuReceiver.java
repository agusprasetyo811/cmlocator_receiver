package nevz;

import gps.manager.GPSManager;

import java.io.IOException;
import java.util.Hashtable;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import data.manager.DataManager;
import data.manager.RMS;

import sms.manager.SMSManagerReceiver;
import sms.manager.SMSManagerReceiverListener;

public class IntroMenuReceiver extends Canvas implements SMSManagerReceiverListener {

	// Action to hidden or show screen
	public boolean showScreen;
	public Image transparant;

	public IntroMenuReceiver(boolean showScreen) {
		setFullScreenMode(true);
		this.showScreen = showScreen;

		// Images
		try {
			transparant = Image.createImage("/bgTransparan.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Menerima Request control Sender
		new Thread() {
			public void run() {
				SMSManagerReceiver.receiver((SMSManagerReceiverListener) IntroMenuReceiver.this, ":11108");
			};
		}.start();

		// Testing IMEI
		System.out.println("IMEI HP->" + Receiver.imei);
		// Sending information Receiver
		String phoneNumber = DataManager.noSender();
		sendingPhoneNumber(phoneNumber, "Mobile Receiver has been On. IMEI : " + Receiver.imei, "");
	}

	// Send Information from Receiver
	public void sendingPhoneNumber(final String nomor, final String message, final String portSender) {
		new Thread() {
			public void run() {
				SMSManagerReceiver.sender(nomor, message, portSender);
			};
		}.start();
	}

	// Get Alarm
	public void alrm() {
		Alert alert = new Alert("Peringatan", " Awas Ada Agus Bocah Bagus", null, AlertType.WARNING);
		alert.setTimeout(10000);
		Receiver.display.setCurrent(alert);
	}

	// Get Vibrate
	public void vibrate() {
		Receiver.display.vibrate(10000);
	}

	protected void paint(Graphics g) {
		if (showScreen == false) {
			g.setColor(0x000000);
			g.fillRect(0, 0, getWidth(), getHeight());
			Receiver.display.setCurrent(null);
		}
	}

	public void messsageReceiverEvent(String message) {
		if ("#GP5".equals(message)) {
			GPSManager.datalocation();
			return;
		}
		if ("#4L4RM".equals(message)) {
			alrm();
			return;
		}
		if ("#V113R4TE".equals(message)) {
			vibrate();
			return;
		}
		if (message.equals(message)) {
			Hashtable dataReceiver = new Hashtable();
			dataReceiver.put("noSender", message);
			RMS.save("dataReceiver", dataReceiver);
			return;
		}
	}
};