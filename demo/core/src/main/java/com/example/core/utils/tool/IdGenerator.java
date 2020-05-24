package com.example.core.utils.tool;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class IdGenerator {

    public static String[] chars = new String[]{    
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", 
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
            "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z"
    }; 
	
    /**
     * 获取短UUID，保证在百万数量级唯一，长度为 8 位，例子（tbN7ru6S，2B6orRJp）
     * 用途：短信验证码，重设的用户密码...等等，对长度比较敏感的 ID
     * @return 字符串型 短UUID
     */
    public static String getShortUuid(){ 
        StringBuffer stringBuffer = new StringBuffer(); 
        String uuid = UUID.randomUUID().toString().replace("-", ""); 
        for (int i = 0; i < 8; i++){
            String str = uuid.substring(i * 4, i * 4 + 4);
            int strInteger  = Integer.parseInt(str, 16); 
            stringBuffer.append(chars[strInteger % 0x3E]); //除62取余
        } 
        return stringBuffer.toString(); 
    }
    
	/**
	 * 获取 nano 时间因子作为时间ID，位数为 14 位数字 例子（89864091147334）
	 * 可以用作 数据库 中的 ID 字段
	 * @return 字符串型 ID
	 */
	public static String getShortTimeId() {
		String dateString = String.valueOf(System.nanoTime());
		return dateString;
	}
	
	/**
	 * 获取 “日期时分秒 + nano 时间因子” 作为时间ID，位数为 28 位数字 例子（2017011223042793189007681236）
	 * 可以用作 数据库 中的 ID 字段
	 * @return 字符串型 ID
	 */
	public static String getTimeId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = sdf.format(new Date());
		dateString += String.valueOf(System.nanoTime());
		return dateString;
	}
	
	/** 
	 * 获取标准的 uuid，长度为 32 位，例子（5d02334e-da48-4952-a168-ca19444efbe2）
	 * @return
	 */
	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return (uuid.toString());
	}
	
	/**
	 * 对字符串md5加密
	 *
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {
	    try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        return new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
	        return ""; 
	    }
	}

	/***
	 * 密码hash加密
	 * @param password
	 * @return
	 */
	public static String passwordToHash(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(password.getBytes());
			byte[] src = digest.digest();
			StringBuilder stringBuilder = new StringBuilder();
			// 字节数组转16进制字符串
			// https://my.oschina.net/u/347386/blog/182717
			for (byte aSrc : src) {
				String s = Integer.toHexString(aSrc & 0xFF);
				if (s.length() < 2) {
					stringBuilder.append('0');
				}
				stringBuilder.append(s);
			}
			return stringBuilder.toString();
		} catch (NoSuchAlgorithmException ignore) {
		}
		return null;
	}
	public static String getRandomPwd(){
	  Random rd = new Random();
//	  String n="";
	  int getNum;
	  /**
	  do {
	   getNum = Math.abs(rd.nextInt())%10 + 48;//产生数字0-9的随机数
	   //getNum = Math.abs(rd.nextInt())%26 + 97;//产生字母a-z的随机数
	   char num1 = (char)getNum;
	   String dn = Character.toString(num1);
	   n += dn;
	  } while (n.length()<6);
	   */
	  //改进效率
		StringBuffer buf = new StringBuffer();
		do {
//			getNum = Math.abs(rd.nextInt())%10 + 48;//产生数字0-9的随机数
			getNum = Math.abs(rd.nextInt()%10) + 48;//产生数字0-9的随机数
			//getNum = Math.abs(rd.nextInt())%26 + 97;//产生字母a-z的随机数
			char num1 = (char)getNum;
			String dn = Character.toString(num1);
			buf.append(dn);
		} while (buf.length()<6);
	  return buf.toString();
	 }
	
}
