package ru.r2cloud.jradio.nayif1;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class Nayif1BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Nayif1Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(RealtimeTelemetry.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(HighResolutionData.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(HighResolutionDataBatch.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(WholeOrbit.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(WholeOrbitDataBatch.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
