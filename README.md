<a name="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![Dependency Graph][maven.shield]][dependencies-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="">
    <img src="_assets/images/logo.png" width="300" alt="Logo">
  </a>

  <h3 align="center">Aeroport MS</h3>

  <p align="center">
   **xtr-aeroports-ms** è un micro-servizio dedicato all'accesso delle informazioni sugli aeroporti in tutto il mondo.
    <br />
    <a href="https://github.com/XtremeAlex/xtr-aeroport-ms"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="">View Demo Live (in progres...)</a>
    ·
    <a href="/issues">Report Bug</a>
    ·
    <a href="/issues">Request Feature</a>
  </p>
</div>


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Sommario</summary>
  <ol>
    <li>
      <a href="#info-sul-progetto">Info sul progetto</a>
      <ul>
        <li><a href="#invito-alla-collaborazione">Invito alla Collaborazione</a></li>
      </ul>
      <ul>
        <li><a href="#stack-tecnologico">Stack Tecnologico</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisiti">Prerequisiti</a></li>
        <li><a href="#compilazione">Compilazione</a></li>
      </ul>
    </li>
    <li><a href="#play-&-test">Play & Test</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#aiutami">Aiutami</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#ringraziamenti">Ringraziamenti</a></li>
  </ol>
</details>


<!-- ABOUT THE PROJECT -->
## Info sul progetto

[![Product Name Screen Shot][product-screenshot]]()

Durante il poco tempo che riesco a strappare al lavoro e agli altri impegni, mi diverto a sperimentare con tecnologie e nuovi framework, e questa ne è una piccola dimostrazione.
Questo progetto nasce come piattaforma sperimentale, l'obiettivo è fornire un set di API robuste per accedere a informazioni dettagliate sugli aeroporti e le rotte aeree a livello globale.
Questa iniziativa è partita come un esperimento personale, si è evoluta in una solida base per testare nuovi pattern, metodologie, e tecnologie.

Questo è solo uno dei numerosi moduli che prevedo di rilasciare, con l'intento di condividerli e arricchirli attraverso il contributo della community.


### Invito alla Collaborazione
Il contributo di idee, codice e feedback è sempre benvenuto e incoraggiato per diversi motivi:
* Sperimentare con Nuove Tecnologie
  * Questo progetto mette alla prova le proprie competenze in un contesto realistico.
* Crescita Collettiva
  * Collaborando, possiamo imparare gli uni dagli altri, scambiare idee e crescere professionalmente insieme.
* Base per Sviluppi Futuri
  * Il progetto è strutturato seguendo le best practice, rendendolo un ottimo punto di partenza per i tuoi sviluppi futuri.
  * L'approccio "Enterprise Like" garantisce che le soluzione sia di alta qualità e pronta per l'uso in contesti aziendali.

Grazie a tutti quelli che hanno o vorranno contribuire ad espandere qusto progetto.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Stack Tecnologico
Le tecnologie/framework e SO usati:
* [![Java][java.shield]][java.url]
* [![Spring][spring.shield]][spring.url]
* [![Windows][Windows.shield]][Windows.url]
* [![Linux][Linux.shield]][Linux.url]
* [![Macos][Macos.shield]][Macos.url]
* [![GitHub][GitHub.shield]][GitHub.url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started
Questo progetto è configurato per usare Maven per la gestione delle dipendenze e la compilazione.
È sviluppato con Spring 3 e Java 17 e può essere facilmente avviato e testato in un ambiente locale.

### Prerequisiti
Per utilizzare e contribuire a questo progetto, avrai bisogno di installare:
- Git (2.43.0)
- Java OJDK (Graal Versione 17)
- Maven (Apache Maven 3.9.6)

**Contenent**

```
.
├── dir.txt
├── main
│   ├── java
│   │   └── com
│   │       └── xtremealex
│   │           └── aeroport
│   │               ├── AeroportApplication.java
│   │               ├── common
│   │               │   └── models
│   │               │       ├── ErrorCode.java
│   │               │       ├── json
│   │               │       │   ├── AirportJson.java
│   │               │       │   └── CountryJson.java
│   │               │       ├── request
│   │               │       │   └── AirportSearchRequest.java
│   │               │       └── response
│   │               │           ├── ResponseWrapper.java
│   │               │           ├── ResponseWrapperBuilder.java
│   │               │           ├── airports
│   │               │           │   ├── AirportDTO.java
│   │               │           │   └── AirportTypeDTO.java
│   │               │           ├── countries
│   │               │           │   └── CountryDTO.java
│   │               │           └── flights
│   │               │               └── VoloDTO.java
│   │               ├── configuration
│   │               │   ├── MyConfig.java
│   │               │   └── MyConfigMvc.java
│   │               ├── controller
│   │               │   ├── SearchAirport.java
│   │               │   ├── SearchAirportType.java
│   │               │   ├── SearchCountries.java
│   │               │   ├── SearchFlights.java
│   │               │   └── TestController.java
│   │               ├── entity
│   │               │   ├── AirportEntity.java
│   │               │   ├── VoloEntity.java
│   │               │   ├── typological
│   │               │   │   ├── AirportTypeTypology.java
│   │               │   │   └── CountryTypology.java
│   │               │   └── view
│   │               │       └── CountryAirportCountView.java
│   │               ├── mapper
│   │               │   ├── IAirportMapper.java
│   │               │   ├── IAirportTypeTypologyMapper.java
│   │               │   └── ICountryTypologyMapper.java
│   │               ├── repository
│   │               │   ├── AirportRepository.java
│   │               │   ├── AirportTypeRepository.java
│   │               │   ├── CountryAirportRepository.java
│   │               │   ├── CountryTypeRepository.java
│   │               │   └── VoloRepository.java
│   │               └── service
│   │                   ├── IAirportService.java
│   │                   ├── IAirportTypeService.java
│   │                   ├── IEncryptionService.java
│   │                   ├── impl
│   │                   │   ├── AirportService.java
│   │                   │   ├── AirportTypeService.java
│   │                   │   └── EncryptionService.java
│   │                   └── provvisorioBatchInit
│   │                       ├── AirportDataService.java
│   │                       └── CountryService.java
│   └── resources
│       ├── META-INF
│       │   └── native-image
│       │       ├── agent-extracted-predefined-classes
│       │       ├── jni-config.json
│       │       ├── predefined-classes-config.json
│       │       ├── proxy-config.json
│       │       ├── reflect-config.json
│       │       ├── resource-config.json
│       │       └── serialization-config.json
│       ├── application.yml
│       ├── banner.txt
│       ├── data.sql
│       ├── dataset
│       │   ├── airports
│       │   │   └── world-airport.json
│       │   └── country
│       │       ├── countries-flag.json
│       │       └── world-cities.json
│       └── schema.sql
└── test
    └── java
        └── com
            └── xtremealex
                └── aeroport
                    └── AeroportApplicationTests.java
```

### Compilazione

**Clonare il Repository**

1. Per iniziare, clona il repository `xtr-aeroports-ms` sul tuo computer locale usando Git.
   Apri il terminale e esegui il seguente comando:
   ```
   git clone https://<repository>/xtr-aeroports-ms.git cd
   xtr-aeroports-ms
   ```

2. Una volta clonato il repository, puoi compilare il progetto utilizzando Maven.
   Esegui il seguente comando nella directory radice del progetto:
   ```
   mvn clean package -DskipTests
   ```
   <img src="_assets/images/mvn-build.png" alt="_assets/images/mvn-build.png"/>

3. Ora puoi avviarlo eseguendo:
   ```
   java -jar ./target/aeroport-0.1.jar
   ```
   <img src="_assets/images/run-by-graal-jdk17.png" alt="_assets/images/graal-jdk17-start-jar.png"/>


4. Generare file necessari per la native-image:

   Ci vorrà un po' se si hanno configurazioni particolari come nel mio caso. Ma è una situazione eccezionale.

   ```
   java -agentlib:native-image-agent=config-output-dir=./src/main/resources/META-INF/native-image -jar ./target/*.jar com.xtremealex.aeroport.AeroportApplication
   ```
   <img src="_assets/images/run-agentlib.png" alt="_assets/images/run-agentlib.png" />


5. Compilare Nativamente un Imagine:

   Sto usando un'Apple M1 come CPU, che è il primo System on a chip progettato da Apple Inc. in architettura RISC su base ARM (Aarch64)
   Nel mio progetto ci sono i profili adeguati per la diversa architettura della propria macchina.
   `farò un approfondimento in seguito`

   Quasi tutte le funzionalità di graal sono supportate su ARM64, ad'eccezione delle limitazioni descritte di seguito.
   <a href="https://www.graalvm.org/22.0/reference-manual/native-image/ARM64/">www.graalvm.org</a>
   ```
    -R:[+|-]WriteableCodeCache: deve essere disabilitato.
    --libc=<value>: musl non è supportato.
    --gc=<value>: Il Garbage Collector G1 ( G1) non è supportato.
   ```

   Ogni opzione ha uno scopo specifico per ottimizzare e configurare la compilazione. (Nel .POM)
   ```
   <plugin>
    <groupId>org.graalvm.buildtools</groupId>
     <artifactId>native-maven-plugin</artifactId>
      <configuration>
       <mainClass>${mainClass}</mainClass>
        <buildArgs>
         <arg>
          --verbose --> Abilita la stampa di messaggi dettagliati durante il processo di compilazione. È utile per il debug e per capire meglio come procede la compilazione.
          -Dspring.aot.enabled=true --> Abilita il supporto per l'ottimizzazione Ahead-of-Time (AOT) di Spring, che migliora le prestazioni di avvio e riduce l'uso della memoria.
          -H:TraceClassInitialization=true --> Traccia l'inizializzazione delle classi durante la compilazione. Questo aiuta a identificare le classi che potrebbero causare problemi durante la compilazione nativa.
          -H:+ReportExceptionStackTraces --> Stampa la traccia dello stack delle eccezioni in caso di errori durante la compilazione.
          -H:Name=aeroport --> Imposta il nome del file eseguibile finale.
          -H:DashboardDump=aeroport-dump --> Specifica il nome del file dump per il dashboard di GraalVM.
          -H:+DashboardAll --> Abilita la raccolta di tutti i dati per il dashboard di GraalVM.
          -H:Class=com.xtremealex.aeroport.AeroportApplication
          --initialize-at-build-time=org.slf4j.LoggerFactory,ch.qos.logback,org.slf4j.MDC,org.slf4j.impl.StaticLoggerBinder -->  Elenca le classi o i pacchetti che devono essere inizializzati al momento della compilazione, anziché a runtime. Ciò può migliorare le prestazioni di avvio.
          --initialize-at-run-time=framework --> In caso si usi un framework personalizzato deve essere inizializzato a runtime, non durante la compilazione.
          -Dspring.graal.remove-unused-autoconfig=true --> Rimuove le configurazioni automatiche non utilizzate per ridurre la dimensione dell'immagine nativa.
          -Dspring.graal.remove-yaml-support=true --> Disabilita il supporto per i file YAML per ridurre ulteriormente la dimensione dell'immagine.
         </arg> 
      ...   
      ```

      Per risolvere i problemi di Reflection bisogna di lanciare prima di qualsiasi compilazione nativa:
      ```
      java -agentlib:native-image-agent=config-output-dir=./src/main/resources/META-INF/native-image -jar ./target/*.jar com.xtremealex.aeroport.AeroportApplication
      ```

      OPPURE
      ```
      java -agentlib:native-image-agent=config-output-dir=./configs/native-image -jar ./target/*.jar com.xtremealex.aeroport.AeroportApplication  
      ```

      Il plugin native-image-agent genera diversi file .json, ognuno dei quali contiene informazioni specifiche su un aspetto del codice che deve essere compilato.
      ```
         <buildArg>-H:ReflectionConfigurationFiles=configs/native-image/reflect-config.json</buildArg>
         <buildArg>-H:JNIConfigurationFiles=configs/native-image/jni-config.json</buildArg>
         <buildArg>-H:DynamicProxyConfigurationFiles=configs/native-image/proxy-config.json</buildArg>
         <buildArg>-H:ResourceConfigurationFiles=configs/native-image/resource-config.json</buildArg>
         <buildArg>-H:SerializationConfigurationFiles=configs/native-image/serialization-config.json</buildArg>
      ```

      Se si crea una folder sotto resources META-INF.native-image non serve aggiungere i buildArgs, di default li cerca dentro resources/META-INF/native-image
      ```
      </buildArgs>
                    <agent>
                        <enabled>true</enabled>
                        <defaultMode>Standard</defaultMode>
                        <options>
                            <trackReflectionMetadata>true</trackReflectionMetadata>
                        </options>
                        <metadataCopy>
                            <disabledStages>
                                <stage>true</stage>
                            </disabledStages>
                            <merge>true</merge>
                            <outputDirectory>/tmp/native-image</outputDirectory>
                        </metadataCopy>
                    </agent>
                    -->
                </configuration>
            </plugin>        
    ...
   ```

  A valle delle configurazioni sul pom, procedere con la compilazione nativa:
  ```
  mvn package -DskipTests -Pnative
  ```
  <img src="_assets/images/native-mvn-build.png" alt="_assets/images/mvn-build-native.png" />

  In ./target/<app-nativa-per-il-tuo-sistema>, in questo caso 'aeroport', per i sistemi windows sarà aeroport.exe

  <img src="_assets/images/native-macos-result-build.png" alt="_assets/images/result-build.png" />


  **Avvio dell'applicazione NATIVA**
  ```
  ./target/aeroport
  ```

  <img src="_assets/images/native-run-app.png" alt="_assets/images/native-run-app.png" />

  Come si puo vedere i tempi di avvio si sono dimezzati (`4.22`), e considerate che è attivo l'init che importa i valori del json dentro l'h2.

  Se procediamo disabilitando l'init abbiamo una differenza immensa della durata di avvio dell'applicazione con benefici assoluti in un'ambiente cloud.
  no-init `1.507 seconds`

  <img src="_assets/images/run-no-init-by-graal-jdk17.png" alt="_assets/images/run-no-init-by-graal-jdk17.png" />

  native no-init `0.151 seconds`, **si avvia 10 vole piu velocemente** e usando meno risorse.

  <img src="_assets/images/native-run-no-init-by-graal-jdk17.png" alt="_assets/images/native-run-no-init-by-graal-jdk17.png" />

  **Perciò facendo un recap:**
  ```
  mvn clean
  mvn package -DskipTests
  java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar ./target/*.jar com.xtremealex.aeroport.AeroportApplication
  mvn package -DskipTests -Pnative
  ./target/aeroport
  ```
  Si può usare anche docker per effettuare una build:

  ```
  mvn package -DskipTests -Pnative && mvn package -DskipTests -Pdocker-m1-arm
  docker run -p 8080:8080 artifactory.io/k8s-test/namespace/com.xtremealex/aeroport:0.2.0
  ```
  <img src="_assets/images/native-docker-arm-build.png" alt="_assets/images/native-docker-arm-build.png" />
  <img src="_assets/images/native-run-docker-arm.png" alt="_assets/images/native-run-docker-arm.png" />

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Play & Test


1. Controller:

   1. getAirportsBy
   ```
   @GetMapping("/getAirportsBy")
    public ResponseEntity<?> getAirportsBy(@RequestParam(required = false) Set<String> types,
                                          @RequestParam(required = false) String isoCountry,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(defaultValue = "0") int pageNumber,
                                          @RequestParam(defaultValue = "12") int pageSize,
                                          @RequestParam(required = false) String sortField,
                                          @RequestParam(defaultValue = "ASC") String sortDir) {
                                          < code... >
    }
                                        
    URL: http://localhost:8080/xtr-aeroport/getAirportsBy?pageNumber=0&pageSize=4&sortField=name&types=1,2,3,4,5,6,7,8,9
    ```
   2. searchAirports
     ```
    @PostMapping("/searchAirports")
    public ResponseEntity<?> searchAirports(@RequestBody AirportSearchRequest searchRequest) {                      
                                          < code... >                              
    }
                                        
    URL: http://localhost:8080/xtr-aeroport/searchAirports
    ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] Creare Verticale SearchAirport
- [x] Creare Verticale SearchAirportType
- [x] Sostituire MolderMapper con Mapstruct
- [x] Compilare Nativamente con Graal
- [ ] Creare tutti i Dockerfile e Docker-Compose per
    - [ ] Graal JDK17
    - [ ] Graal JDK17 (nativo)
    - [ ] OpenJDK17
    - [ ] altri...
- [ ] Testare il tutto riportando le Statistiche

Consulta [open issues](https://github.com/XtremeAlex/xtr-aeroport-ms/issues) per una lista completa delle funzionalità proposte (bug e feature).
s
<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTRIBUTING -->
## Aiutami

L'aiuto di tutti rende la comunità open source un luogo incredibile per imparare, ispirarsi e creare.
Qualsiasi contributo tu possa fornire è **enormemente apprezzato**.

Se hai un suggerimento che potrebbe migliorare questo progetto, per favore effettua un fork del repository e crea una pull request.
Puoi anche semplicemente aprire un problema con il tag "bug".

Non dimenticare di mettere una stella al progetto!
Grazie ancora!



## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create.
Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "bug".

Don't forget to give the project a star!
Thanks again!

## Git Command

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/SexToysFeature`)
3. Commit your Changes (`git commit -m 'Add some SexToysFeature'`)
4. Push to the Branch (`git push origin feature/SexToysFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the Apache License. See `LICENSE` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTACT -->
## Contact

Andrei Alexandru Dabija - [@linkedin](https://www.linkedin.com/in/andrei-alexandru-dabija/) - /andrei-alexandru-dabija

GitHub Link: [https://github.com/XtremeAlex](https://github.com/XtremeAlex)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Ringraziamenti

Inserisco questi url che potrebbero essere utili:

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/XtremeAlex/xtr-aeroport-ms.svg?style=for-the-badge
[contributors-url]: https://github.com/XtremeAlex/xtr-aeroport-ms/graphs/contributors
[dependencies-url]: https://github.com/XtremeAlex/xtr-aeroport-ms/network/dependencies
[forks-shield]: https://img.shields.io/github/forks/XtremeAlex/xtr-aeroport-ms.svg?style=for-the-badge
[forks-url]: https://github.com/XtremeAlex/xtr-aeroport-ms/network/members
[stars-shield]: https://img.shields.io/github/stars/XtremeAlex/xtr-aeroport-ms.svg?style=for-the-badge
[stars-url]: https://github.com/XtremeAlex/xtr-aeroport-ms/stargazers
[issues-shield]: https://img.shields.io/github/issues/XtremeAlex/xtr-aeroport-ms.svg?style=for-the-badge
[issues-url]: https://github.com/XtremeAlex/xtr-aeroport-ms/issues
[license-shield]: https://img.shields.io/github/license/XtremeAlex/xtr-aeroport-ms.svg?style=for-the-badge
[license-url]: https://github.com/XtremeAlex/xtr-aeroport-ms/blob/develop/LICENSE
[linkedin-shield]: https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white
[linkedin-url]: https://www.linkedin.com/in/andrei-alexandru-dabija/
[product-logo]: _assets/images/banner-dark.png
[product-screenshot]: _assets/images/banner.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
[GitHub.shield]: https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white
[GitHub.url]: https://img.shields.io/github/issues/XtremeAlex/
[Linux.shield]: https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black
[Linux.url]: https://wikipedia.org/wiki/Linux
[Macos.shield]:  https://img.shields.io/badge/mac%20os-000000?style=for-the-badge&logo=apple&logoColor=white
[Macos.url]: https://wikipedia.org/wiki/MacOS
[Windows.shield]: https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white
[Windows.url]: https://wikipedia.org/wiki/Windows10
[Java.shield]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java.url]: https://wikipedia.org/wiki/Java_(programming_language)
[Spring.shield]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring.url]: https://spring.io
[Paypal.shield]: https://img.shields.io/badge/PayPal-00457C?style=for-the-badge&logo=paypal&logoColor=white
[Paypal.url]: https://www.paypal.com
[Mozilla.shield]: https://img.shields.io/badge/Firefox_Browser-FF7139?style=for-the-badge&logo=Firefox-Browser&logoColor=white
[Mozilla.url]: https://www.mozilla.org
[maven.shield]: https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white