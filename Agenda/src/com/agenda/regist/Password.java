package com.agenda.regist;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Password {

	public static void main(String[] args) {
		String salt = generateSalt();
		String pw = getEncrypt("dfㅓdf	ㅇㄹ!!", salt);
		System.out.println("salt 암호화 값은 : "+ salt +" 입니다");
		System.out.println("SHA256 암호화 값은 : " + pw +" 입니다");
	}
		public static String getEncrypt(String source, String salt) {
		String result = "";
		
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt.getBytes());
			md.update(source.getBytes());
			result = String.format("%064x", new BigInteger(1, md.digest()));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("SHA256 암호화 오류");
			e.printStackTrace();
		}
		
		
		return result;
	}

	public static String generateSalt() {
		SecureRandom sc;
		String salt = "";
		try {
			sc = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			sc.nextBytes(bytes);
			salt = new String(Base64.getEncoder().encode(bytes));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("salt생성 오류");
			e.printStackTrace();
		}
		return salt;
		
	}
	
}


	