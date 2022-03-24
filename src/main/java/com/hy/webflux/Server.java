package com.hy.webflux;

import com.hy.webflux.handler.UserHandler;
import com.hy.webflux.service.UserService;
import com.hy.webflux.service.imp.UserServiceImp;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

public class Server {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }

    public RouterFunction<ServerResponse> routerFunction(){
        //创建Handler对象
        UserService userService = new UserServiceImp();
        UserHandler userHandler = new UserHandler(userService);

        //设置路由
        return RouterFunctions
                .route(GET("/users/{id}")
                        .and(accept(APPLICATION_JSON)),userHandler::getUserById)
                .andRoute(GET("/users")
                        .and(accept(APPLICATION_JSON)),userHandler::getAllUser);
    }


    //创建服务器完成适配
    public void createReactorServer(){
        //路由和Handler适配
        RouterFunction<ServerResponse> router = routerFunction();
        HttpHandler httpHandler = toHttpHandler(router);

        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        //创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }


}
