package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry2 {

	private long time;
	private PcuDep[] pcuDep;
	private PcuSdc[] pcuSdc;
	private PcuBat[] pcuBat;
	private PcuBus[] pcuBus;
	private AckInfo[] ackInfo;

	public Telemetry2() {
		// do nothing
	}

	public Telemetry2(LittleEndianDataInputStream dis) throws IOException {
		time = dis.readUnsignedInt();
		pcuDep = new PcuDep[2];
		for (int i = 0; i < pcuDep.length; i++) {
			pcuDep[i] = new PcuDep(dis);
		}
		pcuSdc = new PcuSdc[2];
		for (int i = 0; i < pcuSdc.length; i++) {
			pcuSdc[i] = new PcuSdc(dis);
		}
		pcuBat = new PcuBat[2];
		for (int i = 0; i < pcuBat.length; i++) {
			pcuBat[i] = new PcuBat(dis);
		}
		pcuBus = new PcuBus[2];
		for (int i = 0; i < pcuBus.length; i++) {
			pcuBus[i] = new PcuBus(dis);
		}
		ackInfo = new AckInfo[3];
		for (int i = 0; i < ackInfo.length; i++) {
			ackInfo[i] = new AckInfo(dis);
		}
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
