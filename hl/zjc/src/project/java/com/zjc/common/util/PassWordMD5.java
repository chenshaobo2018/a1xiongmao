/**
 * 
 */
package com.zjc.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;



/**
 * @author Administrator
 *
 */
public class PassWordMD5 {

	 private static String byteArrayToHexString(byte b[]) {
	        StringBuffer resultSb = new StringBuffer();
	        for (int i = 0; i < b.length; i++)
	            resultSb.append(byteToHexString(b[i]));

	        return resultSb.toString();
	    }

	    private static String byteToHexString(byte b) {
	        int n = b;
	        if (n < 0)
	            n += 256;
	        int d1 = n / 16;
	        int d2 = n % 16;
	        return hexDigits[d1] + hexDigits[d2];
	    }

	    public static String MD5Encode(String origin, String charsetname) {
	        String resultString = null;
	        try {
	            resultString = new String(origin);
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            if (charsetname == null || "".equals(charsetname))
	                resultString = byteArrayToHexString(md.digest(resultString
	                        .getBytes()));
	            else
	                resultString = byteArrayToHexString(md.digest(resultString
	                        .getBytes(charsetname)));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultString;
	    }


	    public static String MD5Encode(String origin) {
	        return MD5Encode(origin, "UTF-8");
	    }

	    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
	            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


	    @SuppressWarnings("resource")
		public static String getMD5Encode(File file) {
	        String resultString = null;
	        try {
	            FileInputStream inputStream = new FileInputStream(file);
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] buffer = new byte[512];
	            int length = -1;
	            while ((length = inputStream.read(buffer)) != -1) {
	                md.update(buffer, 0, length);
	            }
	            byte[] b = md.digest();
	            resultString = byteArrayToHexString(b).toUpperCase();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultString;

	    }
}
