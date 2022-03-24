package com.hy.webflux.service;

import com.hy.webflux.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    //通过id得到用户信息
    Mono<User> getUserById(String id);

    //获得所有用户信息
    Flux<User> getAllUser();

    //保存用户信息
    Mono<Void> saveUser(Mono<User> userMono);
}
