package com.geekhorizon;

import java.util.concurrent.TimeUnit;

import com.geekhorizon.common.Log;

import io.reactivex.Observable;

public class CreateOp {
 
	public static void main(String[] args) throws InterruptedException {
		interval();
	}
	
	
	public static void interval() throws InterruptedException {
		
		Observable<Long> source = Observable.interval(100L, TimeUnit.MICROSECONDS)
			.map(data -> (data + 1) * 100)
			.take(5);
		
		source.subscribe(Log::it);
		
		//Thread.sleep(1000);
		
		Log.it("END");
	}
	
	
	public static void timer() {
		
		
	}
}
