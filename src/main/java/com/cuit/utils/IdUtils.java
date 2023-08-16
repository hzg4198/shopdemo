package com.cuit.utils;

import java.util.UUID;

public class IdUtils {
	public static String getUid(){
		return UUID.randomUUID().toString();
	}
}
