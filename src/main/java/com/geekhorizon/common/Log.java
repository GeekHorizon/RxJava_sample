package com.geekhorizon.common;

public class Log {
	public static void it(Object obj) {
		
		System.out.println(Thread.currentThread().getName() + " | value " +obj);
	}
}
