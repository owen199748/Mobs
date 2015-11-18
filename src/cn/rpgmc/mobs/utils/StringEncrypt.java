package cn.rpgmc.mobs.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringEncrypt {

	
	public static String encrypt(String str, String sKey) {
      
            if (sKey.length() == 48) {  
                byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));  
                byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));  
                byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));  
  
                byte[] bytIn = str.getBytes();  
                // 加密  

			byte[] bytOut;
			try {
				bytOut = encryptByDES(
						encryptByDES(encryptByDES(bytIn, bytK1), bytK2), bytK3);
				return new String(bytOut);
			} catch (Exception e) {
				return null;
			}

            } else  return null;  

    }  
	
	
	public static String decrypt(String str, String sKey) {
	            if (sKey.length() == 48) {  
	                byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));  
	                byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));  
	                byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));  

	                byte[] bytIn = str.getBytes();   
			byte[] bytOut;
			try {
				bytOut = decryptByDES(
						decryptByDES(decryptByDES(bytIn, bytK3), bytK2), bytK1);
				return new String(bytOut);
			} catch (Exception e) {
				return null;
			}

	            }else return null;  
	        } 
	    
	  
	    /** 
	     * 用DES方法加密输入的字节 bytKey需为8字节长，是加密的密码 
	     */  
	private static byte[] encryptByDES(byte[] bytP, byte[] bytKey)
			throws Exception {
	        DESKeySpec desKS = new DESKeySpec(bytKey);  
	        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");  
	        SecretKey sk = skf.generateSecret(desKS);  
	        Cipher cip = Cipher.getInstance("DES");  
	        cip.init(Cipher.ENCRYPT_MODE, sk);  
	        return cip.doFinal(bytP);  
	    }  
	  
	    /** 
	     * 用DES方法解密输入的字节 bytKey需为8字节长，是解密的密码 
	     */  
	private static byte[] decryptByDES(byte[] bytE, byte[] bytKey)
			throws Exception {
	        DESKeySpec desKS = new DESKeySpec(bytKey);  
	        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");  
	        SecretKey sk = skf.generateSecret(desKS);  
	        Cipher cip = Cipher.getInstance("DES");  
	        cip.init(Cipher.DECRYPT_MODE, sk);  
	        return cip.doFinal(bytE);  
	    }  
	  
	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	// 解密
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	    /** 
	     * 输入密码的字符形式，返回字节数组形式。 如输入字符串：AD67EA2F3BE6E5AD 
	     * 返回字节数组：{173,103,234,47,59,230,229,173} 
	     */  
	private static byte[] getKeyByStr(String str) {
	        byte[] bRet = new byte[str.length() / 2];  
	        for (int i = 0; i < str.length() / 2; i++) {  
	            Integer itg = new Integer(16 * getChrInt(str.charAt(2 * i))  
	                    + getChrInt(str.charAt(2 * i + 1)));  
	            bRet[i] = itg.byteValue();  
	        }  
	        return bRet;  
	    }  
	  
	    /** 
	     * 计算一个16进制字符的10进制值 输入：0-F 
	     */  
	private static int getChrInt(char chr) {
	        int iRet = 0;  
	        if (chr == "0".charAt(0))  
	            iRet = 0;  
	        if (chr == "1".charAt(0))  
	            iRet = 1;  
	        if (chr == "2".charAt(0))  
	            iRet = 2;  
	        if (chr == "3".charAt(0))  
	            iRet = 3;  
	        if (chr == "4".charAt(0))  
	            iRet = 4;  
	        if (chr == "5".charAt(0))  
	            iRet = 5;  
	        if (chr == "6".charAt(0))  
	            iRet = 6;  
	        if (chr == "7".charAt(0))  
	            iRet = 7;  
	        if (chr == "8".charAt(0))  
	            iRet = 8;  
	        if (chr == "9".charAt(0))  
	            iRet = 9;  
	        if (chr == "A".charAt(0))  
	            iRet = 10;  
	        if (chr == "B".charAt(0))  
	            iRet = 11;  
	        if (chr == "C".charAt(0))  
	            iRet = 12;  
	        if (chr == "D".charAt(0))  
	            iRet = 13;  
	        if (chr == "E".charAt(0))  
	            iRet = 14;  
	        if (chr == "F".charAt(0))  
	            iRet = 15;  
	        return iRet;  
	    }  
	  
	public static String md5s(String plainText) {
	        String str = null;  
	        try {  
	            MessageDigest md = MessageDigest.getInstance("MD5");  
	            md.update(plainText.getBytes());  
	            byte b[] = md.digest();  
	  
	            int i;  
	  
	            StringBuffer buf = new StringBuffer("");  
	            for (int offset = 0; offset < b.length; offset++) {  
	                i = b[offset];  
	                if (i < 0)  
	                    i += 256;  
	                if (i < 16)  
	                    buf.append("0");  
	                buf.append(Integer.toHexString(i));  
	            }  
	            // System.out.println("result: " + buf.toString());// 32位的加密  
	            // System.out.println("result: " + buf.toString().substring(8,  
	            // 24));// 16位的加密  
	            str = buf.toString().substring(8, 24);  
	        } catch (NoSuchAlgorithmException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return str;  
	    }  
	  
	}  
	

