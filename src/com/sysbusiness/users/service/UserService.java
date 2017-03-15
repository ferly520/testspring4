package com.sysbusiness.users.service;

import java.io.UnsupportedEncodingException;

public class UserService {

	public static void main(String[] ages){
		 String ss = "\uA8A6\uA3BF\uA3BF\uA3BF\uA1C0\uA3BF\uA3BF\uA3BF\uA3BF\uA3BF\uA3BF\uA3BF";
		 String s = "\u5F20\u4E09";
		 String dd = "¨¦£¿£¿£¿¡À£¿£¿£¿£¿£¿£¿£¿";
		 
		try {
			System.out.println(new String(new String(ss.getBytes("GB2312"),"UTF-8")));
			System.out.println(new String(new String(dd.getBytes("GB2312"),"UTF-8")));
			System.out.println(new String(new String(ss.getBytes("GB2312"),"GBK").getBytes("GBK"),"UTF-8"));
			System.out.println(new String(new String(dd.getBytes("GB2312"),"GBK").getBytes("GBK"),"UTF-8"));
			} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
