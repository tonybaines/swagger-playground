import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory
import io.vertx.core.http.HttpServerOptions


class Swagger : AbstractVerticle() {
    override fun stop(stopFuture: Future<Void>?) {
        OpenAPI3RouterFactory
                .createRouterFactoryFromFile(vertx, "src/main/resources/swagger.yaml", { ar ->
                    if (ar.succeeded()) {
                        // Spec loaded with success
                        val routerFactory = ar.result()

                        val router = routerFactory.getRouter()

                        val server = vertx.createHttpServer(
                                HttpServerOptions().setPort(8080).setHost("localhost"))
                        server.requestHandler(router::accept).listen()
                    } else {
                        // Something went wrong during router factory initialization
                        val exception = ar.cause()
                        exception.printStackTrace()
                    }
                })
    }


}