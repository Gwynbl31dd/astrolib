# astrolib

Java library for astronomical calculations.

This project is a **pure Java library** (no executable entry point) built with Maven and Java 21.
It uses JPMS (Java modules), JUnit 5, Spotless, Checkstyle, and Maven Enforcer for build hygiene.

---

## Requirements

- Java **21+**
- Maven **3.9+**

Verify your environment:

```bash
java --version
mvn --version
```

## Day-to-day commands

Format code (recommended before commit)

Applies formatting to Java sources and pom.xml:

Run full local checks

Runs, in order:

* Maven Enforcer (Java/Maven versions, dependency sanity)

* Compilation (with warnings as errors)

* Unit tests

* Spotless format check

* Checkstyle rules

```bash
mvn clean verify
```

This is the command you want before pushing.

## Run tests only

```bash
mvn test
```

## Build the library JAR
```bash
mvn clean package
```

Artifacts produced in target/:
 
* astrolib-<version>.jar

* astrolib-<version>-sources.jar

* astrolib-<version>-javadoc.jar

## Install locally

Installs the library into your local Maven repository:

```bash
mvn clean install
```

You can then depend on it from another project:

```xml
<dependency>
  <groupId>space.darksitedb</groupId>
  <artifactId>astrolib</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

## Java Module

This library is a named Java module:

```java
module space.darksitedb.astrolib {
    exports space.darksitedb.astrolib;
}
```

Consumers using JPMS can depend on it directly.
Non-module consumers will use the Automatic-Module-Name defined in the JAR manifest.

## Versioning

This project follows semantic versioning:

MAJOR – breaking API changes

MINOR – backward-compatible features

PATCH – backward-compatible bug fixes

Until 1.0.0, APIs may change freely.

## License

TBD

ANOTHER CHANGE

THIS IS A CHANGE

A change by user A