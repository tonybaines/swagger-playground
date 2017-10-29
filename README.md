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

## Thoughts on versioning and discovery

* Semantic versioning
  * **major**.**minor**.**patch**
  * **patch** for bug fixes
  * **minor** for backward-compatible changes (additions)
  * **major** for backward-incompatible changes
* Only expose major version in API
* Root level API with discovery
  * version-specific info at eg. `https://host:port/api/v2`
  * `-SNAPSHOT` suffix during dev
  * HTML versions for humans (Swagger Docs, CHANGELOG)
```
GET: https://host:port/api 
Accept: application/json
{
  "latest-stable": {
    "href" : https://host:port/api/v2",
    "content-type": "application/json"
  },
  "versions": {
    "v1": {
      "latest" : false,
      "released" : true,
      "links": {
        "users": {
          "href": "https://host:port/api/v1/users",
          "content-type": "application/json"
        },
        "profiles": {
          "href": "https://host:port/api/v1/profiles",
          "content-type": "application/json"
        },
        "docs": {
          "href": "https://host:port/api/v1/docs",
          "content-type": "text/html"
        }
      }
    },
    "v2": {
      "latest" : true,
      "released" : true,
      "links": {
        "users": {
          "href": "https://host:port/api/v2/users",
          "content-type": "application/json"
        },
        "profiles": {
          "href": "https://host:port/api/v2/profiles",
          "content-type": "application/json"
        },
        "docs": {
          "href": "https://host:port/api/v2/docs",
          "content-type": "text/html"
        }
      }
    },
    "v3-SNAPSHOT": {
      "latest" : false,
      "released" : false,
      "links": {
        "users": {
          "href": "https://host:port/api/v3/users",
          "content-type": "application/json"
        },
        "profiles": {
          "href": "https://host:port/api/v3/profiles",
          "content-type": "application/json"
        },
        "events": {
          "href": "https://host:port/api/v3/events",
          "content-type": "application/json"
        },
        "docs": {
          "href": "https://host:port/api/v3/docs",
          "content-type": "text/html"
        }
      }
    }
  }
}
```