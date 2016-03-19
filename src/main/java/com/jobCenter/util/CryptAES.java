package com.jobCenter.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Key;

/**
 * 描述：AES加密
 * 作者 ：kangzz
 * 日期 ：2016-03-19 10:26:36
 */
public class CryptAES {

	private static final String AESTYPE = "AES/ECB/PKCS5Padding";

	/**
	 * 描述：AES加密
	 * @param  keyStr 密钥
	 * @param  plainText 加密数据
	 * @return String 加密完数据
	 * 作者 ：kangzz
	 * 日期 ：2016-03-19 10:26:54
	 */
	public static String AES_Encrypt(String keyStr, String plainText) {
		byte[] encrypt = null;
		try {
			Key key = generateKey(keyStr);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypt = cipher.doFinal(plainText.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(encrypt));
	}

	/**
	 * 描述：AES解密
	 * @param  keyStr 密钥
	 * @param  encryptData 解密数据
	 * @return 解密后字符串
	 * 作者 ：kangzz
	 * 日期 ：2016-03-19 10:27:28
	 */
	public static String AES_Decrypt(String keyStr, String encryptData) {
		byte[] decrypt = null;
		try {
			Key key = generateKey(keyStr);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decrypt = cipher
					.doFinal(Base64.decodeBase64(encryptData.getBytes()));
		} catch (Exception e) {
			;
		}
		return new String(decrypt).trim();
	}

	/**
	 * @Description: 封装KEY值
	 * @param key
	 * @param @throws Exception   
	 * @return Key  
	 * @throws
	 * @author liuxm
	 * @date 2014年11月21日
	 */
	private static Key generateKey(String key) throws Exception {
		
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			return keySpec;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		String keyStr = "8w091ql5l2tt6qxj";
		System.out.println(keyStr.length());
		String plainText = "this is a string will be AES_Encrypt";
		// 测试获取账户接口
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("uid", "c1543e4d-41df-adf4-0a73-03ad9b50c338");
		String encText = AES_Encrypt(keyStr, jsonObj.toString());
		String a  =  URLDecoder.decode("%2Bqsab4SEEfdBA68%2Bdl8nXp5e2hx5pmiULuMP%2FbQ3JBYQPLzbKvF9QNunmG0jfoY0", "UTF-8");
		System.out.println("--a--"+a);
		String decString = AES_Decrypt(keyStr, a);
		System.out.println(jsonObj.toString());
		System.out.println(encText+"---");
		//String decString1 = AES_Decrypt("fc53791c2bf8b8ce8204065dcf3ee7ca", b);
		System.out.println(decString);
		//System.out.println(decString1);
		//String decString2 = AES_Decrypt("8w091ql5l2tt6qxj","Y2BQbLteTAqJVSNeevY%2BL2xu6z5xRVCsuwo1Bcd%2BBC4%2Bc4ssmKhKDja%2FIt8Ebx7hvRTNjq4CROyGx6Er8iU%2BsMVMWOapSVUSdelvuQW5Gad4ztN%2B0RisUoFzQ9lPSlOKUQgoWEf6Ve%2Bh%2BhlmChr7T67JYUi87IZAaRx7Y0P%2FI3YehAU5tVeTyNex3AgftnOA5YBEvSTxyNFhQ94uZqCwuA%3D%3D");
		System.out.println(AES_Decrypt("8w091ql5l2tt6qxj",URLDecoder.decode("Y2BQbLteTAqJVSNeevY%2BL2xu6z5xRVCsuwo1Bcd%2BBC4%2Bc4ssmKhKDja%2FIt8Ebx7hvRTNjq4CROyGx6Er8iU%2BsMVMWOapSVUSdelvuQW5Gad4ztN%2B0RisUoFzQ9lPSlOKUQgoWEf6Ve%2Bh%2BhlmChr7T67JYUi87IZAaRx7Y0P%2FI3YehAU5tVeTyNex3AgftnOA5YBEvSTxyNFhQ94uZqCwuA%3D%3D","UTF-8")));	

	//	System.out.println(decString2);

	}
}