# Beadandó feladat

## Tippek

* Pár rövid tipp, ami jól jöhet fejlesztés során: [tips.md](tips.md)

## Torpedó parancssoros játék implementáció

* A félév során a hallgatóknak önállóan kell lefejleszteni egy Java parancssoros Torpedó játékot
    * A Torpedó kétszemélyes stratégiai táblajáték, mely 2x2 db négyzetarányos táblán játszható
    * A győzelemhez ki kell lőni az ellenfél összes hajóját
    * Mindkét játékos előtt két darab vízszintesen betűzött, és függőlegesen számozott tábla van. Az egyiken ő jelöli a lövéseit, a másikon a saját hajói vannak, és az ellenfél lövéseit jelöli.
    * A játékosok felváltva mondanak egymásnak pozíciókat, (pl. A3) és mindketten kijelölik a mondott területet.
    * Találatnak számít, ha eltalálunk egy hajót, süllyedésnek, ha minden kockáját eltaláltuk.
    * Ha nem találjuk el a hajót, azt X-el, ha eltaláljuk +-al jelöljük, az elsüllyedt hajót kisatírozzuk.
    * A játék akkor ér véget, ha valamelyik játékosnak az összes hajója ki van lőve.
    * Forrás: [Wikipedia](https://hu.wikipedia.org/wiki/Torped%C3%B3_(j%C3%A1t%C3%A9k))
* Esetünkben az egyik játékos mi magunk leszünk, ellenfelünk pedig legyen egy lebutított robot, aki véletlenszerű lépéseket tesz
* A beadandó feladatot két ütemben kell majd elkészíteni és megvédeni
* A védések az óra időpontjában fognak történni (7. és 14. héten)
* **Elvárások az első (7. heti) védésre:**
    * Egy publikus GitHub repository létrehozása
    * A létrehozott Git repository tartalmazza a beadandó forráskódját
    * A repository tartalmaz egy megfelelő .gitignore fájlt annak érdekébe, hogy IDE vagy Maven specifikus ideiglenes fájlok ne kerüljenek fel a repository-ba
    * Egy Java 11-es Maven projekt létrehozása (pom.xml és Maven folder struktúra)
    * A Maven projekt az alábbi konfigurációkat tartalmazza:
        * Plugin-ek:
            * org.apache.maven.plugins.maven-jar-plugin - annak érdekében, hogy felkonfiguráljuk az alkalmazásunk belépési pontját (Main Class)
            * org.apache.maven.plugins.maven-assembly-plugin - annak érdekében, hogy egy függőségeket tartalmazó, futtatható JAR fájl jöjjön létre az alkalmazás build-elése eredményeként
            * org.jacoco.jacoco-maven-plugin - annak érdekében, hogy a megírt Egység tesztek kód lefedettségét tudjuk mérni
            * org.apache.maven.plugins.maven-checkstyle-plugin - annak érdekében, hogy a projekten elkövetett kód formázási hibákat és egyéb rossz praktikák automatikus detektáljunk
        * Függőségek:
            * JUnit5
            * Mockito
            * Logback
    * Az alkalmazás Objektum Orientált modellezésének megkezdése
        * Az alkalmazásunkhoz szükséges VO (Value Object) osztályok létrehozása (ügyelve és figyelembe véve a "best practice"-eket: Object methods overriding, Immutability, stb)
    * Az alkalmazás képes kezdetleges felhasználói interakciókat fogadni
        * Például: Játékos nevének bekérése, Játék elindítása, Torepedó játéktér kiiratása, Hajók lerakásának interakciója, stb
        * Itt nem határozunk meg kötelező elvárásokat, tetszőleges kezdetleges interakciók elegendőek
    * Egység tesztek 80% lefedettséget biztosítanak üzleti logikát tartalmazó osztályokra (tehát például VO osztályokra nem szükséges Egység teszteket írni)
* **Elvárások a második (14. heti) védésre:**
    * Spring IoC / DI framework bevezetése a projektbe
    * A teljes Torpedó játék funkcionalitás lefejlesztésre került (lehetséges egy játékot végig játszani elejétől a végéig)
    * Az alkalmazás egy adatbázisba lementi a játékosok nevét és azt, hogy hányszor nyertek
        * Az alkalmazás képes megjeleníteni parancssorban egy high score táblázatot (melyik játékos hány meccset nyert)
    * Egy aktuálisan folyamatban lévő játék állást az alkalmazás képes egy XML fájlba kimenteni és később visszatölteni (tehát a játékos onnan folytathatja a játékot, ahol korábban abba hagyta)
    * Egység tesztek továbbra is 80% lefedettséget biztosítanak üzleti logikát tartalmazó osztályokra