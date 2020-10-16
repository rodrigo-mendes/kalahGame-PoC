# Kalah Game
## Stack
* **Java**: 14
* **Gradle**: 6.6.1
* **Springboot - Webflux**: 2.3.4.RELEASE
* **JUnit**: 5

## How to execute
### from Command line
For start server execution open a terminal on root project directory
 * for linux: `gradlew bootRun`
 * for windows: `gradlew.bat bootRun`

### from IDE
Need to import gradle project on your favorite IDE, for start server you need to execute gradle task: `bootRun`

### Swagger-UI
After start server to access API documentation and for testing using swagger-ui: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Architecture
Driver for the architecture was microservices using `PORT & ADAPTER` approach, that was responsible for generate a component segregated from frameworks dependencies with all business logic, that could be easily moved to another framework.

### Architecture Decisions
* Using a inMemory repository for quick development, because there were any requisites for persistence information type.
* Use of springboot with webflux for reactive programming combine with Netty for better performance and lightweight server, was based on **SOLID** principles, more like for **LID** (Liskov substitution principle, Interface segregation principle, Dependency inversion principle ). 
* Using two layers RouteFunction and handler, was based on **SOLID** principles, more like for **S** (Single-responsibility principle)
* For cross concerns like logging, was used AOP (Aspects Oriented Programming), that way it's only one point to maintain for this technical need, and the code contain all business logic is clean from logging code.   

## Limitations
* There are no player's turn identification.
* There are no preference mechanism for change type of game from 3, 4 or 6 stones, but the game board was project to be evolved for these needs, because could be a future possible evolution 

## Quality

* Black box testing was creating to execute with external server running `com.rms.kalah.KalahApplicationTests` for integration test.
* White box testing was creating to execute internally without need from external server running: `com.rms.kalah.primaryAdapter.movement.MovementRouterTests` and `com.rms.kalah.primaryAdapter.game.GameRouterTests`

## Possible improvements
###Techinical
* JQAssistant could be used to enforce architecture decisions in all projects, just like ArchUnit.
* Could be using a distributed data structure like Redis or Hazelcast to scale and create a distributed version and could be embedded or external solution. 

## References
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [SOLID](https://en.wikipedia.org/wiki/SOLID)
* [JQAssistant](https://jqassistant.org/)
* [ArchUnit](https://www.archunit.org/)