# 13. hét, 1. óra

## XML szerializáció/deszerializáció

### Rövid ismertető az XML-ről

- eXtensible Markup Language
- Adattárolásra és adattovábbításra
- Főbb fogalmak áttekintése
  - Elem
  - Attribútúm
  - XML fa struktúra
- Forrás: https://www.w3schools.com/xml/default.asp

### Rövid JAXB ismertető

- Maven függőségek használata
  - API
  - Implementáció
- Fontosabb annotációk áttekintése
  - `@XmlRootElement`
  - `@XmlType`
  - `@XmlElement`
  - `@XmlAttribute`
- `Marshaller` és `Unmarshaller` használata
- Példa, egyszerű `Person` objektum kiíratása majd visszaolvasása XML-ből
- Forrás: https://eclipse-ee4j.github.io/jaxb-ri/

### JAXB bevezetése a Sudoku alkalmazásba

- `GameSavesRepository` XML implementációja
  - `XmlGameSavesRepository`
    - Legegyszerűbb implementáció, gyakorlatilag a `MapVO` kiírása XML-be, minimális módosítással
      - `PersistableMapVO` osztály, ami fieldeket tekintve megegyezik a `MapVO`-val
      - Rendelekzik az `@XmlRootElement` annotációval, illetve üres konstruktorral
  - `AdvancedXmlGameSavesRepository`
    - `XmlMapVO` objektum, ami jobban struktúrált, annak érdekében, hogy a kimeneti XML fájlunk olvashatóbb legyen
- Az új XML-es implementáció bekonfigurálása az alkalmazásunk alá
