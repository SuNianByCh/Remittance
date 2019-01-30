package com.yaer.remittance.utils;


/*
public class DES2 {

	private static final String MCRYPT_TRIPLEDES = "DESede";
	private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";

    private static String key = "iufles8787rewjk1qkq9dj76";
	private static String iv = "vs0ld7w3";

	private static String shopKey = "4KEwGUxXKMysVAyzGfngyi3N";
	private static String shopIv = "3RdgZwfH";
	//ios
//	private static String key = "iufles8787rewjk1qkq9dj72";
//	private static String iv = "vs0ld7w1";

	*/
/**
	 * 解密
	 * 
	 *//*

	public static String decrypt(String data) {
		try {
			DESedeKeySpec spec = new DESedeKeySpec(*/
/*key*//*
BuildConfig.URL_KEY.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
			SecretKey sec = keyFactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			IvParameterSpec IvParameters = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, sec, IvParameters);
			return new String(cipher.doFinal(Base64.decode(data)), "UTF-8");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return "";
	}

	*/
/**
	 * 解密
	 * 
	 *//*

	public static String shopDecrypt(String data) {
		try {
			DESedeKeySpec spec = new DESedeKeySpec(shopKey.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
			SecretKey sec = keyFactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			IvParameterSpec IvParameters = new IvParameterSpec(shopIv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, sec, IvParameters);
			return new String(cipher.doFinal(Base64.decode(data)), "UTF-8");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return "";
	}

	*/
/**
	 * 加密
	 * 
	 *//*

	public static String encrypt(String data) {
		try {
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
			SecretKey sec = keyFactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		//	IvParameterSpec IvParameters = new IvParameterSpec(BuildConfig.URL_IV.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, sec, IvParameters);
			return Base64.encode(cipher.doFinal(data.getBytes()));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	*/
/**
	 * 加密
	 * 
	 *//*

	public static String shopEncrypt(String data) {
		try {
			DESedeKeySpec spec = new DESedeKeySpec(shopKey.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
			SecretKey sec = keyFactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			IvParameterSpec IvParameters = new IvParameterSpec(shopIv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, sec, IvParameters);
			return Base64.encode(cipher.doFinal(data.getBytes()));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return "";
	}

}*/
