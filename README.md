# Simple Rock/Paper/Scissors implementation

This is simple rock/paper/scissors game implementation. It uses Markov chains as an statistical prediction model.

## Technology stack

* Java 11
* Spring Boot
* Spring REST Docs
* Spring Shell for command line interface
* Spring MVC used to provide REST API
* Maven is used as a build system

## Starting application

Application uses `spring-boot-maven-plugin`, so it can be simply started by invoking following command:

```
$ mvn spring-boot:run
```

As well JAR file can be executed directly:

```
$ java -jar rockpaperscissors-0.0.1-SNAPSHOT.jar
```

## Command line interface

Application uses `spring-shell` to provide command line interface. Please find available commands below.

```
AVAILABLE COMMANDS

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error.

Play Command
        play: Play with computer, requires item as input {ROCK|PAPER|SCISSORS}

Stats Command
        stats: Get statistics about played games
```

## REST API

It provides REST API which is documented using spring-rest-docs, documentation can be found under
following URL, after application start: http://localhost:8080/.
