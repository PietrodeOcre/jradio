## About [![Build Status](https://travis-ci.org/dernasherbrezon/jradio.svg?branch=master)](https://travis-ci.org/dernasherbrezon/jradio) [![codecov](https://codecov.io/gh/dernasherbrezon/jradio/branch/master/graph/badge.svg)](https://codecov.io/gh/dernasherbrezon/jradio)

Software radio decoding written in Java. The idea of this project is to get blocks from the [gnuradio](https://github.com/gnuradio) and implement them in Java. This gives the following:

  * No need for tooling. You could use [gnuradio-companion](https://wiki.gnuradio.org/index.php/HowToUse) to build a working pipeline, then use the same blocks to build the pipeline in Java.
  * All Java benefits: run same binaries on multiple platforms without compilation. Single programming language instead of Python and C++. Better tooling like IDE, profilers and memory analyzers.

## Supported blocks

All blocks meant to be binary compatible with gnuradio versions. This will ensure you got the same results when moving from gnuradio-companion to Java.

  * AdditiveScrambler
  * AGC
  * BinarySlicer
  * ClockRecoveryMM and ClockRecoveryMMComplex
  * ComplexConjugate
  * ConstellationSoftDecoder
  * CorrelateAccessCodeTag
  * CostasLoop
  * FIRFilter and ComplexFIRFilter
  * FixedLengthTagger
  * FloatToChar
  * FloatToComplex
  * LowPassFilter and LowPassFilterComplex
  * FrequencyXlatingFIRFilter
  * Rail
  * RootRaisedCosineFilter
  * GUI Histogram Sink (Spectogram) 
  * TaggedStreamToPdu
  * UnpackedToPacked
  * WavFileSource
  * WavFileSink
  * osmocom source (RtlTcp)
  * File Source (InputStreamSource)
  * File Sink (OutputStreamSink)
  * Multiply
  * MultiplyConst
  * SigSource
  * QuadratureDemodulation
  * PeakDetection. Detect peaks in the FFT. Not a gnuradio block.

Forward error correction:

  * Viterbi (hard)
  * ViterbiSoft
  * CCSDS ReedSolomon
  * BCH ReedSolomon
  
Frequency correction:
  
  * GmskFrequencyCorrection. Calculates frequency correction based on 2 main GMSK spikes

## Supported satellites

Using the blocks above, you could decode signals from the following satellites:

  * AAUSAT-4 (NORAD 41460). Digital telemetry only. Based on [gr-aausat](https://github.com/daniestevez/gr-aausat). See the pipeline at the [AAUSAT4Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/aausat4/AAUSAT4Test.java)
  * Meteor-M N2 (NORAD 40069). Decode pictures. Based on [meteor-decoder](https://github.com/artlav/meteor_decoder) and [meteor-m2-lrpt](https://github.com/otti-soft/meteor-m2-lrpt). See the pipeline at the [MeteorImageTest](https://github.com/dernasherbrezon/jradio/blob/lrpt/src/test/java/ru/r2cloud/jradio/meteor/MeteorImageTest.java)
  * 1Kuns-Pf (NORAD 43466). Decode short and long beacon formats, images. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [KunsPfTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/kunspf/KunsPfTest.java)
  * Sat3Cat1 (NORAD 99901). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Sat3Cat1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/sat3cat1/Sat3Cat1Test.java)
  * AT03 (NORAD 42784). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [At03Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/at03/At03Test.java)
  * AU02/AU03 (NORAD 42723/42731). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Au02Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/au02/Au02Test.java)  
  
## Doppler correction

Doppler correction could be made using SigSource and Multiply blocks. Here is the sample code:

```java
PassPredictor predictor = new PassPredictor(tle, currentLocation);
SigSource source2 = new SigSource(Waveform.COMPLEX, sampleRate, new DopplerValueSource(sampleRate, satelliteFrequency, correctPeriodMillis, startTimeMillis) {

	@Override
	public long getDopplerFrequency(long satelliteFrequency, long currentTimeMillis) {
		return predictor.getDownlinkFreq(satelliteFrequency, new Date(currentTimeMillis));
	}
}, 1.0f);
Multiply mul = new Multiply(source, source2, true);
```

Where "getDopplerFrequency" was calculated using [predict4java](https://github.com/badgersoftdotcom/predict4java)
