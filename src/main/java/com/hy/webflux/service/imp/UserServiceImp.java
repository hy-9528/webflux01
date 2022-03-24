package com.hy.webflux.service.imp;

import com.hy.webflux.pojo.User;
import com.hy.webflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    private Map<String,User> users = new HashMap<>();

    public UserServiceImp(){
        users.put("1",new User("hy","nan",18));
        users.put("2",new User("jack","nan",28));
        users.put("3",new User("mary","nv",29));
    }


    @Override
    public Mono<User> getUserById(String id) {
        User user = users.get(id);
        return Mono.justOrEmpty(user);
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(users.values());
    }

    @Override
    public Mono<Void> saveUser(Mono<User> userMono) {
        return userMono.doOnNext(person->{
            //向map集合中放值
            String id = users.size()+1+"";
            users.put(id,person);
        }).thenEmpty(Mono.empty());
    }
}
