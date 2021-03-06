package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.Endianness;

public class PackedToUnpackedTest {

	@Test
	public void testMultiple() throws Exception {
		@SuppressWarnings("resource")
		PackedToUnpacked pack2Unpack = new PackedToUnpacked(new ArrayByteInput(0, 1, 2), 1, Endianness.GR_MSB_FIRST);
		// 0
		for (int i = 0; i < 8; i++) {
			assertEquals(0, pack2Unpack.readByte());
		}
		// 1
		for (int i = 0; i < 7; i++) {
			assertEquals(0, pack2Unpack.readByte());
		}
		assertEquals(1, pack2Unpack.readByte());
		// 2
		for (int i = 0; i < 6; i++) {
			assertEquals(0, pack2Unpack.readByte());
		}
		assertEquals(1, pack2Unpack.readByte());
		assertEquals(0, pack2Unpack.readByte());

	}

	@Test
	public void testSuccess() throws Exception {
		@SuppressWarnings("resource")
		PackedToUnpacked pack2Unpack = new PackedToUnpacked(new ArrayByteInput(0b10101010), 1, Endianness.GR_MSB_FIRST);
		assertEquals(0b1, pack2Unpack.readByte());
		assertEquals(0b0, pack2Unpack.readByte());
		assertEquals(0b1, pack2Unpack.readByte());
		assertEquals(0b0, pack2Unpack.readByte());
		assertEquals(8, pack2Unpack.getContext().getTotalSamples().longValue());

		pack2Unpack = new PackedToUnpacked(new ArrayByteInput(0b10101010), 1, Endianness.GR_LSB_FIRST);
		assertEquals(0b0, pack2Unpack.readByte());
		assertEquals(0b1, pack2Unpack.readByte());
		assertEquals(0b0, pack2Unpack.readByte());
		assertEquals(0b1, pack2Unpack.readByte());

		pack2Unpack = new PackedToUnpacked(new ArrayByteInput(0b10101010), 2, Endianness.GR_MSB_FIRST);
		assertEquals(0b10, pack2Unpack.readByte());
		assertEquals(0b10, pack2Unpack.readByte());
		assertEquals(4, pack2Unpack.getContext().getTotalSamples().longValue());

		pack2Unpack = new PackedToUnpacked(new ArrayByteInput(0b10101010), 2, Endianness.GR_LSB_FIRST);
		assertEquals(0b01, pack2Unpack.readByte());
		assertEquals(0b01, pack2Unpack.readByte());
	}

	@SuppressWarnings({ "unused", "resource" })
	@Test(expected = IllegalArgumentException.class)
	public void testUnsupportPartialChunks() {
		new PackedToUnpacked(new ArrayByteInput(0b10101010), 3, Endianness.GR_MSB_FIRST);
	}

}
