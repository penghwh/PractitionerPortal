package com.bailian.ta.util.httppost;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

	private static String ivValue = "";

	public static String encrypt(String seed, String cleartext, String iv) {
		ivValue = iv;
		byte[] rawKey;
		try {
			rawKey = getRawKey(seed.getBytes());
			byte[] result = encrypt(rawKey, cleartext.getBytes());
			String ivAToHex = toHex(iv.getBytes());
			String resultAToHex = toHex(result);
			return ivAToHex + resultAToHex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private static byte[] getRawKey(byte[] seed) throws Exception {
		byte[] afterSeed = new byte[2 * seed.length];
		for (int i = 0; i < seed.length; i++) {
			afterSeed[i] = seed[i];
		}
		for (int i = seed.length; i < afterSeed.length; i++) {
			afterSeed[i] = 0;
		}
		return afterSeed;
	}

	private static byte[] encrypt(byte[] raw, byte[] clear) {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

		byte[] encrypted = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();

			byte[] dataBytes = clear;
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

			IvParameterSpec iv = new IvParameterSpec(ivValue.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			encrypted = cipher.doFinal(plaintext);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encrypted;
	}

	public static String toHex(byte[] buf) {
		if (buf == null) {
			return "";
		}
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private final static String HEX = "0123456789ABCDEF";

	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}

	// 猫陆卢氓艗鈥撁ヂ€斆γぢ嘎裁ぢ嘎好ワ拷锟矫モ€β库€好ニ喡睹尖€撁狅拷
	public static String toHexString(String s) {
		String str = "";

		for (int i = 0; i < s.length(); i++) {
			int ch = s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return "0x" + str;// 0x猫隆篓莽陇潞氓锟斤拷氓鈥β库€好ニ喡?
	}

	// 猫陆卢忙锟铰⒚ワ拷锟矫モ€β库€好ニ喡睹尖€撁狅拷盲赂潞氓颅鈥斆γぢ嘎?
	public static String toStringHex(String s) {
		if ("0x".equals(s.substring(0, 2))) {
			s = s.substring(2);
		}
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
		}

		try {
			s = new String(baKeyword, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // UTF-16le:Not
		return s;
	}
}
