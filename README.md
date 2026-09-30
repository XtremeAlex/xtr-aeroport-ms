> Stato: deprecato dal 29/09/2026. Questo progetto non è più mantenuto.
> Al suo posto c'è `xtr-aeroport-api-spring`, l'API unica della suite (non ancora pubblicata su GitHub), che ha assorbito anche `xtr-aeroport-typology`.
> Il codice resta qui per chi vuole consultarlo, ma non riceverà più correzioni, aggiornamenti di sicurezza o nuove release.

<a name="readme-top"></a>

<br />
<div align="center">
  <img src="_assets/images/banner-dark.png" alt="Aeroport MS" width="100%">
  <br /><br />
  <img src="_assets/images/logo.png" width="300" alt="Logo">

  <h3 align="center">Aeroport MS</h3>

  <p align="center">
    Il microservizio per cercare informazioni sugli aeroporti di tutto il mondo.
    <br />
    <a href="https://github.com/XtremeAlex/xtr-aeroport-ms"><strong>Esplora la documentazione &raquo;</strong></a>
    <br />
    <br />
    <a href="https://github.com/XtremeAlex/xtr-aeroport-ms/issues">Segnala un bug</a>
    &middot;
    <a href="https://github.com/XtremeAlex/xtr-aeroport-ms/issues">Richiedi una feature</a>
  </p>
</div>

<details>
  <summary>Sommario</summary>
  <ol>
    <li>
      <a href="#perché-esiste">Perché esiste</a>
      <ul>
        <li><a href="#la-suite">La suite</a></li>
        <li><a href="#invito-alla-collaborazione">Invito alla collaborazione</a></li>
        <li><a href="#stack-tecnologico">Stack tecnologico</a></li>
      </ul>
    </li>
    <li>
      <a href="#per-iniziare">Per iniziare</a>
      <ul>
        <li><a href="#cosa-serve">Cosa serve</a></li>
        <li><a href="#struttura-del-progetto">Struttura del progetto</a></li>
        <li><a href="#compilazione">Compilazione</a></li>
      </ul>
    </li>
    <li><a href="#api">API</a></li>
    <li><a href="#play--test">Play &amp; Test</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#come-contribuire">Come contribuire</a></li>
    <li><a href="#licenza">Licenza</a></li>
    <li><a href="#contatti">Contatti</a></li>
    <li><a href="#ringraziamenti">Ringraziamenti</a></li>
  </ol>
</details>

## Perché esiste

`xtr-aeroport-ms` espone API REST per cercare aeroporti in tutto il mondo. I filtri (tipologia, paese, nome) si possono combinare, e ci sono paginazione e ordinamento. È nato come banco di prova personale per pattern e tecnologie recenti (microservizi, cloud-native, GraalVM native) su un caso concreto, ma l'ho impostato come un progetto aziendale: scalabile, sicuro, facile da mantenere.

Perché l'ho deprecato: per le tipologie chiamava `xtr-aeroport-typology` via Feign. Voleva dire due JVM, una chiamata di rete in più e più cose che si potevano rompere. Sul Raspberry Pi, dove la suite deve girare, era troppo. I due servizi sono diventati uno solo, `xtr-aeroport-api-spring`, che usa un database SQLite di sola lettura al posto di PostgreSQL.

### La suite

| Modulo | A cosa serve | Stato |
|---|---|---|
| `xtr-aeroport-api-spring` | API unica per aeroporti, tipologie, paesi e messaggi EDIFACT (non ancora pubblicata su GitHub) | Attivo |
| `xtr-aeroport-api-quarkus` | Porting della stessa API su Quarkus (non ancora pubblicato su GitHub) | Sperimentale |
| `xtr-aeroport-edifact-spring-web` | Console web EDIFACT, ha preso il posto di `xtr-aeroport-web-java` (non ancora pubblicata su GitHub) | Attivo |
| [`xtr-aeroport-batch`](https://github.com/XtremeAlex/xtr-aeroport-batch) | Import massivo dei dati | Attivo, offline |
| [`xtr-aeroport-common-lib`](https://github.com/XtremeAlex/xtr-aeroport-common-lib) | Libreria condivisa | Legacy |
| [`xtr-aeroport-ms`](https://github.com/XtremeAlex/xtr-aeroport-ms) | Microservizio di ricerca aeroporti (questo modulo) | Deprecato |
| [`xtr-aeroport-typology`](https://github.com/XtremeAlex/xtr-aeroport-typology) | Servizio dati tipologici | Deprecato |
| [`xtr-aeroport-web-java`](https://github.com/XtremeAlex/xtr-aeroport-web-java) | Frontend web | Deprecato |

### Invito alla collaborazione

Quando il progetto era vivo, l'invito era aperto a idee, codice e feedback. L'idea era:

- **sperimentare** tecnologie e pattern recenti su un progetto vero;
- **crescere insieme**, scambiandosi idee e imparando gli uni dagli altri;
- **avere una base** già impostata secondo le buone pratiche da cui partire per i propri progetti.

Oggi quell'invito vale per `xtr-aeroport-api-spring`.

### Stack tecnologico

- Java 17 (GraalVM)
- Spring Boot 3.2.1 (Web, Data JPA, Actuator)
- Spring Cloud OpenFeign per la comunicazione tra servizi
- PostgreSQL, HikariCP
- MapStruct, Lombok
- springdoc-openapi (Swagger UI)
- Micrometer Tracing + OpenTelemetry / Zipkin
- Docker, Helm / Kubernetes
- Gira su Linux, macOS e Windows

<p align="right">(<a href="#readme-top">torna su</a>)</p>

## Per iniziare
Si compila con Maven, su Spring Boot 3 e Java 17, e si avvia senza problemi in locale.

### Cosa serve

- Git (>= 2.43)
- GraalVM JDK 17 per la build nativa, oppure un qualsiasi JDK 17 per la build JVM
- Maven (>= 3.9.6), oppure il wrapper `./mvnw` già incluso
- Docker, per il database e per le build in container
- [`xtr-aeroport-typology`](https://github.com/XtremeAlex/xtr-aeroport-typology) in esecuzione, se ti servono gli endpoint delle tipologie (passano da Feign)

### Coordinate del progetto

| Proprietà | Valore |
|---|---|
| groupId | `com.xtremealex` |
| artifactId | `aeroport` |
| version | `3.2.0` |
| main class | `com.xtremealex.aeroport.AeroportApplication` |

### Struttura del progetto

```
src/main/java/com/xtremealex/aeroport
├── AeroportApplication.java        avvio Spring Boot (+ @EnableFeignClients)
├── configuration
│   ├── MyConfig.java               bean applicativi (ResponseWrapperBuilder)
│   ├── MyConfigMvc.java            MVC, CORS, interceptor
│   └── MySwaggerConfig.java        OpenAPI / Swagger UI
├── controller
│   ├── SearchAirport.java          ricerca aeroporti (filtri, paginazione)
│   ├── SearchAirportType.java      proxy tipologie (via Feign)
│   ├── SearchFlights.java          ricerca voli (placeholder)
│   └── TestController.java         endpoint di test
├── feign
│   └── SearchAirportTypeFeignClient.java   client verso il servizio typological
├── interceptors
│   └── MyLoggingInterceptor.java   logging delle chiamate ai controller
└── service
    ├── IAirportService.java        contratto del servizio di ricerca
    └── impl/AirportService.java    implementazione (query + mapping DTO)
```

> Entity, DTO, mapper, repository e utility arrivano dalla dipendenza
> [`xtr-aeroport-common-lib`](https://github.com/XtremeAlex/xtr-aeroport-common-lib).

### Compilazione

**1. Clonare il repository**

```bash
git clone https://github.com/XtremeAlex/xtr-aeroport-ms.git
cd xtr-aeroport-ms
```

**2. Build JVM**

```bash
./mvnw clean package -DskipTests
java -jar ./target/aeroport-3.2.0.jar
```

<img src="_assets/images/mvn-build.png" alt="Build Maven" />

**3. Generare i metadati per la native image**

La native image ragiona "a mondo chiuso": reflection, proxy e risorse dinamiche vanno dichiarati prima, in fase di build. Il `native-image-agent` li raccoglie da solo: si avvia il jar sulla JVM e si usano i vari percorsi dell'applicazione.

```bash
java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image \
  -jar ./target/aeroport-3.2.0.jar
```

<img src="_assets/images/run-agentlib.png" alt="native-image-agent" />

**4. Build nativa (GraalVM)**

```bash
./mvnw package -DskipTests -Pnative
./target/aeroport
```

Se sei su ARM64 (Apple Silicon): `WriteableCodeCache` va disabilitato, `--libc=musl` non è supportato e nemmeno il Garbage Collector G1 ([riferimento GraalVM](https://www.graalvm.org/reference-manual/native-image/)).

**5. Build Docker (buildpacks)**

```bash
# Apple Silicon (ARM64)
./mvnw package -DskipTests -Pdocker-m1-arm
# x86
./mvnw package -DskipTests -Pdocker-x86
```

<p align="right">(<a href="#readme-top">torna su</a>)</p>

## API

Una volta avviato il servizio, la documentazione interattiva è su Swagger UI: `/<context-path>/swagger-ui.html`.

| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/getAirportsBy` | Ricerca aeroporti via query string (types, isoCountry, name, paginazione) |
| POST | `/searchAirports` | Ricerca aeroporti via corpo JSON (`AirportSearchRequest`) |
| GET | `/getAllAirportTypes` | Tutte le tipologie di aeroporto |
| GET | `/getAllAirportType/{pageNumber}/{pageSize}/{sortField}/{sortDir}` | Tipologie paginate (path variable) |
| GET | `/getAllAirportType` | Tipologie paginate (request param) |

Esempio:

```
GET /getAirportsBy?types=1,2&isoCountry=IT&pageNumber=0&pageSize=12&sortField=name&sortDir=ASC
```

<p align="right">(<a href="#readme-top">torna su</a>)</p>

## Play & Test

```bash
# test unitari
./mvnw test
```

- Metriche Prometheus via Actuator: `/actuator/prometheus`
- Tracing distribuito inviato a Zipkin (Micrometer Tracing + OpenTelemetry)

<p align="right">(<a href="#readme-top">torna su</a>)</p>

## Roadmap

Chiusa con la deprecazione. Le due voci aperte non verranno fatte qui.

- [x] Ricerca aeroporti con filtri combinabili e paginazione
- [x] Proxy tipologie via Feign
- [x] Swagger / OpenAPI
- [x] Logging interceptor
- [x] Tracing Zipkin / OpenTelemetry
- [x] Build nativa GraalVM e profili Docker
- [x] Test unitari del service
- [ ] Ricerca voli (`SearchFlights`)
- [ ] Gestione errori centralizzata (`@RestControllerAdvice`)

Le vecchie issue restano consultabili [qui](https://github.com/XtremeAlex/xtr-aeroport-ms/issues).

<p align="right">(<a href="#readme-top">torna su</a>)</p>

## Come contribuire

Il progetto è deprecato, quindi aprire Pull Request qui ha poco senso. Se vuoi contribuire alla suite, il posto giusto è `xtr-aeroport-api-spring`. Per chi vuole comunque partire da qui con un fork, il giro è quello classico:

1. fai un fork del progetto;
2. crea un branch per la tua modifica (`git checkout -b feature/nome-feature`);
3. fai commit (`git commit -m "Aggiunge nome-feature"`);
4. fai push del branch (`git push origin feature/nome-feature`).

<p align="right">(<a href="#readme-top">torna su</a>)</p>

## Licenza
Doppia licenza:

- **GNU AGPL-3.0** (vedi [`LICENSE`](LICENSE)) per l'uso open source. Chi usa questo software, anche solo come servizio di rete, deve renderne disponibile il codice sorgente.
- **Licenza commerciale** per l'uso dentro prodotti proprietari (vedi [`COMMERCIAL-LICENSE.md`](COMMERCIAL-LICENSE.md)). In ogni caso di riuso va mantenuta l'attribuzione all'autore.

<p align="right">(<a href="#readme-top">torna su</a>)</p>

## Contatti

Andrei Alexandru Dabija (XtremeAlex) · [alexdabi92@gmail.com](mailto:alexdabi92@gmail.com) · [2ad.bubume.it](https://2ad.bubume.it/) · [LinkedIn](https://www.linkedin.com/in/andrei-alexandru-dabija/) · [github.com/XtremeAlex](https://github.com/XtremeAlex)

<p align="right">(<a href="#readme-top">torna su</a>)</p>

## Ringraziamenti

- [Spring Boot](https://spring.io/projects/spring-boot) e [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- [GraalVM](https://www.graalvm.org/) per la compilazione nativa
- [springdoc-openapi](https://springdoc.org/) per la documentazione API
- [Best-README-Template](https://github.com/othneildrew/Best-README-Template), da cui ho preso spunto per la struttura

<p align="right">(<a href="#readme-top">torna su</a>)</p>
