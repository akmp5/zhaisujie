package com.ag.zhaisujie;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *    ValidUtil.java
 *     <p>
 *     ��֤������
 *     Copyright: Copyright(c) 2013 
 *     @author Gavin_Feng
 *     </p>
 * 
 */
public class ValidUtil {

	/**
	 * ��֤�����ַ�Ƿ���ȷ
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	/**
	 * ��֤�ֻ�����
	 * 
	 * @param mobiles
	 * @return [0-9]{5,9}
	 */
	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static boolean isNum(String number) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^[0-9]{5}$");
			Matcher m = p.matcher(number);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static void main(String[] args) {
		String phone = "13611716465";
		System.out.print(isMobileNO(phone));
	}
}
