package ru.r2cloud.jradio.smogp;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SmogPBeaconTest {

	@Test
	public void testTelemetry1() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0137AD615E2AAA615E34F500000000000000000000300FAB615E34F500000000000000000000508CAA615E34F500000000000000000000680BAB615E34F50000000000000000000088C5AA615E34F500000000000000000000B0F5AA615E34F500000000000000000000D00A0C4A090C4B080C4E0000E5C38932F821B02D8A7B");
		SmogPBeacon result = new SmogPBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SmogPBeaconTelemetry1.json", result);
	}

	@Test
	public void testTelemetry2() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0238AD615E38AD615EEC0D0040282FAD615EEC0D00402833AD615E25002700740D00000500A5020034AD615E29002B00760D00000500A0020031AD615E560F00002A000C32AD615E5F0F000029000C36AD615E580FB70C00000B000036AD615E650FA10C00000E00000A0C4A090C4B080C4E00000000542D85C6607BEF176033");
		SmogPBeacon result = new SmogPBeacon();
		result.readBeacon(data);
		 AssertJson.assertObjectsEqual("SmogPBeaconTelemetry2.json", result);
	}
}
