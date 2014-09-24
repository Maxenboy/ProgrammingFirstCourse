package client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.util.Scanner;
import javax.net.ssl.*;

import server.HospitalServer;

public class HospitalClient {

	private ObjectInputStream objIn;
	private ObjectOutputStream objOut;
	private InputStream in;
	private OutputStream out;

	/*********************************
	 * PRIVATE CONSTANTS
	 *********************************/

	private static final String DELIMITER = ";";
	private static final String TRUSTSTORE_PATH = HospitalServer.BASE_PATH + "clientTS";
	private static final String TRUSTSTORE_PASSWORD = "passphrase";

	/**
	 * Default constructor. Connects to HospitalServer with specified port.
	 */
	public HospitalClient(String hostname, int port) {
		try {
			connect(hostname, port, login());
			prompt(objOut, objIn);
			in.close(); // throws IOException()
			out.close(); // throws IOException()
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prompts the input of username and password.
	 * 
	 * @return username and password
	 */
	public String login() {
		StringBuffer user = new StringBuffer();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter username: ");
		user.append(scan.nextLine()).append(DELIMITER);
		System.out.println("Enter password: ");
		user.append(scan.nextLine());
		return user.toString();
	}

	/**
	 * Connects to server with hostname, port and user
	 * 
	 * @param hostname
	 *            server hostname
	 * @param port
	 *            server port
	 * @param userInfo
	 *            this users login username and password as a string separated
	 *            with DELIMITER
	 */
	public void connect(String hostname, int port, String userInfo) throws Exception {
		// Sets truststore path and password
		this.setTrustStorePathsAndPassword();
		SSLContext ctx;
		KeyManagerFactory kmf;
		KeyStore ks;
		KeyStore ts;
//		TrustManagerFactory tmf;

		try {
			// Specifies algorithms to be used
			ctx = SSLContext.getInstance("TLS");
			kmf = KeyManagerFactory.getInstance("SunX509");
			ks = KeyStore.getInstance("JKS");
			ts = KeyStore.getInstance("JKS");
//			tmf = TrustManagerFactory.getInstance("SunX509");

			String username = userInfo.split(DELIMITER)[0];
			String passphrase = userInfo.split(DELIMITER)[1];

			// Loads user KeyStore and TrustStore with associated
			// passphrases
			ks.load(new FileInputStream(HospitalServer.BASE_PATH + username), passphrase.toCharArray());
			System.out.println("The keystore type: " + ks.getType()
					+ " keystore contains user " + ks.containsAlias(username));
			ts.load(new FileInputStream(TRUSTSTORE_PATH),
					TRUSTSTORE_PASSWORD.toCharArray());
			System.out.println("TrustStore contains user: "
					+ ts.containsAlias(username));

			kmf.init(ks, passphrase.toCharArray()); // throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException
			ctx.init(kmf.getKeyManagers(), null, null); // throws KeyManagementException
			// SocketFactory socketFactory = SSLSocketFactory.getDefault();
			SSLSocketFactory socketFactory = ctx.getSocketFactory(); // Throws IllegalStateException()
			SSLSocket socket = (SSLSocket) socketFactory.createSocket(hostname, port);// throws IOException, UnknownHostException
			InputStream in = socket.getInputStream();// throws IOException()
			OutputStream out = socket.getOutputStream();// throws IOException()
			objIn = new ObjectInputStream(in);
			objOut = new ObjectOutputStream(out); // throws IOException()
			out.flush(); // throws IOException()
			String commonName = ((String)objIn.readObject()).split("=")[1].split(",")[0];
			System.out.println("Welcome, " + commonName + "!\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void prompt(ObjectOutputStream objOut, ObjectInputStream objIn) throws Exception {
		Scanner scan = new Scanner(System.in);
		while (true) {
			String input = scan.nextLine().toLowerCase();
		if (input.toLowerCase().equals("quit")) {
				System.exit(0);
			}
			objOut.writeObject(input);
			objOut.flush();
			String answer = (String) objIn.readObject();
			handleAnswerFromServer(answer, objOut);
		}
	}

	private void handleAnswerFromServer(String answer, ObjectOutputStream objOut) throws Exception {
		if (answer.equals("write accepted")) {
			System.out.println("Please write the new journal:");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine().toLowerCase();
			objOut.writeObject(input);
			objOut.flush();
			System.out.println((String) objIn.readObject());
		}else {
			System.out.println(answer + "\n");	
		}
	}

	/*
	 * Sets truststore path and password
	 */
	private void setTrustStorePathsAndPassword() {
		System.setProperty("javax.net.ssl.trustStore", TRUSTSTORE_PATH);
		System.setProperty("javax.net.ssl.trustStorePassword",
				TRUSTSTORE_PASSWORD);
	}

	public static void main(String[] args) {
		new HospitalClient(HospitalServer.HOSTNAME, HospitalServer.PORT);
	}
}
