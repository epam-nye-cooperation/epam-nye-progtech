# Fox and Hounds parancssoros játék implementáció

* A félév során a hallgatóknak önállóan kell lefejleszteni egy Java parancssoros Fox-and-Hounds játékot
* Ennek leírása itt olvasható a Fox and Hounds szekcióban (angolul):
    https://en.wikipedia.org/wiki/Fox_games
    * A fox-and-hounds kétszemélyes stratégiai táblajáték, mely 1 db négyzetarányos NxN-es (N páros szám, 4 <=N<=12), tipikusan 8x8-as táblán játszható.
    * Az oszlopok számozása tipikusan a,b,c, ... betûkkel történik, a soroké 1,2,3,..,N sorszámokkal -- de ettõl nem függ a játékprogram.
    * Induláskor N/2 darab kutya áll a felsõ sor páros oszlopindexû mezõiben, és 1 db róka áll a bal alsó sarokban. Ez azt jelenti, hogy azonos színû mezõkön állnak, ha sakkpályának gondoljuk a pályát. 
    * A két játékos közül az egyik a rókát vezeti, a másik a kutyákat; minden körben mindkét játékos egyet lép. Egy körben a kutyákat vezetõ csak egy kutyával léphet. 
    * A lépések során a bábuk egy átlós irányban egyet mozdulhatnak el. (1 hosszú futólépés  sakkban.) Nyilván a pályáról nem lehet lelépni.
    * A kutyák célja az, hogy beszorítsák a rókát úgy, hogy ne tudjon lépni. A róka célja az, hogy eljusson a felsõ sor akármelyik mezõjére.
    * A játék akkor ér véget, ha valamelyik játékos eléri a célját.
* Esetünkben az egyik játékos a parancssoron be adja meg a lépését ( mi a rókát vezetjük), ellenfelünk pedig legyen egy lebutított robot, aki véletlenszerû lépéseket tesz a kutyákkal, ugye, mindig csak eggyel.
* A beadandó feladatot két ütemben kell majd elkészíteni és megvédeni
* A védések az óra idõpontjában fognak történni (7. és 14. héten)
* **Elvárások az elsõ (7. heti) védésre:**
    * Egy publikus GitHub repository létrehozása
    * A létrehozott Git repository tartalmazza a beadandó forráskódját
    * A repository tartalmaz egy megfelelõ .gitignore fájlt annak érdekébe, hogy IDE vagy Maven specifikus ideiglenes fájlok ne kerüljenek fel a repository-ba
    * Egy Java 11-es Maven projekt létrehozása (pom.xml és Maven folder struktúra)
    * A Maven projekt az alábbi konfigurációkat tartalmazza:
        * Plugin-ek:
            * org.apache.maven.plugins.maven-jar-plugin - annak érdekében, hogy felkonfiguráljuk az alkalmazásunk belépési pontját (Main Class)
            * org.apache.maven.plugins.maven-assembly-plugin - annak érdekében, hogy egy függõségeket tartalmazó, futtatható JAR fájl jöjjön létre az alkalmazás build-elése eredményeként
            * org.jacoco.jacoco-maven-plugin - annak érdekében, hogy a megírt Egység tesztek kód lefedettségét tudjuk mérni
            * org.apache.maven.plugins.maven-checkstyle-plugin - annak érdekében, hogy a projekten elkövetett kód formázási hibákat és egyéb rossz praktikák automatikus detektáljunk
        * Függõségek:
            * JUnit5
            * Mockito
            * Logback
    * Az alkalmazás Objektum Orientált modellezésének megkezdése
        * Az alkalmazásunkhoz szükséges VO (Value Object) osztályok létrehozása (ügyelve és figyelembe véve a "best practice"-eket: Object methods overriding, Immutability, stb)
    * Az alkalmazás képes kezdetleges felhasználói interakciókat fogadni
        * Például: Játékos nevének bekérése, Játék elindítása, Fox-and_Hounds játéktér kiiratása, Egy lépés fogadása a parancssoron, a lépés vizsgálata abból a szempontból, hogy alkalmazható-e; a lépés alkalmazása és az eredmény kiírása, stb
        * Itt nem határozunk meg kötelezõ elvárásokat, tetszõleges kezdetleges interakciók elegendõek
    * Egység tesztek 80% lefedettséget biztosítanak üzleti logikát tartalmazó osztályokra (tehát például VO osztályokra nem szükséges egységteszteket írni)
* **Elvárások a második (14. heti) védésre:**
    * A teljes Fox and Hounds játék funkcionalitás lefejlesztésre került (lehetséges egy játékot végig játszani elejétõl a végéig)
    * A projekt a `mvn clean install` parancs futtatására hiba nélkül fordul
	* Spring IoC / DI framework bevezetése a projektbe
    * Az alkalmazás egy adatbázisba lementi a játékosok nevét és azt, hogy hányszor nyertek
        * Az alkalmazás képes megjeleníteni parancssorban egy high score táblázatot (melyik játékos hány meccset nyert)
    * Opcionális (plusz pontért): egy aktuálisan folyamatban lévõ játék állást az alkalmazás képes egy XML fájlba kimenteni és késõbb visszatölteni (tehát a játékos onnan folytathatja a játékot, ahol korábban abba hagyta)
    * Egység tesztek továbbra is 80% lefedettséget biztosítanak üzleti logikát tartalmazó osztályokra
