# IS216.023 

## Requirements

For building and running the application you need:

- [Node 20 & Npm 10](https://nodejs.org/en/download)
- [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [Maven 3](https://maven.apache.org)

## Run the application locally

``` bash
npm install
npm run prepare
```

``` bash
mvn spring-boot:run
```

use a browser to navigate to http://localhost:8080/swagger-ui/index.html

## Other commands

### Format code

``` bash
mvn fmt:format
```

## How to name commit message? 

**Commitlint** checks if your commit messages meet the [conventional commit format](https://conventionalcommits.org).

Commit message pattern:

```sh
type(scope?): subject  #scope is optional; multiple scopes are supported (current delimiter options: "/", "\" and ",")
```

Examples:

```text
chore: run tests on travis ci
```

```text
fix(server): send cors headers
```

```text
feat(blog): add comment section
```

Common types according to [commitlint-config-conventional (based on the Angular convention)](https://github.com/conventional-changelog/commitlint/tree/master/@commitlint/config-conventional#type-enum) can be:

- build
- chore
- ci
- docs
- feat
- fix
- perf
- refactor
- revert
- style
- test

## References

Read these references if needed:

- Open api swagger: https://springdoc.org/
- Lombok: https://codippa.com/lombok/
