<a name="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <img src="_assets/images/banner-dark.png" alt="Aeroport MS" width="100%">
  <br /><br />
  <img src="_assets/images/logo.png" width="300" alt="Logo">

  <h3 align="center">Aeroport MS</h3>

  <p align="center">
    Microservizio dedicato all'accesso alle informazioni sugli aeroporti di tutto il mondo.
    <br />
    <a href="https://github.com/XtremeAlex/xtr-aeroport-ms"><strong>Esplora la documentazione &raquo;</strong></a>
    <br />
    <br />
    <a href="https://github.com/XtremeAlex/xtr-aeroport-ms/issues">Segnala un bug</a>
    &middot;
    <a href="https://github.com/XtremeAlex/xtr-aeroport-ms/issues">Richiedi una feature</a>
  </p>
</div>

<!-- SOMMARIO -->
<details>
  <summary>Sommario</summary>
  <ol>
    <li>
      <a href="#info-sul-progetto">Info sul progetto</a>
      <ul>
        <li><a href="#invito-alla-collaborazione">Invito alla collaborazione</a></li>
        <li><a href="#stack-tecnologico">Stack tecnologico</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisiti">Prerequisiti</a></li>
        <li><a href="#struttura-del-progetto">Struttura del progetto</a></li>
        <li><a href="#compilazione">Compilazione</a></li>
      </ul>
    </li>
    <li><a href="#api">API</a></li>
    <li><a href="#play--test">Play &amp; Test</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#come-contribuire">Come contribuire</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contatti">Contatti</a></li>
    <li><a href="#ringraziamenti">Ringraziamenti</a></li>
  </ol>
</details>

<!-- INFO SUL PROGETTO -->
## Info sul progetto

`xtr-aeroport-ms` è un microservizio che espone API REST per cercare aeroporti nel
mondo con filtri combinabili (tipologia, paese, nome), paginazione e ordinamento.
Nasce come piattaforma sperimentale per testare pattern e tecnologie moderne
(microservizi, cloud-native, GraalVM native) in un contesto realistico, ma è
strutturato con un approccio "enterprise like": scalabilità, sicurezza e
manutenibilità.

È uno dei moduli della suite `xtr-aeroport-*`:

| Modulo | Ruolo |
|---|---|
| [`xtr-aeroport-ms`](https://github.com/XtremeAlex/xtr-aeroport-ms) | Microservizio di ricerca aeroporti (questo repo) |
| [`xtr-aeroport-batch`](https://github.com/XtremeAlex/xtr-aeroport-batch) | Import massivo dati (Spring Batch + GraalVM native) |
| [`xtr-aeroport-typological`](https://github.com/XtremeAlex/xtr-aeroport-typological) | Servizio dati tipologici |
| [`xtr-aeroport-common-lib`](https://github.com/XtremeAlex/xtr-aeroport-common-lib) | Libreria condivisa |
| [`xtr-aeroport-web-java`](https://github.com/XtremeAlex/xtr-aeroport-web-java) | Frontend web |

### Invito alla collaborazione

Idee, codice e feedback sono benvenuti:

- **Sperimentare** con tecnologie e pattern moderni in un progetto concreto.
- **Crescere insieme** scambiando idee e imparando gli uni dagli altri.
- **Partire da una base** strutturata secondo le best practice per i propri sviluppi futuri.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Stack tecnologico

- Java 17 (GraalVM)
- Spring Boot 3.2.1 (Web, Data JPA, Actuator)
- Spring Cloud OpenFeign (comunicazione tra servizi)
- PostgreSQL, HikariCP
- MapStruct, Lombok
- springdoc-openapi (Swagger UI)
- Micrometer Tracing + OpenTelemetry / Zipkin
- Docker, Helm / Kubernetes
- Linux, macOS, Windows

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

Il progetto usa Maven per dipendenze e build. È sviluppato con Spring Boot 3 e
Java 17 e può essere avviato e testato in locale.

### Prerequisiti

- Git (>= 2.43)
- GraalVM JDK 17 (per la build nativa) oppure un JDK 17 qualsiasi (per la build JVM)
- Maven (>= 3.9.6) — oppure il wrapper `./mvnw` incluso
- Docker (per il database e le build containerizzate)
- Il servizio [`xtr-aeroport-typological`](https://github.com/XtremeAlex/xtr-aeroport-typological) in esecuzione, per gli endpoint delle tipologie (via Feign)

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

> Entity, DTO, mapper, repository e utility sono forniti dalla dipendenza
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

La native image lavora a closed-world: reflection, proxy e risorse dinamiche vanno
dichiarate a build time. Il `native-image-agent` li genera eseguendo il jar sulla
JVM ed esercitando i vari percorsi dell'applicazione.

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

Note su ARM64 (Apple Silicon): `WriteableCodeCache` disabilitato, `--libc=musl`
non supportato, Garbage Collector G1 non supportato
([riferimento GraalVM](https://www.graalvm.org/reference-manual/native-image/)).

**5. Build Docker (buildpacks)**

```bash
# Apple Silicon (ARM64)
./mvnw package -DskipTests -Pdocker-m1-arm
# x86
./mvnw package -DskipTests -Pdocker-x86
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- API -->
## API

Documentazione interattiva via Swagger UI una volta avviato il servizio:
`/<context-path>/swagger-ui.html`.

| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/getAirportsBy` | Ricerca aeroporti per query string (types, isoCountry, name, paginazione) |
| POST | `/searchAirports` | Ricerca aeroporti via corpo JSON (`AirportSearchRequest`) |
| GET | `/getAllAirportTypes` | Tutte le tipologie di aeroporto |
| GET | `/getAllAirportType/{pageNumber}/{pageSize}/{sortField}/{sortDir}` | Tipologie con paginazione (path variable) |
| GET | `/getAllAirportType` | Tipologie con paginazione (request param) |

Esempio:

```
GET /getAirportsBy?types=1,2&isoCountry=IT&pageNumber=0&pageSize=12&sortField=name&sortDir=ASC
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- PLAY & TEST -->
## Play & Test

```bash
# test unitari
./mvnw test
```

- Metriche Prometheus via Actuator: `/actuator/prometheus`
- Tracing distribuito esportato verso Zipkin (Micrometer Tracing + OpenTelemetry)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- [x] Ricerca aeroporti con filtri combinabili e paginazione
- [x] Proxy tipologie via Feign
- [x] Swagger / OpenAPI
- [x] Logging interceptor
- [x] Tracing Zipkin / OpenTelemetry
- [x] Build nativa GraalVM e profili Docker
- [x] Test unitari del service
- [ ] Ricerca voli (`SearchFlights`)
- [ ] Gestione errori centralizzata (`@RestControllerAdvice`)

Consulta le [open issues](https://github.com/XtremeAlex/xtr-aeroport-ms/issues) per l'elenco completo.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Come contribuire

1. Fai un fork del progetto
2. Crea il tuo feature branch (`git checkout -b feature/nome-feature`)
3. Fai commit delle modifiche (`git commit -m "Aggiunge nome-feature"`)
4. Fai push sul branch (`git push origin feature/nome-feature`)
5. Apri una Pull Request

Ogni contributo è molto apprezzato. E non dimenticare una stella al progetto!

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distribuito con doppia licenza:

- **GNU AGPL-3.0** (vedi [`LICENSE`](LICENSE)) per uso open source. Chi usa questo
  software, anche come servizio di rete, deve renderne disponibile il codice sorgente.
- **Licenza commerciale** per l'uso in prodotti proprietari (vedi
  [`COMMERCIAL-LICENSE.md`](COMMERCIAL-LICENSE.md)). Ogni riuso deve mantenere
  l'attribuzione all'autore.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTATTI -->
## Contatti

Andrei Alexandru Dabija — [LinkedIn](https://www.linkedin.com/in/andrei-alexandru-dabija/) — [github.com/XtremeAlex](https://github.com/XtremeAlex)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- RINGRAZIAMENTI -->
## Ringraziamenti

- [Spring Boot](https://spring.io/projects/spring-boot) e [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- [GraalVM](https://www.graalvm.org/) per la compilazione nativa
- [springdoc-openapi](https://springdoc.org/) per la documentazione API
- [Best-README-Template](https://github.com/othneildrew/Best-README-Template) come ispirazione per la struttura

<p align="right">(<a href="#readme-top">back to top</a>)</p>
