package ru.r2cloud.jradio.aistechsat3;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Aistechsat3BeaconTest {

	@Test
	public void testType10() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0180D783010A01000176EE5CF8055A00010101007300770000000000000000000000060000000401905CF8055A0045B037F2075CF8055A0005FF93F8BEFF9404001D6B0800018E900FBAD51000D0234401DA000000010EDD0A850017548402001C344600007B8B006E0075C76E5CF8055A00010E0E0E0D10602005003F00000000004800B900A800F7002F0212016200CA000D000D0009000800060006010000010101000003020000012200024CD80000011300000009000000030000000500000002000000000000000000000000000000000000000007");
		Aistechsat3Beacon beacon = new Aistechsat3Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Aistechsat3Beacon-type10.json", beacon);
	}

	@Test
	public void testType20() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0180D78301140100012E1E5CA265820004C1150000432DB7B0C28CD4DE43CEEF8642F8DACDC281BDAA43B15FB900000000433600000000000043A8000000000000000000000706070600000706070607063F54A3DBDC3F094D823D9929D0BA8347B838A85029B9C6108FDBDC08CF360000000000000000000000000000000000800000008000000080000000AA925CA265820004000000000002003A003A3D8DFC993D956E1DBCA30789BC5E8BBB3C9D734B01020201010000B40851B8C0");
		Aistechsat3Beacon beacon = new Aistechsat3Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Aistechsat3Beacon-type20.json", beacon);
	}

	@Test
	public void testType21() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0180D78301150100012E1E5CF805790004435FEE42C38E223C4307FCD4C05BA4753FA92FD73CB72330C2AE0000BAAF1B1E41824DC7000000000000000000000000000000000000000000000000000000000000000040460000415400004102000041F1400040E70000000000000000000000000000AA925CF80579000403000000BE43AF86");
		Aistechsat3Beacon beacon = new Aistechsat3Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Aistechsat3Beacon-type21.json", beacon);
	}

	// type 22 is being tested by demod test

	@Test
	public void testType23() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0180D78301170100019B855CF8057900043F5E122D3E844114BEC351BD3E4042D0BDF7D8D3BF29B0FC3F28F65E3EAA175ABD6FE0613CD48D703ADED45F42C800003AAF1B1EC1824DC70000000000000000000000000000000000000000000000000000000000000000150F5CF805790004BE3B783D3F10A4803F0BEB28BF1721B2BD6FE0563CCB76A23ADECCF63B1A5CF8057900044A6CCD764A5B2F134A85D3A545896B3B4511E4B1C5B45230");
		Aistechsat3Beacon beacon = new Aistechsat3Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Aistechsat3Beacon-type23.json", beacon);
	}

	@Test
	public void testType26() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0180D783011A01000176EE5D0B0F4D00040101BE000000045D0B0F4B13DD13E200030000000000A637AC5D0B0F4D00040002010101012E1E5D0B0F4D0004C1130000070607060000070607060706C00DB41B0000000000000000000000000000000000000000");
		Aistechsat3Beacon beacon = new Aistechsat3Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Aistechsat3Beacon-type26.json", beacon);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Aistechsat3Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ADCSBeacon0.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ADCSBeacon2.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PlatformBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(UHFAntennaTelemetryBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ADCSFineSunSensorBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ADCSBeacon6.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PayloadBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
