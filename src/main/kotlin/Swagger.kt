import com.google.gson.GsonBuilder
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory.createRouterFactoryFromFile




data class User(val id: String, val name: String)
val USERS = (1 until 5).map { it.toString() }.map { User(it, "user-$it") }

class Swagger : AbstractVerticle() {
    override fun start(stopFuture: Future<Void>?) {
        createRouterFactoryFromFile(vertx, "src/main/resources/swagger.yaml", { ar ->
            if (ar.succeeded()) {
                // Spec loaded with success
                val routerFactory = ar.result()
                val gson = GsonBuilder().setPrettyPrinting().create()

                routerFactory.addHandlerByOperationId("users") { event ->

                    event.response()
                            .putHeader("content-type", "application/json")
                            .setChunked(true)
                            .write(gson.toJson(USERS))
                            .end()
                }
                routerFactory.addFailureHandlerByOperationId("users") { event ->
                    event.failure().printStackTrace()
                }

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