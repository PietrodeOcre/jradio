package ru.r2cloud.jradio.eseo;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class EseoBeaconTest {

	@Test
	public void testType1() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA9861E4FFF0037AF20F34D0000000000000000000000000000000000000000000000000000B003F00000000000300000000000000FFFFBF11010187AABD1F8E093FAF87BBBD9FFFA4FF94FF69FF53FF96FF8B610000E0FF01B30000420C0000B20000420C000050550F0050540F0000000000000000000000000070107000000000");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type1.json", b);
	}

	@Test
	public void testType2() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA986132FFF0047ADA65E5655566606610622D015F0066001E001E00EA030C0076FF3A00FB018EFF94FF98FF85FF8DFF92FF9A009B00990094009C009600606100AAB100A20007005E0002000000200000008000020001000200000001000100130062000100000000000100690000000000010000003FD0000200E0FF0100000000");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type2.json", b);
	}

	@Test
	public void testType3() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA9861B8FFF0057AF2F20F34D00000FFF7FFFF000000004303230300000000000000000070100000000000000000000040007ECE7F0E00000000000C0F0E000000000000B8009C00DF00A2009B009A00A3009C00A300AF009A0000000000004000000B003F00000000000300000000000000FFFFBF11000000000000000000000000");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type3.json", b);
	}

	@Test
	public void testType4() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA986152FFF0067A0101C3000000DADB873DF3A4913C8E66B93D87577E3FFB766ABF12C62A3DECA404C034350B45F2EAFAC340B7CC45D48DE1C01F78CCBFDDF91040A0FA0D00000000000000000000000000E200C500C600BA00D000E000C700C600BE00D0006F003A00000000000000000000000000000000000000000066016002");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type4.json", b);
	}

	@Test
	public void testType5() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA986160FFF0077700000000000000000000000000000000000000000000000000000040000000000000000000000B003F00000000000300000000000000000000000000C3000000000000008061003FD0000200000000B36BB76C0000001B01A000D6420C0000B26BB64B000000E4008B00D6420C00000000000000000000");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type5.json", b);
	}

	@Test
	public void testType6() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA98610AFFF0087A0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type6.json", b);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(EquipmentStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(CpuError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(TcError1.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(TcError2.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Rs422Status.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Rs422Error.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Rs485Status.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Rs485Error.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ObdStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PmmError1.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(TtTxStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(TtError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PlatformFdir.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(MmError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(MtError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(CanStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PlCanError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PyCanError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(CanTimeoutError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(HkStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(HkError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ObdError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ObdTempError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(AcsError.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(HSTXStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(HSTXCommunicationCondition.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(HSTXMemoryCondition.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();

		assertPojoMethodsFor(Type1.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Type2.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Type3.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Type4.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Type5.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Type6.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}

}
