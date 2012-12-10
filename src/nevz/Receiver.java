package nevz;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class Receiver extends MIDlet {

	public static Display display;
	public static Receiver midlet;
	public String noTelp;
	public Displayable displayable;
	public static String imei;
	public static String imeiSiemens;
	public static String imeiSamsung;
	public static String imeiSE;
	public static String imeiMotorola;
	public static String imeiNokia;
	public Canvas screen;

	public Receiver() {
		display = Display.getDisplay(this);
		midlet = this;

		// Descibe Imei like HP type
		imeiSiemens = System.getProperty("com.siemens.IMEI"); // Siemens
		imeiSamsung = System.getProperty("com.samsung.imei"); // Samsung
		imeiMotorola = System.getProperty("com.motorola.IMEI"); // Motorola
		imeiSE = System.getProperty("com.sonyericsson.imei"); // Sony Erricson
		imeiNokia = System.getProperty("com.nokia.mid.imei"); // Nokia

		// Cek imei Type HP
		if (System.getProperty("com.siemens.IMEI") != null) {
			imei = imeiSiemens;
		} else if (System.getProperty("com.samsung.imei") != null) {
			imei = imeiSamsung;
		} else if (System.getProperty("com.sonyericsson.imei") != null) {
			imei = imeiSE;
		} else if (System.getProperty("com.motorola.IMEI") != null) {
			imei = imeiMotorola;
		} else if (System.getProperty("com.nokia.mid.imei") != null) {
			imei = imeiNokia;
		}
	}

	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub
	}

	protected void startApp() throws MIDletStateChangeException {
		display.setCurrent(new IntroMenuReceiver(false));
	}

	public void setDisplayable(Displayable displayable) {
		this.displayable = displayable;
	}

	public Displayable getDisplayable() {
		return displayable;
	}
}
