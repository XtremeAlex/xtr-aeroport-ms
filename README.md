# xtr-aeroport-ms

Microservizio Spring Boot per l'accesso alle informazioni su aeroporti e rotte aeree, con supporto alla compilazione nativa GraalVM.

## Stack tecnologico

- Java 17 (GraalVM)
- Spring Boot 3.2.1
- GraalVM native image
- Maven

## Build ed esecuzione

```bash
# build JVM
./mvnw clean package -DskipTests
java -jar ./target/aeroport-*.jar

# build nativa (GraalVM)
./mvnw package -DskipTests -Pnative
./target/aeroport
```

## Contesto

Fa parte della suite `xtr-aeroport-*`, un esempio di architettura a microservizi:

- `xtr-aeroport-ms` — questo microservizio
- `xtr-aeroport-batch` — import massivo dati (Spring Batch + GraalVM native)
- `xtr-aeroport-typological` — dati tipologici
- `xtr-aeroport-common-lib` — libreria condivisa

## License

Distribuito sotto licenza Apache 2.0. Vedi `LICENSE`.

## Contatti

Andrei Alexandru Dabija — [LinkedIn](https://www.linkedin.com/in/andrei-alexandru-dabija/) — [github.com/XtremeAlex](https://github.com/XtremeAlex)
