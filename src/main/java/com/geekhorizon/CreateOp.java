package com.geekhorizon;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import com.geekhorizon.common.Log;

import io.reactivex.Observable;

public class CreateOp {

	public static void main(String[] args) throws InterruptedException {
		interval();
		
		timer();
		
		range();
		
		intervalRange();
		
		defer();
		
		noDefer();
		
		repeat();
	}

	public static void interval() throws InterruptedException {

		Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS).map(data -> (data + 1) * 100)
				.take(5);

		source.subscribe(Log::it);

		Thread.sleep(1000);

		Log.it("END");
	}

	public static void timer() throws InterruptedException {
		Observable<String> source = Observable.timer(100L, TimeUnit.MILLISECONDS)
				.map(notUsed -> {
					return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
				});
		
		source.subscribe(Log::it);
		Thread.sleep(1000);
		Log.it("END");
	}
	
	public static void range() throws InterruptedException {
		Observable<Integer> source = Observable.range(2, 10).filter(number -> (number & 1) == 0);
		
		source.subscribe(Log::it);
		Log.it("END");
	}
	
	public static void intervalRange() throws InterruptedException {
		Observable<Long> source = Observable.intervalRange(2, 10, 0, 200, TimeUnit.MILLISECONDS).filter(number -> (number & 1) == 0);
		
		source.subscribe(Log::it);
		Thread.sleep(5000);
		Log.it("END");
	}
	
	
	public static void defer() throws InterruptedException {
		
		Log.it("DEFER START");
		
		CreateOp obj = new CreateOp();
		Observable<String> source = Observable.defer(() -> obj.getObservable());
		source.subscribe(val -> Log.it("subscribe #1:" + val));
		source.subscribe(val -> Log.it("subscribe #2:" + val));
		
		Log.it("DEFER END");
	}

	public static void noDefer() throws InterruptedException {
		Log.it("NODEFER START");
		
		CreateOp obj = new CreateOp();
		
		Observable<String> source = obj.getObservable();
		source.subscribe(val -> Log.it("subscribe #1:" + val));
		source.subscribe(val -> Log.it("subscribe #2:" + val));
		
		Log.it("NODEFER END");
	}
	
	
	private Iterator<String> numbers = Arrays.asList("1", "2", "3", "4", "5").iterator();
	
	
	
	
	public Observable<String> getObservable() {
		
		if (numbers.hasNext()) {
			
			String number = numbers.next();
			
			return Observable.just(number, number, number);
		}
		
		return Observable.empty();
	}
	
	
	
	

	public static void repeat() throws InterruptedException {
	
		
		Observable.timer(2, TimeUnit.SECONDS).map(val -> "val :: " + val)
 		.repeat()
		.subscribe(Log::it);
		
		
		Thread.sleep(10000);
		
		
		
	}
	
	
}
