package ru.r2cloud.jradio.pwsat2;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class PwSat2BeaconTest {

	@Test
	public void testParseTelemetry() throws Exception {
		String data = "A0AEA682A864E0A0AEA682A8646103F0CD02000000076600C2372CBE970500000000E0DA6E380000000000000000000000000000000CBC0200D7090081E30D000000000000000000000000000000000080FCFFF57F0280386FC9092AE476A79CB0016000503971552AC7534A3661D09BBD4777223D00052AE06AAB28061000575B8EE88000D8DABA45C18803DC4C01E0B71380163A1E55880A000F00647301001757ECA2C61AEB9327C00340260A0040C617C708000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		PwSat2Beacon beacon = new PwSat2Beacon();
		beacon.readExternal(ViterbiTest.hexStringToByteArray(data));
		AssertJson.assertObjectsEqual("PwSat2Beacon-Telemetry.json", beacon);
	}

}
