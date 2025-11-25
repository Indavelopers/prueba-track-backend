package com.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    // Create a router
    Router router = Router.router(vertx);

    // Define a simple "Hello World" route
    router.get("/").handler(ctx -> {
      ctx.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    });

    // Create the HTTP server and listen on port 8080
    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080, http -> {
        if (http.succeeded()) {
          startPromise.complete();
          System.out.println("HTTP server started on http://localhost:8080");
        } else {
          startPromise.fail(http.cause());
        }
      });
  }
}