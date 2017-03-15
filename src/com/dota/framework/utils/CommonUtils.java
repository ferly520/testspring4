package com.dota.framework.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

public class CommonUtils {
	
	private static final int BUFFER_SIZE=16*1024;
	
	public static String listToString(List<String> list){
		if(list==null||list.size()==0){
			return "";
		}
		
		String res="(";
		for (int i = 0; i < list.size(); i++) {
			String value=list.get(i);
			value="'"+value+"'";
			if(i<list.size()-1){
				res+=value+",";
			}else{
				res+=value;
			}			
		}
		
		return res+=")";
	}
	
	public static String getListValue(List<String> list){
		if(list==null||list.size()==0){
			return "";
		}
		String temp="";
		for (int i = 0; i < list.size(); i++) {
			if(i!=list.size()-1){
				temp+=list.get(i)+",";
			}else{
				temp+=list.get(i);
			}
		}
		
		return temp;
	}
	
	
	public static long ObjectTOLong(Object longObj)  
	{  
		if(longObj==null){
			return 0L;
		}
		
	    return Long.valueOf(String.valueOf(longObj));  
	}  
	
	/**
	 * 让字符串自增1 并保持2位长度
	 * @param temp 传入的参数
	 * @return
	 */
	public static String makeStr2(String temp){		
		Integer value=Integer.parseInt(temp);
		value=value+1;
		temp=""+value;
		
		return temp.length()==1?"0"+temp:temp;
	}
		
	
	/**
	 * 文件复制
	 * @param src
	 * @param dst
	 * @return
	 */
	public static File copyFile(File src, File dst){		
		InputStream in = null;
		OutputStream out = null;
		int length=0;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];			
			while ((length=in.read(buffer))!=-1) {
				out.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {				
					e.printStackTrace();
				}				
			}
		}
		
		return dst;
	}
		
	/**
	 * 生成验证码(发短信验证)
	 * @param charCount 位数
	 * @return
	 */
	public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }
	
	public static int randomInt(int from, int to) {
        Random r = new Random();
        int res=from + r.nextInt(to - from);
        return res;
	}	
	
	public static void main(String[] ages){
	  System.out.println(getRandNum(6));
	}
	
}
