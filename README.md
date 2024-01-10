El proyecto esta contruido sobre Java 17 y utiliza maven como su project management, tambien esta construido usando el framework Spring Boot 3.2.1

Para ejecutarlo se puede realizar de dos maneras:

1. Usando el plugin de spring boot

```bash
git clone https://github.com/jselvamadrigal/technical-test.git
cd technical-test
mvn spring-boot:run
```

2. Ejecutando el jar generado

```bash
git clone https://github.com/jselvamadrigal/technical-test.git
cd technical-test
mvn clean package
java -jar target/technical-test-0.0.1.jar
```


Una vez que el proyecto este corriendo dirigirse a la siguiente URL

```Link
http://localhost:5010/technical-test/
```
