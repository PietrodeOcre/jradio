package ru.r2cloud.jradio.nusat;

public class Randomize {
	
	private static final int[] sequence = new int[] {0x1D, 0x8B, 0x06, 0x0C, 0x54, 0xDF, 0x21, 0xCB,
			0x5C, 0x74, 0xE3, 0x15, 0x68, 0x04, 0x41, 0x91,
			0x7A, 0x3D, 0x7A, 0x81, 0x30, 0x57, 0x1A, 0x0A,
			0x09, 0xDB, 0x33, 0x57, 0x1F, 0x86, 0xEF, 0x58,
			0xE0, 0x16, 0xBD, 0x9B, 0xA6, 0x42, 0xFB, 0x09,
			0xD6, 0xCB, 0xE1, 0x27, 0x8E, 0xE7, 0x95, 0x1B,
			0x46, 0x4C, 0xEE, 0xC3, 0x75, 0x7D, 0xA6, 0x1C,
			0xF2, 0x45, 0x01, 0x00, 0xFE, 0xAF, 0xFD, 0x03};

	public static void shuffle(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) ((data[i] & 0xFF) ^ sequence[i % sequence.length]);
		}
	}

	private Randomize() {
		// do nothing
	}
}
