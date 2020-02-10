package com.study.rsa;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public abstract class RSACoder extends Coder {
	public static final String KEY_ALGORITHM = "RSA";  
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";  
  
    private static final String PUBLIC_KEY = "RSAPublicKey";  
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    
    /**
     * 用私钥对信息生成数字签名
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
    		byte[] keyBytes = decryptBASE64(privateKey);
    		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
    		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    		
    		//取私钥对象
    		PrivateKey pkey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    		signature.initSign(pkey);
    		signature.update(data);
    		return encryptBASE64(signature.sign());
    }
    
    /**
     * 校验数字签名
     * @param data
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
    		byte[] keyBytes = decryptBASE64(publicKey);
    		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    		
    		PublicKey pKey = keyFactory.generatePublic(keySpec);
    		
    		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    		signature.initVerify(pKey);
    		signature.update(data);
    		return signature.verify(decryptBASE64(sign));
    }
    
    /**
     * 解密
     * 用私钥解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptPrivateKey(byte[] data, String key) throws Exception {
    		byte[] keyBytes = decryptBASE64(key);
    		//取得私钥
    		PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
    		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    		Key pKey = keyFactory.generatePrivate(encodedKeySpec);
    		//对数据解密
    		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    		cipher.init(Cipher.DECRYPT_MODE, pKey);
    		return cipher.doFinal(data);
    }
    
    /**
     * 用公钥解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey( byte[] data, String key) throws Exception{
    		//解密
    		byte[] keyBytes = decryptBASE64(key);
    		//取得公钥
    		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    		KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
    		Key pKey = factory.generatePublic(keySpec);
    		// 对数据解密
    		Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
    		cipher.init(Cipher.DECRYPT_MODE, pKey);
   		return cipher.doFinal(data);
    }
    
    /**
     * 用公钥加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey( byte[] data, String key) throws Exception{
         
        byte[] keyBytes = decryptBASE64(key);
         
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key pKey = keyFactory.generatePublic(keySpec);
         
         
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pKey);
         
        return cipher.doFinal(data);
    }
    
    /**
     * 用私钥加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception{
         
        byte[] keyBytes = decryptBASE64(key);
         
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(keySpec);
         
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
         
        return cipher.doFinal(data);
    }
    
    /**
     * 取得私钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey( Map<String, Object> keyMap) throws Exception{
 
        Key key = (Key) keyMap.get(PRIVATE_KEY);
         
        return encryptBASE64(key.getEncoded());
    }
    
    /**
     * 取得公钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey( Map<String, Object> keyMap) throws Exception{
 
        Key key = (Key) keyMap.get(PUBLIC_KEY);
         
        return encryptBASE64(key.getEncoded());
    }
    
    /**
     * 初始化密钥
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception{
         
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
         
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
         
        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
         
        Map<String, Object> keyMap = new HashMap(2);
        keyMap.put(PRIVATE_KEY, privateKey);
        keyMap.put(PUBLIC_KEY, publicKey);
        return keyMap;
    }
    
    
    

}
