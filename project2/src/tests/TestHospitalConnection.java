package tests;

import org.junit.Before;
import org.junit.Test;

import server.HospitalServer;

import client.HospitalClient;

public class TestHospitalConnection {

	//@Before
	@Test
	public void setUpConnection(){
		new HospitalServer(HospitalServer.PORT);
		new HospitalClient(HospitalServer.HOSTNAME, HospitalServer.PORT);
	}
}
