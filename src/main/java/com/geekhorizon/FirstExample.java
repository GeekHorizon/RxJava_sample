package com.geekhorizon;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * RxJava 예제
 * @author iris
 *
 */
public class FirstExample {
	public void emit() {
		Observable.just("Hello", "RxJava 2!!").subscribe(System.out::println);
	}
	
	public static void main(String args[]) {
		FirstExample demo = new FirstExample();
		demo.emit();
		
		flatMap();
		
		reduce();
		
		filter();
	}
	
	public static void flatMap() {
		int dan = 3;
		Observable<String> source = Observable.just(dan)
				.flatMap(gugu -> Observable.range(1, 9),
						(gugu, i) -> gugu + " * " + i + " = " + gugu * i);
		source.subscribe(System.out::println);
	}
	
	public static void reduce() {
		String[] balls = {"1", "3", "5"};
		Maybe<String> source =  Observable.fromArray(balls).reduce((ball1, ball2) -> ball2 + "(" +ball1 + ")");
		source.subscribe(System.out::println);
	}
	
	public static void filter() {
		List<Pair<String, Integer>> sales = new ArrayList<>();
		sales.add(Pair.of("TV", 2000));
		sales.add(Pair.of("Camera", 1000));
		sales.add(Pair.of("TV", 3000));
		sales.add(Pair.of("Phone", 900));
		sales.add(Pair.of("TV", 2000));
		
		Observable<Pair<String, Integer>> source = Observable.fromIterable(sales).filter(sale -> StringUtils.equals("TV", sale.getLeft()));
		
		source.subscribe(System.out::println);
		
	}
}