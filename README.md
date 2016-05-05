# ctakes-dropwizard

This project serves annotations generated by Apache cTakes using a Dropwizard server. 

To use the service

```POST localhost:8080/annotate```

```HEADERS: Content-type: application/json```


```{ "text": "The patient was diagnosed with the flu"}```

To start the server:
* sh setup.sh
* java -Dctakes.umlsuser=<umls_username> -Dctakes.umlspw=<umls_passwd> -jar target/ctakes-dropwizard.0.0.1-SNAPSHOT.jar server
