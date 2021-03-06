package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.EOFException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.WavFileSourceTest;

public class UnpackedToPackedTest {

	@Test
	public void testSuccess() throws Exception {
		try (ByteInput source = new UnpackedToPacked(new FixedLengthTagger(new CorrelateAccessCodeTag(new BinarySlicer(new ClockRecoveryMM(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 20.0f, (float) (0.25 * 0.175 * 0.175), 0.005f, 0.175f, 0.005f)), 8, "010011110101101000110100010000110101010101000010"), 2008), 1, Endianness.GR_MSB_FIRST)) {
			TestUtil.assertByteInput("UnpackedToPacked.bin", source);
			Tag tag = getFirst(source.getContext());
			assertEquals(251, tag.get(FixedLengthTagger.LENGTH));
		}
	}

	@Test
	public void testTotalNumberOfSamples() throws Exception {
		ArrayByteInput data = new ArrayByteInput(1, 0, 0, 0, 1, 1, 0, 1, 1);
		UnpackedToPacked u2p = new UnpackedToPacked(data, 1, Endianness.GR_MSB_FIRST);
		assertEquals((byte) 0b10001101, u2p.readByte());
		assertEquals((byte) 0b10000000, u2p.readByte());
		try {
			u2p.readByte();
			fail("expected eof");
		} catch (EOFException e) {
			// do nothing
		}
		assertEquals(2L, u2p.getContext().getTotalSamples().longValue());
		u2p.close();
	}

	public static Tag getFirst(Context context) {
		Map<String, Tag> tags = context.getTags();
		assertEquals(1, tags.size());
		Iterator<Entry<String, Tag>> it = tags.entrySet().iterator();
		if (it.hasNext()) {
			return it.next().getValue();
		}
		return null;
	}

}
