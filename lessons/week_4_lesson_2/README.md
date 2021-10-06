# 4. hét, 2. óra

## Money projekt további refaktorálása

### Immutable

- `Product` osztály létrehozása, mely tartalmaz egy `Money` példányt
- A referencián keresztül el tudjuk rontani a `Product` árát
- Ez problémás lehet
- Legyen a `Money` is és a `Product` is immutable
  - `final` kulcsszó használata (fieldeken de akár az osztályon is)
  - Setter metódusok eltávolítása
  - Getter metódusok esetén akár másolatok létrehozása a visszaadott értékből
- Előnyök
  - Az objektum csak létrehozáskor kaphat értéket
  - Szálbiztosság
- Hátrányok
  - Megnövekedett memóriaigény

### Függés a Money és a Bank között

- Mind a kéttő hivatkozza egymást (tightly-coupled)
- Függetlenítsük a `Bank` interfacet a `Money`-tól
- A `Bank` osztály ezentúl csak egy átváltási rátát fog visszaadni
- Az új `convertTo()` metódus használati helyeinél refaktorálás

## Unit tesztelés

### Egyszerű unit tesztek

- JUnit5 könyvtár hozzáadása, mint függőség (https://junit.org/junit5/docs/current/user-guide/)
- Maven projekten belül a test könyvtárstruktúra megalkotása
- Teszt elnevezési konvenciók
  - Osztálynév, pl.: `StaticBankTest`
  - Metódusnév, pl.: `testConvertToShouldReturnOneWhenTheTwoCurrenciesAreEqual`
  - A teszt alatt álló osztály egy példánya: `underTest`
- Tesztesetek írása
  - Given / when / then struktúra
  - A tesztelt metódus minél jobb lefedése
- Assertek írása
- `StaticBank` osztály tesztelése

### Mockolás

- Mockito könyvtár felvétele, mint függőség (https://site.mockito.org/)
- Ha a tesztelt osztályunk függőségeket tartalmaz, akkor azokat mockoljuk (nem hozunk létre belőlük is valós példányokat)
- Egyszerű mockolás, mi legyen a visszatérési érték adott paraméterek esetén
  - `given(...).willReturn(...)`
- `MoneyComparator` osztály tesztelése

## Ami az órából kimaradt

- `Money` osztály `add()` metódusának tesztelése
  - Az itt feltöltött változatban megtalálható
- Parametrizált teszt
  - `StaticBankTest` esetén egyszerűsítés lehet, lásd a `testGetExchangeRateShouldReturnCorrectValue` tesztesetet
  - További függőség kell hozzá, `junit-jupiter-params`, lásd a `pom.xml`-t

## A kód fejlődése

- Kiindulási pont az óra elején: https://github.com/epam-nye-cooperation/epam-nye-progtech/commit/9c119a1a01e0ed802f239096ffd3005fc2dfcd2b
- Az elért állapot: https://github.com/epam-nye-cooperation/epam-nye-progtech/commit/25703ee019c74a4633af03733a7fbd5acb1a424c
- A két állapot összehasonlítva: https://github.com/epam-nye-cooperation/epam-nye-progtech/compare/9c119a1...25703ee
