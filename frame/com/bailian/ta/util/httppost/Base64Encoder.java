package com.bailian.ta.util.httppost;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/***
 * A class to encode Base64 streams and strings. See RFC 1521 section 5.2 for
 * details of the Base64 algorithm.
 * <p>
 * This class can be used for encoding strings: <blockquote>
 * 
 * <pre>
 * String unencoded = &quot;webmaster:try2gueSS&quot;;
 * String encoded = Base64Encoder.encode(unencoded);
 * </pre>
 * 
 * </blockquote> or for encoding streams: <blockquote>
 * 
 * <pre>
 * OutputStream out = new Base64Encoder(System.out);
 * </pre>
 * 
 * </blockquote>
 * 
 * @author <b>Jason Hunter</b>, Copyright &#169; 2000
 * @version 1.2, 2002/11/01, added encode(byte[]) method to better handle binary
 *          data (thanks to Sean Graham)
 * @version 1.1, 2000/11/17, fixed bug with sign bit for char values
 * @version 1.0, 2000/06/11
 */
public class Base64Encoder extends FilterOutputStream {

	private static final char[] chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '+', '/' };

	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
			5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26,
			27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1,
			-1, -1, -1 };

	private int charCount;
	private int carryOver;

	/***
	 * Constructs a new Base64 encoder that writes output to the given
	 * OutputStream.
	 * 
	 * @param out
	 *            the output stream
	 */
	public Base64Encoder(OutputStream out) {
		super(out);
	}

	/***
	 * Writes the given byte to the output stream in an encoded form.
	 * 
	 * @exception IOException
	 *                if an I/O error occurs
	 */
	@Override
	public void write(int b) throws IOException {
		// Take 24-bits from three octets, translate into four encoded chars
		// Break lines at 76 chars
		// If necessary, pad with 0 bits on the right at the end
		// Use = signs as padding at the end to ensure encodedLength % 4 == 0

		// Remove the sign bit,
		// thanks to Christian Schweingruber <chrigu@lorraine.ch>
		if (b < 0) {
			b += 256;
		}

		// First byte use first six bits, save last two bits
		if (charCount % 3 == 0) {
			int lookup = b >> 2;
			carryOver = b & 3; // last two bits
			out.write(chars[lookup]);
		}
		// Second byte use previous two bits and first four new bits,
		// save last four bits
		else if (charCount % 3 == 1) {
			int lookup = ((carryOver << 4) + (b >> 4)) & 63;
			carryOver = b & 15; // last four bits
			out.write(chars[lookup]);
		}
		// Third byte use previous four bits and first two new bits,
		// then use last six new bits
		else if (charCount % 3 == 2) {
			int lookup = ((carryOver << 2) + (b >> 6)) & 63;
			out.write(chars[lookup]);
			lookup = b & 63; // last six bits
			out.write(chars[lookup]);
			carryOver = 0;
		}
		charCount++;

		// Add newline every 76 output chars (that's 57 input chars)
		if (charCount % 57 == 0) {
			out.write('\n');
		}
	}

	/***
	 * Writes the given byte array to the output stream in an encoded form.
	 * 
	 * @param buf
	 *            the data to be written
	 * @param off
	 *            the start offset of the data
	 * @param len
	 *            the length of the data
	 * @exception IOException
	 *                if an I/O error occurs
	 */
	@Override
	public void write(byte[] buf, int off, int len) throws IOException {
		// This could of course be optimized
		for (int i = 0; i < len; i++) {
			write(buf[off + i]);
		}
	}

	/***
	 * Closes the stream, this MUST be called to ensure proper padding is
	 * written to the end of the output stream.
	 * 
	 * @exception IOException
	 *                if an I/O error occurs
	 */
	@Override
	public void close() throws IOException {
		// Handle leftover bytes
		if (charCount % 3 == 1) { // one leftover
			int lookup = (carryOver << 4) & 63;
			out.write(chars[lookup]);
			out.write('=');
			out.write('=');
		} else if (charCount % 3 == 2) { // two leftovers
			int lookup = (carryOver << 2) & 63;
			out.write(chars[lookup]);
			out.write('=');
		}
		super.close();
	}

	/***
	 * Returns the encoded form of the given unencoded string. The encoder uses
	 * the ISO-8859-1 (Latin-1) encoding to convert the string to bytes. For
	 * greater control over the encoding, encode the string to bytes yourself
	 * and use encode(byte[]).
	 * 
	 * @param unencoded
	 *            the string to encode
	 * @return the encoded form of the unencoded string
	 */
	public static String encode(String unencoded) {
		byte[] bytes = null;
		try {
			bytes = unencoded.getBytes("UTF-8");
		} catch (UnsupportedEncodingException ignored) {
		}
		return encode(bytes);
	}

	/***
	 * Returns the encoded form of the given unencoded string.
	 * 
	 * @param bytes
	 *            the bytes to encode
	 * @return the encoded form of the unencoded string
	 */
	public static String encode(byte[] bytes) {
		ByteArrayOutputStream out = new ByteArrayOutputStream((int) (bytes.length * 1.37));
		Base64Encoder encodedOut = new Base64Encoder(out);

		try {
			encodedOut.write(bytes);
			encodedOut.close();

			return out.toString("UTF-8");
		} catch (IOException ignored) {
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage: java com.oreilly.servlet.Base64Encoder fileToEncode");
			return;
		}

		Base64Encoder encoder = null;
		BufferedInputStream in = null;
		try {
			encoder = new Base64Encoder(System.out);
			in = new BufferedInputStream(new FileInputStream(args[0]));

			byte[] buf = new byte[4 * 1024]; // 4K buffer
			int bytesRead;
			while ((bytesRead = in.read(buf)) != -1) {
				encoder.write(buf, 0, bytesRead);
			}
		} finally {
			if (in != null)
				in.close();
			if (encoder != null)
				encoder.close();
		}
	}

	public static byte[] decode(String str) {
		byte[] data = str.getBytes();
		int len = data.length;
		ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
		int i = 0;
		int b1, b2, b3, b4;

		while (i < len) {

			/* b1 */
			do {
				b1 = base64DecodeChars[data[i++]];
			} while (i < len && b1 == -1);
			if (b1 == -1) {
				break;
			}

			/* b2 */
			do {
				b2 = base64DecodeChars[data[i++]];
			} while (i < len && b2 == -1);
			if (b2 == -1) {
				break;
			}
			buf.write((b1 << 2) | ((b2 & 0x30) >>> 4));

			/* b3 */
			do {
				b3 = data[i++];
				if (b3 == 61) {
					return buf.toByteArray();
				}
				b3 = base64DecodeChars[b3];
			} while (i < len && b3 == -1);
			if (b3 == -1) {
				break;
			}
			buf.write(((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2));

			/* b4 */
			do {
				b4 = data[i++];
				if (b4 == 61) {
					return buf.toByteArray();
				}
				b4 = base64DecodeChars[b4];
			} while (i < len && b4 == -1);
			if (b4 == -1) {
				break;
			}
			buf.write(((b3 & 0x03) << 6) | b4);
		}
		return buf.toByteArray();
	}

}
