## Build executable jar with dependencies  

- <a href="#maven-assembly-plugin">maven-assembly-plugin</a>
- <a href="#maven-shade-plugin">maven shade plugin</a>  
- <a href="#maven-dependency-plugin">maven-dependency-plugin</a>    

---  

<div id="maven-assembly-plugin"></div>

> ## Maven assembly plugin  

**pom-maven-assembly-plugin.xml**  

```aidl
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>2.4</version>
    <configuration>
        <finalName>demo</finalName>
        <archive>
            <manifest>
                <mainClass>demo.Bootstrap</mainClass>
            </manifest>
        </archive>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
        <appendAssemblyId>false</appendAssemblyId>
    </configuration>
</plugin>
```

> Build  

```aidl
$ mvn clean compile assembly:single
or
$ mvn clean package
<executions>
    <execution>
        <id>make-assembly</id>
        <phase>package</phase>
        <goals>
            <goal>single</goal>
        </goals>
    </execution>
</executions>
```  

> Jar file  

```aidl
./
├── com
│   └── google
│       └── gson
.......... class files ...........
├── demo
│   └── Bootstrap.class
└── META-INF
    ├── MANIFEST.MF
    └── maven
        └── com.google.code.gson
            └── gson
                ├── pom.properties
                └── pom.xm
```  

---  

<div id="maven-shade-plugin"></div>

> ## Maven shade plugin  

**pom-maven-shade-plugin.xml**  

```aidl
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>2.3</version>
    <configuration>
        <finalName>demo</finalName>
        <createDependencyReducedPom>false</createDependencyReducedPom>
        <transformers>
            <transformer
              implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                <mainClass>demo.Bootstrap</mainClass>
            </transformer>
        </transformers>
    </configuration>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```  

> build

```aidl
$ mvn clean package
$ java -jar target/demo.jar
```  

> jar file  

```aidl
app@app:~/build-test$ tree .
.
├── com
│   └── google
│       └── gson
.......... class files ...........
├── demo
│   └── Bootstrap.class
└── META-INF
    ├── MANIFEST.MF
    └── maven
        ├── com.google.code.gson
        │   └── gson
        │       ├── pom.properties
        │       └── pom.xml
        └── me.zaccoding
            └── build
                ├── pom.properties
                └── pom.xml

17 directories, 186 files
```  

---  

<div id="maven-dependency-plugin"></div>

> ## Maven dependency plugin  

WORKING.......
- jar:file/...BOOT-INF/lib/*.jar class loader
