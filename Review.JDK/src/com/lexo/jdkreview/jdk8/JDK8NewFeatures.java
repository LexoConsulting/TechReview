package com.lexo.jdkreview.jdk8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class JDK8NewFeatures {
    public void run() {
        this.testForEach();

        this.testDefaultAndStaticMethod();

        this.testLambdaExpressions();

        this.testSteamAPI();
    }

    private void testForEach() {
        //creating sample Collection
        List<Integer> myList = new ArrayList<>();
        for (int i = 0; i < 10; i++) myList.add(i);

        // traversing using Iterator
        Iterator<Integer> it = myList.iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            System.out.println("Iterator Value::" + i);
        }

        //traversing through forEach method of Iterable with anonymous class
        myList.forEach(new Consumer<Integer>() {
            public void accept(Integer t) {
                System.out.println("forEach anonymous class Value::" + t);
            }

        });

        //traversing with Consumer interface implementation
        MyConsumer action = new MyConsumer();
        myList.forEach(action);
    }

    private void testDefaultAndStaticMethod() {

    }

    private void testLambdaExpressions() {
        Runnable r1 = () -> {
            System.out.println("My Runnable");
        };

        Interface1 i1 = (s) -> System.out.println(s);
        i1.method1("abc");
    }

    private void testSteamAPI() {
        List<Integer> myList = new ArrayList<>();
        for(int i=0; i<100; i++) myList.add(i);

        //sequential stream
        Stream<Integer> sequentialStream = myList.stream();

        //parallel stream
        Stream<Integer> parallelStream = myList.parallelStream();

        //using lambda with Stream API, filter example
        Stream<Integer> highNums = parallelStream.filter(p -> p > 90);
        //using lambda in forEach
        highNums.forEach(p -> System.out.println("High Nums parallel="+p));

        Stream<Integer> highNumsSeq = sequentialStream.filter(p -> p > 90);
        highNumsSeq.forEach(p -> System.out.println("High Nums sequential="+p));
    }

    private void testTimeAPI() {

    }
}

//Consumer implementation that can be reused
class MyConsumer implements Consumer<Integer>{
    public void accept(Integer t) {
        System.out.println("Consumer impl Value::"+t);
    }
}

@FunctionalInterface
interface Interface1 {

    void method1(String str);

    default void log(String str) {
        System.out.println("I1 logging::" + str);
    }

    static void print(String str) {
        System.out.println("Printing " + str);
    }

    //trying to override Object method gives compile-time error as
    //"A default method cannot override a method from java.lang.Object"

    //	default String toString(){
    //		return "i1";
    //	}

}
