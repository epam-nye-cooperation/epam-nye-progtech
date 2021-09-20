# 2. hét, 2. óra
## Maven
### Prezentáció 
### Hello World Maven project
* alap Maven project létrehozása IntelliJ-ből
* Hello World Main class létrehozás
* Maven parancsok
    * mvn validate
    * mvn compile
    * mvn test
    * mvn package (packaging alapértelmezetten JAR)
    * mvn install
    * mvn deploy
    * mvn clean

### Házi feladat
* Csináljatok egy hello world futtatható JAR file-t ami bekéri a neved és egy nyelvet, és az adott nyelven üdvözöl
* Az alkalmazás legalább 3 nyelvet támogasson köztük a magyart
* Ha a felhasználó nem ad meg nyelvet, akkor alapértelmezetten a magyar nyelvet használjuk
* A megoldást rakjuk fel a saját GitHub account-unk alatt létrehozott nye-progtech repo week_1 mappájába
* Használj Maven-t
* Példa input és output
    * java -jar hello-world.jat Csaba -> Szia Csaba!
    * java -jar hello-world.jat Csaba hun -> Szia, Csaba!
    * java -jar hello-world.jat Csaba eng -> Hello, Csaba!
    * java -jar hello-world.jat Csaba haw -> Aloha, Csaba!