package ru.r2cloud.jradio.util;

public class MTRandom extends java.util.Random {

	/**
	 * Auto-generated serial version UID. Note that MTRandom does NOT support serialisation of its internal state and it may even be necessary to implement read/write methods to re-seed it properly. This is only here to make Eclipse shut up about it being missing.
	 */
	private static final long serialVersionUID = -515082678588212038L;

	// Constants used in the original C implementation
	private static final int UPPER_MASK = 0x80000000;
	private static final int LOWER_MASK = 0x7fffffff;
                         
	private static final int N = 624;
	private static final int M = 397;
	private static final int[] MAGIC = { 0x0, 0x9908b0df };
	private static final int MAGIC_FACTOR1 = 1812433253;
	private static final int MAGIC_FACTOR2 = 1664525;
	private static final int MAGIC_FACTOR3 = 1566083941;
	private static final int MAGIC_MASK1 = 0x9d2c5680;
	private static final int MAGIC_MASK2 = 0xefc60000;
	private static final int MAGIC_SEED = 19650218;
	private static final long DEFAULT_SEED = 5489L;

	// Internal state
	private transient int[] mt;
	private transient int mti;
	private transient boolean compat = false;

	// Temporary buffer used during setSeed(long)
	private transient int[] ibuf;

	/**
	 * The default constructor for an instance of MTRandom. This invokes the no-argument constructor for java.util.Random which will result in the class being initialised with a seed value obtained by calling System.currentTimeMillis().
	 */
	public MTRandom() {
		super();
	}

	/**
	 * This version of the constructor can be used to implement identical behaviour to the original C code version of this algorithm including exactly replicating the case where the seed value had not been set prior to calling genrand_int32.
	 * <p>
	 * If the compatibility flag is set to true, then the algorithm will be seeded with the same default value as was used in the original C code. Furthermore the setSeed() method, which must take a 64 bit long value, will be limited to using only the lower 32 bits of the seed to facilitate seamless migration of existing C code into Java where identical behaviour is required.
	 * <p>
	 * Whilst useful for ensuring backwards compatibility, it is advised that this feature not be used unless specifically required, due to the reduction in strength of the seed value.
	 * 
	 * @param compatible
	 *            Compatibility flag for replicating original behaviour.
	 */
	public MTRandom(boolean compatible) {
		super(0L);
		compat = compatible;
		setSeed(compat ? DEFAULT_SEED : System.currentTimeMillis());
	}

	/**
	 * This version of the constructor simply initialises the class with the given 64 bit seed value. For a better random number sequence this seed value should contain as much entropy as possible.
	 * 
	 * @param seed
	 *            The seed value with which to initialise this class.
	 */
	public MTRandom(long seed) {
		super(seed);
	}

	// Initializes mt[N] with a simple integer seed. This method is
	// required as part of the Mersenne Twister algorithm but need
	// not be made public.
	private final void setSeed(int seed) {

		// Annoying runtime check for initialisation of internal data
		// caused by java.util.Random invoking setSeed() during init.
		// This is unavoidable because no fields in our instance will
		// have been initialised at this point, not even if the code
		// were placed at the declaration of the member variable.
		if (mt == null)
			mt = new int[N];

		// ---- Begin Mersenne Twister Algorithm ----
		mt[0] = seed;
		for (mti = 1; mti < N; mti++) {
			mt[mti] = (MAGIC_FACTOR1 * (mt[mti - 1] ^ (mt[mti - 1] >>> 30)) + mti);
		}
		// ---- End Mersenne Twister Algorithm ----
	}

	/**
	 * This method resets the state of this instance using the 64 bits of seed data provided. Note that if the same seed data is passed to two different instances of MTRandom (both of which share the same compatibility state) then the sequence of numbers generated by both instances will be identical.
	 * <p>
	 * If this instance was initialised in 'compatibility' mode then this method will only use the lower 32 bits of any seed value passed in and will match the behaviour of the original C code exactly with respect to state initialisation.
	 * 
	 * @param seed
	 *            The 64 bit value used to initialise the random number generator state.
	 */
	@Override
	public final synchronized void setSeed(long seed) {
		if (compat) {
			setSeed((int) seed);
		} else {

			// Annoying runtime check for initialisation of internal data
			// caused by java.util.Random invoking setSeed() during init.
			// This is unavoidable because no fields in our instance will
			// have been initialised at this point, not even if the code
			// were placed at the declaration of the member variable.
			if (ibuf == null)
				ibuf = new int[2];

			ibuf[0] = (int) seed;
			ibuf[1] = (int) (seed >>> 32);
			setSeed(ibuf);
		}
	}

	/**
	 * This method resets the state of this instance using the integer array of seed data provided. This is the canonical way of resetting the pseudo random number sequence.
	 * 
	 * @param buf
	 *            The non-empty integer array of seed information.
	 * @throws NullPointerException
	 *             if the buffer is null.
	 * @throws IllegalArgumentException
	 *             if the buffer has zero length.
	 */
	public final synchronized void setSeed(int[] buf) {
		int length = buf.length;
		if (length == 0) {
			throw new IllegalArgumentException("Seed buffer may not be empty");
		}
		// ---- Begin Mersenne Twister Algorithm ----
		int i = 1;
		int j = 0;
		int k = (N > length ? N : length);
		setSeed(MAGIC_SEED);
		for (; k > 0; k--) {
			mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * MAGIC_FACTOR2)) + buf[j] + j;
			i++;
			j++;
			if (i >= N) {
				mt[0] = mt[N - 1];
				i = 1;
			}
			if (j >= length)
				j = 0;
		}
		for (k = N - 1; k > 0; k--) {
			mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * MAGIC_FACTOR3)) - i;
			i++;
			if (i >= N) {
				mt[0] = mt[N - 1];
				i = 1;
			}
		}
		mt[0] = UPPER_MASK; // MSB is 1; assuring non-zero initial array
		// ---- End Mersenne Twister Algorithm ----
	}

	/**
	 * This method forms the basis for generating a pseudo random number sequence from this class. If given a value of 32, this method behaves identically to the genrand_int32 function in the original C code and ensures that using the standard nextInt() function (inherited from Random) we are able to replicate behaviour exactly.
	 * <p>
	 * Note that where the number of bits requested is not equal to 32 then bits will simply be masked out from the top of the returned integer value. That is to say that:
	 * 
	 * <pre>
	 * mt.setSeed(12345);
	 * int foo = mt.nextInt(16) + (mt.nextInt(16) &lt;&lt; 16);
	 * </pre>
	 * 
	 * will not give the same result as
	 * 
	 * <pre>
	 * mt.setSeed(12345);
	 * int foo = mt.nextInt(32);
	 * </pre>
	 * 
	 * @param bits
	 *            The number of significant bits desired in the output.
	 * @return The next value in the pseudo random sequence with the specified number of bits in the lower part of the integer.
	 */
	@Override
	protected final synchronized int next(int bits) {
		// ---- Begin Mersenne Twister Algorithm ----
		int y, kk;
		if (mti >= N) { // generate N words at one time

			// In the original C implementation, mti is checked here
			// to determine if initialisation has occurred; if not
			// it initialises this instance with DEFAULT_SEED (5489).
			// This is no longer necessary as initialisation of the
			// Java instance must result in initialisation occurring
			// Use the constructor MTRandom(true) to enable backwards
			// compatible behaviour.

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ MAGIC[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ MAGIC[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ MAGIC[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];

		// Tempering
		y ^= (y >>> 11) & 0xffffffff;
		y ^= (y << 7) & MAGIC_MASK1;
		y ^= (y << 15) & MAGIC_MASK2;
		y ^= (y >>> 18);
		// ---- End Mersenne Twister Algorithm ----
		return (y >>> (32 - bits));
	}

}
