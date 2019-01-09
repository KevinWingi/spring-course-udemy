package com.springcourse.service.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

	public static String getSecureHash(String text) {
		String hash = DigestUtils.sha256Hex(text);
		return hash;
	}
}
