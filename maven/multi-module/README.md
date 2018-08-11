## Multi module with maven

> Tree  

```aidl
multi-module
├── README.md
├── module-api
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── api
│   │   │   │       ├── ApiApplication.java
│   │   │   │       └── web
│   │   │   │           └── PersonController.java
│   │   │   └── resources
│   │   │       └── application.properties
│   │   └── test
│   │       └── java
├── module-common
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── common
│   │               └── entity
│   │                   └── Person.java
├── module-web
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── web
│   │   │   │       ├── WebApplication.java
│   │   │   │       └── controller
│   │   │   │           └── PersonController.java
│   │   │   └── resources
│   │   │       ├── application.properties
│   │   │       └── static
│   │   │           └── index.html
│   │   └── test
│   │       └── java
├── pom.xml
└── src
    ├── assembly
    │   └── build.xml
    └── resources
        └── bin
            └── start.sh

```  

> build  

```
mvn clean install
```  

> TODO  

will build tar 