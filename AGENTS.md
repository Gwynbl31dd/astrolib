# Astrolib Agent Instructions

A **pure Java 21 library** for astronomical calculations (unit conversions, orbital mechanics, sidereal time). No runtime dependencies — JUnit 5 test-only. Published to local Maven repo and consumed by other projects.

- **GroupId / ArtifactId**: `space.darksitedb:astrolib`
- **JPMS module**: `space.darksitedb.astrolib`

## Build & Test

```bash
mvn clean verify          # Full check: compile, test, checkstyle, JaCoCo coverage gate
mvn test                  # Tests only
mvn clean install         # Build + install to local Maven repo
mvn spotless:apply        # Auto-format code (run before committing)
```

## Package Layout

```
space.darksitedb.astrolib/
├── Orbit.java            # Orbital mechanics
└── units/
    ├── Unit.java         # Base interface
    ├── angle/            # Degree, Radian, ArcMinute, ArcSecond, Dms
    ├── length/           # Au, Parsec, LightYear, Meter, Kilometer, …
    ├── temperature/      # DegreeCelsius, DegreeFahrenheit, DegreeKelvin
    └── time/             # JulianDate, GST, LST, LCT, UT, Hms, Hour, …
```

## Quality Gates (Enforced — Build Will Fail)

- **Checkstyle**: severity `error` — max 120-char lines, `NeedBraces`, naming conventions. Config: [config/checkstyle/checkstyle.xml](config/checkstyle/checkstyle.xml)
- **JaCoCo**: 80% instruction coverage minimum (`verify` phase)
- **Compiler**: `-Xlint:all -Werror` — all warnings are errors
- **Spotless**: formatter; run `mvn spotless:apply` to fix before `verify`
- **Enforcer**: Java 21+ and Maven 3.8+ required

## Conventions

- No runtime dependencies — keep it that way
- All new unit classes implement or extend from `units/` structure
- Tests mirror src layout (e.g., `TimeTest.java` covers `time/` package)
- `module-info.java` is excluded from Checkstyle
