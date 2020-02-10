package com.study.rsa;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.provider.MD5;

public abstract class Coder {
	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";
	
	public static final String key_mac = "hmacMD5";
	
	/**
	 * BASE64解密
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static byte[] decryptBASE64(String key) throws IOException {
		return new BASE64Decoder().decodeBuffer(key);
	}
	
	/**
	 * BASE64加密
	 * @param key
	 * @return
	 */
	public static String encryptBASE64(byte[] key) {
		return new BASE64Encoder().encodeBuffer(key);
	}
	
	/**
	 * MD5 加密
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encryptMD5(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}
	
	/**
	 * SHA 加密
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encryptSHA(byte [] data) throws NoSuchAlgorithmException {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}
	/**
	 * 初始化HMAC密钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String initMacKey() throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(key_mac);
		SecretKey secretKey = generator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}
	
	public static byte[] encryptHMAC(byte[] data, String key) throws IOException, NoSuchAlgorithmException, InvalidKeyException{
		SecretKey secretKey =  new SecretKeySpec(decryptBASE64(key), key_mac);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}

}
