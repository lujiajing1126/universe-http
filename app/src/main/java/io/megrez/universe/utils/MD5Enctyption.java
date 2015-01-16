package io.megrez.universe.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by megrez on 15/1/16.
 */
public class MD5Enctyption {
  public static String getCipher(String plain) {
    MessageDigest messageDigest = null;

    try {
      messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.reset();
      messageDigest.update(plain.getBytes("UTF-8"));

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();

    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    byte[] byteArray = messageDigest.digest();

    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < byteArray.length; i++) {
      if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
        sb.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
      else
        sb.append(Integer.toHexString(0xFF & byteArray[i]));
    }
    return sb.toString().toUpperCase();
  }
}
