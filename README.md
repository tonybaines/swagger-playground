# swagger-playground

Links
* https://github.com/OAI/OpenAPI-Specification/blob/master/IMPLEMENTATIONS.md
* http://vertx.io/blog/openapi-fka-swagger-3-support-in-eclipse-vert-x-now-in-test-stage/
* https://github.com/Mermade/openapi-gui
* https://github.com/int128/gradle-swagger-generator-plugin
* https://github.com/phiz71/vertx-swagger
* https://github.com/swagger-api/swagger-codegen/tree/master/samples/client/petstore/kotlin
* http://vertx.io/docs/vertx-web-api-contract/java/#_openapi_3_support

## IntelliJ IDEA

The 'Swagger Plugin' help to edit the `.json` file

## Gradle tasks

Uses https://github.com/int128/gradle-swagger-generator-plugin

Produce docs, client libs for Java, Kotlin, Python, Bash(!) and Vert.x server code (in Java)

    ./gradlew generateSwaggerCode

Generate Swagger UI dynamic HTML

    ./gradlew generateSwaggerUIDocs
