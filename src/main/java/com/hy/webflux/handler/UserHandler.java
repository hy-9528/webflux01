package com.hy.webflux.handler;

import com.hy.webflux.pojo.User;
import com.hy.webflux.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserHandler {

    private UserService userService;

    public UserHandler(UserService userService){
        this.userService = userService;
    }

    //通过id响应用户信息
    public Mono<ServerResponse> getUserById(ServerRequest request){
        String id = request.pathVariable("id");

        //id为空处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        Mono<User> userMono = userService.getUserById(id);
        //将userMono变成流的形式返回给客户端
        return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(userMono,User.class)
                            .switchIfEmpty(notFound);
    }

    //响应所有用户信息
    public Mono<ServerResponse> getAllUser(ServerRequest request){
        Flux<User> users = userService.getAllUser();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(users,User.class);
    }

    //添加
    public Mono<ServerResponse> saveUser(ServerRequest request){
        //得到user对象
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(userService.saveUser(userMono));
    }

}
