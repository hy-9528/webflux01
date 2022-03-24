package com.hy.webflux.observer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReactorDemo {

    public static void main(String[] args) {

        //通过just方法直接声明,通过subscribe方法进行订阅
        //如果不订阅的话，不会返回数据
        Flux.just(1,2,3,5).subscribe(System.out::print);
        Mono.just(1);

        //通过其他方法声明
        //1.声明数组
        Integer[] array = new Integer[]{6,7,8};
        Flux.fromArray(array);

        //2.声明集合
        List<Integer> list = Arrays.asList(1,2,4);
        Flux.fromIterable(list);

        //3.声明流
        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);


    }
}
