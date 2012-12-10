package sms.manager;

import java.io.IOException;

import javax.microedition.io.Connector;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 * @author Neo-Nevz
 *
 */
public class SMSManagerReceiver {

	// Make Construktor Private
	private SMSManagerReceiver() {

	}

	// Sender Message
	public static void sender(String nomor, String message, String portSender) {

		String nomorTelp = "sms://" + nomor + portSender;
		try {
			MessageConnection mc = (MessageConnection) Connector.open(nomorTelp);
			TextMessage tm = (TextMessage) mc.newMessage(MessageConnection.TEXT_MESSAGE);
			tm.setPayloadText(message);
			mc.send(tm);
			mc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Receiver Message
	public static void receiver(SMSManagerReceiverListener listener, String portReceiver) {
		try {

			MessageConnection mcr = (MessageConnection) Connector.open("sms://" + portReceiver);
			while (true) {
				Message msg = mcr.receive();
				if (msg instanceof TextMessage) {
					TextMessage tmsg = (TextMessage) msg;
					String msgOut = tmsg.getPayloadText();
					listener.messsageReceiverEvent(msgOut);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
