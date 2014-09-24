package server;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import javax.security.cert.X509Certificate;
import javax.net.ssl.*;
import org.json.JSONException;
import org.json.JSONObject;
import database.JSONDatabase;
import java.util.Calendar;

public class HospitalServer {

	private JSONDatabase db;

	/*********************************
	 * PUBLIC CONSTANTS
	 *********************************/

	public final static String BASE_PATH = "rightfiles/";
	public final static int PORT = 12345;
	public static final String HOSTNAME = "localhost";

	/*********************************
	 * PRIVATE CONSTANTS
	 *********************************/

	private static final String TRUSTSTORE_PATH = BASE_PATH
			+ "Server/serverTS.jks";
	private static final String TRUSTSTORE_PASSWORD = "passphrase";
	private static final String KEYSTORE_SERVER_PATH = BASE_PATH
			+ "Server/serverKS";
	private static final String KEYSTORE_PASSWORD = "passphrase";
	private static final String USER_TYPE = "O=";
	private static final String DIVISION = "OU=";
	private static final String CERT_NAME = "CN=";

	/*********************************
	 * CONSTRUCTOR
	 *********************************/

	/**
	 * Creates JSON Database. Initiates HospitalServer with specified port and
	 * connects to client.
	 * 
	 * @param port
	 *            port to connect to.
	 */
	public HospitalServer(int port) {
		db = JSONDatabase.getInstance();
		try {
			connectToClient(port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Connects to the client.
	 * 
	 * @param port
	 * @throws Exception
	 */
	public void connectToClient(int port) throws Exception {
		// Copy+paste from JSSE example code

		// Sets keystore and truststore paths and passwords.
		this.setStorePathsAndPasswords();

		SSLContext ctx;
		KeyManagerFactory kmf;
		KeyStore ks;
		TrustManagerFactory tmf;

		char[] passphrase = KEYSTORE_PASSWORD.toCharArray();
		try {
			X509Certificate cert = null;
			String subject = "";
			// Specifies algorithms to be used
			ctx = SSLContext.getInstance("TLS");
			kmf = KeyManagerFactory.getInstance("SunX509");
			tmf = TrustManagerFactory.getInstance("SunX509");
			ks = KeyStore.getInstance("JKS");

			// Loads server KeyStore with associated passphrase
			ks.load(new FileInputStream(KEYSTORE_SERVER_PATH), passphrase);
			kmf.init(ks, passphrase);
			tmf.init(ks);
			ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
			SSLServerSocketFactory factory = ctx.getServerSocketFactory();

			SSLServerSocket serverSocket = (SSLServerSocket) factory
					.createServerSocket(port);
			SSLSocket socket = (SSLSocket) serverSocket.accept();
			socket.setNeedClientAuth(true);
			SSLSession session = socket.getSession();
			InputStream in = socket.getInputStream();// throws IOException()
			OutputStream out = socket.getOutputStream();// throws IOException()
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			StringBuilder sb = new StringBuilder();
			try {
				cert = session.getPeerCertificateChain()[0];
				subject = cert.getSubjectDN().getName();
				objOut.writeObject(subject);
				objOut.flush();
			} catch (SSLPeerUnverifiedException pue) {
				System.out.println("Peer unverified.");
			}
			ObjectInputStream objIn = new ObjectInputStream(in);
			// Main Loop
			while (true) {
				String clientInput = nextInputFromclient(objIn);
				String output = parseInput(clientInput, subject, objOut, objIn);
				outputToClient(output, objOut);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Waits for the next input from the client and returns it.
	 * 
	 * @param objIn
	 * @return input string from client
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private synchronized String nextInputFromclient(ObjectInputStream objIn)
			throws IOException, ClassNotFoundException {
		try {
			return (String) objIn.readObject();
		} catch (Exception e) {
			System.out.println("Client disconnected.");
			System.exit(1);
		}
		return null;
	}

	/**
	 * Parses the input received from the client.
	 * 
	 * @param clientInput
	 *            Input from client.
	 * @param subject
	 *            Client certificate subject.
	 * @throws IOException
	 */
	private String parseInput(String clientInput, String subject,
			ObjectOutputStream objOut, ObjectInputStream objIn)
			throws Exception {
		String command = clientInput.split(" ")[0];
		String parameter = "";
		if (clientInput.indexOf(' ') > -1) {
			parameter = clientInput.substring(clientInput.indexOf(' ')).trim();
		}
		String output = "";
		if (command.equals("read")) {
			output = readJournal(parameter, subject);
		} else if (command.equals("write")) {
			output = requestWrite(parameter, subject, objOut, objIn);
		} else if (command.equals("delete")) {
			output = deleteEntry(parameter, subject);
		} else if (command.equals("create")) {
			output = createJournal(parameter, subject, objOut, objIn);
		} else if (command.equals("leander")) {
			output = "OMG!! ROOT ACCESS!";
			JSONObject entry = db.getEntry(parameter);
			if (entry != null) {
				output += "\n" + entry.getString("Jour");
			}
		} else {
			output = "Unknown command.";
		}
		return output;
	}

	/**
	 * Sends the string output to the client.
	 * 
	 * @param output
	 * @param objOut
	 * @throws IOException
	 */
	private void outputToClient(String output, ObjectOutputStream objOut)
			throws IOException {
		objOut.writeObject(output);
		objOut.flush();
	}

	/**
	 * 
	 * @return the actual date and time
	 */
	private String Date() {
		Calendar c = Calendar.getInstance();
		String date = Integer.toString(c.get(Calendar.YEAR)) + " "
				+ Integer.toString(c.get(Calendar.MONTH) + 1) + " "
				+ Integer.toString(c.get(Calendar.DATE)) + " "
				+ Integer.toString(c.get(Calendar.HOUR_OF_DAY)) + ":"
				+ Integer.toString(c.get(Calendar.MINUTE)) + ":"
				+ Integer.toString(c.get(Calendar.SECOND));
		return date;
	}

	/**
	 * Returns the journal of a patient if it the client has permission.
	 * 
	 * @param subject
	 *            Certificate of the client.
	 * @return If the client has permission: the journal belonging to
	 *         patientName. If the patient doesn't exist: the string
	 *         "Patient does not exist." Otherwise: the string
	 *         "You are not allowed to read this journal."
	 */
	private String readJournal(String patientName, String subject)
			throws Exception {
		String certName = subject.split(CERT_NAME)[1].split(",")[0]
				.toLowerCase();
		String division = subject.split(DIVISION)[1].split(",")[0]
				.toLowerCase();
		String userType = subject.split(USER_TYPE)[1].split(",")[0]
				.toLowerCase();
		JSONObject entry = db.getEntry(patientName);
		StringBuilder sb = new StringBuilder();
		if (entry != null) {
			try {
				if (division.equals("socialstyrelsen")
						|| ((userType.equals("doctor") || userType
								.equals("nurse")) && division.equals(entry
								.getString("Div").toLowerCase()))
						|| patientName.equals(certName)
						|| certName.equals(entry.getString("Nurse")
								.toLowerCase())) {
					System.out.println(userType + " : " + certName);
					System.out
							.println("read" + " " + patientName + "s journal");
					System.out.println("@" + " " + Date() + "\n");
					sb.append(userType + " : " + certName + "\n");
					sb.append("read" + " " + patientName + "s journal" + "\n");
					sb.append("@" + " " + Date() + "\n" + "\n");
					writeToLog(sb.toString());

					return entry.getString("Jour");

				}
			} catch (JSONException e) {
				System.out.println("JSONException: " + e.getMessage());
			}
		} else {
			return "Patient does not exist.";
		}
		return "You are not allowed to read this journal.";
	}

	private void writeToLog(String logString) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("log.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(logString);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests if subject is allowed to modify the journal of patientName. Returns
	 * a status message.
	 * 
	 * @param patientName
	 * @param subject
	 * @return "Please input journal entry:" if allowed
	 */
	private String requestWrite(String patientName, String subject,
			ObjectOutputStream objOut, ObjectInputStream objIn)
			throws Exception {
		String certName = subject.split(CERT_NAME)[1].split(",")[0]
				.toLowerCase();
		String userType = subject.split(USER_TYPE)[1].split(",")[0]
				.toLowerCase();
		JSONObject entry = (JSONObject) db.getEntry(patientName);
		StringBuilder sb = new StringBuilder();
		if (entry != null) {
			try {
				if (userType.equals("doctor")
						&& certName.equals(entry.getString("Dr").toLowerCase())
						|| userType.equals("nurse")
						&& certName.equals(entry.getString("Nurse")
								.toLowerCase())
						|| certName.equals(entry.getString("Nurse")
								.toLowerCase())) {
					String output = "write accepted";
					outputToClient(output, objOut);
					waitForJournalFromClient(objOut, objIn, patientName);
					System.out.println(userType + " : " + certName);
					System.out.println("wrote" + " on " + patientName
							+ "s journal");
					System.out.println(Date() + "\n");
					sb.append(userType + " : " + certName + "\n");
					sb.append("wrote" + " on " + patientName + "s journal"
							+ "\n");
					sb.append("@" + " " + Date() + "\n" + "\n");
					writeToLog(sb.toString());
					return "Journal saved!";
				}
			} catch (JSONException e) {
				System.out.println("JSONException: " + e.getMessage());
			}
		} else {
			return "Patient does not exist.";
		}
		return "You are not allowed to write to this journal.";
	}

	private synchronized void waitForJournalFromClient(
			ObjectOutputStream objOut, ObjectInputStream objIn,
			String patientName) throws Exception {
		String journal = (String) objIn.readObject();
		db.writeJournal(patientName, journal);
	}

	/**
	 * Removes the entry called patientName from the database
	 * 
	 * @param patientName
	 * @param subject
	 *            The person attempting to execute the command
	 * @return A status message in the form of a String
	 */
	private String deleteEntry(String patientName, String subject) {
		String division = subject.split("OU=")[1].split(",")[0].toLowerCase();
		if (division.equals("socialstyrelsen")) {
			StringBuilder sb = new StringBuilder();
			if (db.deleteEntry(patientName)) {
				System.out.println(division);
				System.out.println("deleted " + patientName + "s journal");
				System.out.println("@" + " " + Date() + "\n");
				sb.append(division + "\n");
				sb.append("deleted " + patientName + "s journal" + "\n");
				sb.append("@" + " " + Date() + "\n" + "\n");
				writeToLog(sb.toString());
				return patientName + " deleted from database.";
			} else {
				return "Patient does not exist.";
			}
		} else {
			return "You are not allowed to delete this journal.";
		}
	}

	/**
	 * Assigns a nurse and a doctor to the patient <i>patientName</i>
	 * 
	 * @param objOut
	 *            OutputStream to write to.
	 * @param objIn
	 *            InputStream to receive client inputs.
	 * @param patientName
	 *            name of the patient
	 * @throws Exception
	 */
	private String createJournal(String patientName, String subject,
			ObjectOutputStream objOut, ObjectInputStream objIn)
			throws Exception {
		JSONObject entry = (JSONObject) db.getEntry(patientName);
		String certName = subject.split("CN=")[1].split(",")[0].toLowerCase();
		String division = subject.split("OU=")[1].split(",")[0].toLowerCase();
		String userType = subject.split("O=")[1].split(",")[0].toLowerCase();
		if (entry != null) {
			try {
				StringBuilder sb = new StringBuilder();
				if (userType.equals("doctor")
						&& certName.equals(entry.getString("Dr").toLowerCase())) {
					entry.put("Dr", certName);
					entry.put("Div", division);
					entry.put("Jour", "");
					String output = "create accepted";
					outputToClient(output, objOut);
					waitForNurseInputForCreateJournal(entry, objIn, patientName);
					System.out.println(userType + " : " + certName);
					System.out.println("created journal for " + patientName);
					System.out.println("@" + " " + Date() + "\n");
					sb.append(userType + " : " + certName + "\n");
					sb.append("created journal for " + patientName + "\n");
					sb.append("@" + " " + Date() + "\n" + "\n");
					writeToLog(sb.toString());
					return "Journal created for " + patientName + "!\n";
				} else {
					return "You are not allowed to create a journal for this patient.";
				}
			} catch (JSONException e) {
				System.out.println("JSONException: " + e.getMessage());
			}
		}
		return "Patient does not exist.";
	}

	/**
	 * Private helper method to createJournal that reads input from client and
	 * invokes the database to make the proper changes.
	 * 
	 * @param objOut
	 *            OutputStream to write to.
	 * @param objIn
	 *            InputStream to receive client inputs.
	 * @param patientName
	 *            name of the patient
	 * @throws Exception
	 */
	private synchronized void waitForNurseInputForCreateJournal(
			JSONObject entry, ObjectInputStream objIn, String patientName)
			throws Exception {
		String nurse = (String) objIn.readObject();
		entry.put("Nurse", nurse);
	}

	/*
	 * Sets keystore and truststore paths and passwords
	 */
	private void setStorePathsAndPasswords() {
		System.setProperty("javax.net.ssl.keyStore", KEYSTORE_SERVER_PATH);
		System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);
		System.setProperty("javax.net.ssl.trustStore", TRUSTSTORE_PATH);
		System.setProperty("javax.net.ssl.trustStorePassword",
				TRUSTSTORE_PASSWORD);
	}

	public static void main(String[] args) {
		// Creates a HospitalServer with port set to PORT}
		new HospitalServer(PORT);
	}

}
