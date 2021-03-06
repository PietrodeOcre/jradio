package ru.r2cloud.jradio.smogp;

import java.io.IOException;
import java.util.UUID;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.LongValueSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.Tag;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;

public class SmogPShortCorrelate implements MessageInput {

	private static final int STEP = 51;
	private static final int[] SYNCWORD = new int[] { 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1 };

	private final ByteInput input;
	private final byte[] window;
	private int currentIndex = 0;
	private final int threshold;

	public SmogPShortCorrelate(ByteInput input, int threshold) {
		this.input = input;
		this.threshold = threshold;
		window = new byte[STEP * 52];
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (true) {
			window[currentIndex] = input.readByte();
			currentIndex++;
			if (currentIndex >= window.length) {
				currentIndex = 0;
			}
			if (!match()) {
				continue;
			}
			byte[] result = new byte[window.length];
			System.arraycopy(window, currentIndex, result, 0, window.length - currentIndex);
			System.arraycopy(window, 0, result, window.length - currentIndex, currentIndex);
			Tag tag = new Tag();
			tag.setId(UUID.randomUUID().toString());
			LongValueSource currentSample = getContext().getCurrentSample();
			if (currentSample != null) {
				tag.put(CorrelateAccessCodeTag.SOURCE_SAMPLE, currentSample.getValue());
			}
			getContext().put(tag.getId(), tag);
			return result;
		}
	}

	private boolean match() {
		int match = 0;
		for (int j = 0; j < SYNCWORD.length; j++) {
			int bit;
			int arrayIndex = currentIndex + j * STEP;
			if (arrayIndex >= window.length) {
				arrayIndex = arrayIndex - window.length;
			}
			if (window[arrayIndex] > 0) {
				bit = 1;
			} else {
				bit = 0;
			}
			if (bit == SYNCWORD[j]) {
				match++;
			}
		}
		if (match >= SYNCWORD.length - threshold) {
			return true;
		}
		return false;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

}
