# Wumplusz parancssoros labirintusjáték implementáció

* A félév során a hallgatóknak önállóan kell lefejleszteni egy Java parancssoros labirintus játékot
* Ennek leírása itt olvasható
* A wumplusz egy egyszemélyes problémamegoldó 2D-táblajáték, aminek jelen feladatban a pályaszerkesztőjét és játékmenetét kell implementálni
* A program egy felhasználónév bekérésével kezdődik, utána
* A program egy alapmenüvel folytatódik, amiből lehet lépni pályaszerkesztésbe, fájlból beolvasásba, adatbázisból betöltésbe, adatbázisba mentésbe,  játszásba, kilépésbe
* A pályaszerkesztés és a fájlból beolvasás közül elég az **egyiket** implementálni
* A pályaszerkesztés közben:
    * A játék 1 db négyzetes alakú NxN-es (N egész szám,  6<=N<=20), tipikusan 10x10-as táblán játszható.
    * Az oszlopok számozása tipikusan a,b,c, ... betûkkel történik, a soroké 1,2,3,..,N sorszámokkal -- de ettõl nem függ a játékprogram.
    * A pálya tartalmazhatja a következő fajta elemeket: FAL, VEREM, WUMPUSZ, HŐS, ARANY
    * Egy pozició csak egy lehet a {FAL,VEREM,WUMPUSZ,ARANY} halmazból, s lehet üres is
    * A hős csak ott lehet, ahol nincsen fal, s nincs verem, de lehet együtt az arannyal!
    * A pályaszerkesztés menetében adott pozícióra lehessen hozzáadni és elvenni a fent megnevezett elemeket
        * WUMPUSZ-ból N<=8 esetén 1 lehessen, 9<=N<=14 esetén 2, N>14 esetén 3, ez automatikusan állítódjon be N alapján
        * ARANY-ból 1 legyen, a játék célja az, hogy a hős visszavigye a kiindulásához
        * HŐSBŐL is 1 darab legyen, neki a kezdőirányát is meg kell adni, a nyilainak száma automatikusan számolódjon a wumpuszok számából, azzal egyenlően, az aranyat ne birtokolja kezdéskor
    * Induláskor, a pályaszerkesztés kezdésekor a pálya tartalmazzon az első és utolsó sorban és oszlopban falakat, azokat ne lehessen változtatni
    * A pályaszerkesztés végét egy külön paranccsal jelezzük, ekkor visszakerülünk az alapállapotba
* A fájlból beolvasás során egy "wumpluszinput.txt" nevű fájlból kell beolvasni a pályát,
    * amiben NxN-es pálya esetén
    * az első sorban áll
        * N decimális integerként megadva és még
        * egy angol betű, a hős pozíciójának oszlopaként és még
        * egy int, ami a hős sorát írja le, aztán még
        * egy N/W/S/E betű, ami a hős kezdeti irányát jelzi
    * a többi, N számú sorban N hosszú sztringeknek kell lennie, amik a W/H/U/P/G/_ karakterekből állnak
        * W -- fal, H -- hős, U -- wumpusz, P -- verem, G -- arany, _ -- üres
* Mind a fájlbeolvasásnál, mind a szerkesztés vége jelzése után validálni kell az inputot a helyes darabszámokra is.
* Például, a ![példavilág](https://urldefense.com/v3/__https://courses.washington.edu/css482/hw60x.png__;!!GF_29dbcQIUBPA!wDwnLcYdBl2BBO43AenNO0v3g2ROOyfgbJfAQr-zKVgUNDA04_X3fUyGP3RjpjB85-z7OJ88DRcf9HRJC0HhTb0y$ [courses[.]washington[.]edu])
* világot a következő [input fájl](wumpuszinput.txt)-lal lehet bevinni
    * 6 B 5 E
    * WWWWWW
    * W___PW
    * WUGP_W
    * W____W
    * W__P_W
    * WWWWWW
* ((A képen látható stench és breeze jelekkel most nem foglalkozunk,majd esetleg mestint órán))
* A fenti példában csak a szélen vannak falak, de ez ránk nem érvényes, ilyesmi pályáink is lehetségesek, ahol belül is van fal:
*  ![példavilág2](https://urldefense.com/v3/__http://www.kr.tuwien.ac.at/students/prak_wumpusjava/simulator/images/applet.gif__;!!GF_29dbcQIUBPA!wDwnLcYdBl2BBO43AenNO0v3g2ROOyfgbJfAQr-zKVgUNDA04_X3fUyGP3RjpjB85-z7OJ88DRcf9HRJC6HU_Hdi$ [kr[.]tuwien[.]ac[.]at])
* Az adatbázisba mentés és visszatöltés parancsokat még nem kell implementálni, majd csak a 2. fázisban
* A játék módba lépve:
    * A HŐS minden pillanatában legyen olyan tulajdonsága, hogy merre néz; valamint, hogy hány nyila van (a nyilak száma kezdetben megegyezik a wumpuszok számával)
    * A HŐS-nek legyenek ilyan akciói: lép, fordul jobbra, fordul balra, lő, aranyat felszed
    * A lövés hatása: a nyíl egyenesen, a hős nézési irányába indul, a falakon nem tud átmenni -- ott megsemmisül, de ha előtte eltalálja a wumpuszt,
    * akkor az lekerül a pályáról, egy sikolyt hallatva (ezt nem kell most implementálni)
    * A lépés, fordulások hatását nem kell magyarázni, világos (remélem, VS)
    * A verembe lépve, elveszit egy nyilat
    * A wumpuszra lépve meghal a hős
    * A felszedés hatása, hogy már birtokolja az aranyat a hős
    * A játékmenet közbenső állapotait nem szükséges menteni
    * Legyen olyan akció is, hogy "felad", akkor kilépünk a játékból
    * Legyen olyan akció is, hogy "halasztás", akkor elmentjük a játékállást, társítva a felhasználói névvel -- ezt az 1. fázisban még nem kell
    * Ha a hős teljesítette a küldetést, akkor
        * elmentjük a felhasználónevet és a pontszámot -- ezt az 1. fázisban még nem kell
        * kiírjuk, hogy ennyi-meg-ennyi lépéssel teljesítetted a pályát, ügyes vagy, s visszalépünk az alapmenübe


* A beadandó feladatot két ütemben kell majd elkészíteni és megvédeni
* A védések az óra idõpontjában fognak történni (9. és 14. héten a nappali képzésen, a levelezőn az első és a második összevonva, dec 9, szombaton)
* A nappalis védéseken a hétfői védés előtti vasárnap reggel 8.00-ig beadott commit-okat értékeljük,
* a levelezősön a szombati védés előtti pénteken reggel 8:00-ig beadottakat értékeljük,
* **Elvárások az elsõ védésre:**
    * Egy publikus GitHub repository létrehozása
    * A létrehozott Git repository tartalmazza a beadandó forráskódját
    * A repository tartalmaz egy megfelelõ .gitignore fájlt annak érdekébe, hogy IDE vagy Maven specifikus ideiglenes fájlok ne kerüljenek fel a repository-ba
    * Egy Java 17-es Maven projekt létrehozása (pom.xml és Maven folder struktúra)
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
        * Az alkalmazásunkhoz szükséges VO (Value Object) osztályok létrehozása
            * (ügyelve és figyelembe véve a "best practice"-eket: Object methods overriding, Immutability, stb)
            * betartva a Clean Code szabályait
    * Az alkalmazás képes kezdetleges felhasználói interakciókat fogadni
        * Például:
            * Játékos nevének bekérése,
            * Pályaszerkesztés kezdése
            * játéktér kiiratása
            * Egy szerkesztési parancs (elemek hozzáadása, visszavétele) fogadása a parancssoron
            * a szerkesztési lépés vizsgálata abból a szempontból, hogy alkalmazható-e
        * Itt nem határozunk meg kötelezõ elvárásokat, tetszõleges kezdetleges interakciók elegendõek
    * Egység tesztek 80% lefedettséget biztosítanak üzleti logikát tartalmazó osztályokra (tehát például VO osztályokra nem szükséges egységteszteket írni)
* **Elvárások a második védésre:**
    * A teljes játék funkcionalitás lefejlesztésre került (lehetséges egy játékot végig játszani elejétõl a végéig)
    * A projekt a `mvn clean install` parancs futtatására hiba nélkül fordul
    * Az alkalmazás egy adatbázisba lementi a játékosok nevét és azt, hogy hányszor nyertek
        * Az alkalmazás képes megjeleníteni parancssorban egy high score táblázatot (melyik játékos hány meccset nyert)
    * Opcionális (plusz pontért): egy aktuálisan folyamatban lévõ játék állást az alkalmazás képes egy XML fájlba kimenteni és késõbb visszatölteni (tehát a játékos onnan folytathatja a játékot, ahol korábban abba hagyta)
    * Egység tesztek továbbra is 80% lefedettséget biztosítanak üzleti logikát tartalmazó osztályokra
