# 5. hét, 1. óra

## Sudoku - 1. rész

### Mi az a Sudoku?

- A játék alap szabályainak átbeszélése
  - Forrás: https://hu.wikipedia.org/wiki/Sz%C3%BAdoku

### Az alap Maven projekt átbeszélése

- Package struktúra
  - model
  - service
  - ui
- Függőségek
  - Logback
  - JUnit 5
  - Mockito
- Pluginek
  - Maven Assembly Plugin
  - Maven Surefire Plugin
  - JaCoCo Maven Plugin
  - Maven Site Plugin
  - Maven Checkstyle plugin

### Sudoku pálya reprezentálása

- Saját model osztály létrehozása: `MapVO`
  - A pályán lévő számok
    - Kétdimenziós tömbként
    - 1-9-ig elfogadott értékek
    - A 0 jelenti, hogy az adott mező üres
  - Fix értékek jelölése
    - Így a beolvasott pálya kezdeti értékeit nem lehet módosítani később
  - Immutable osztály
    - Szükséges mélymásoló műveletek elvégzése

### Pálya nyers változatának beolvasása

- Egy általános `MapReader` interface
  - Az implementációtól függ, hogy honnan olvasunk be egy pályát
- `BufferedReaderMapReader` implementáció
  - Egy `BufferedReader`-ből olvas be pályát
  - `BufferedReader` mint függőség, ezáltal a tesztelés is könnyebb
  - Saját kivétel (`MapReadException`) dobása, hibás beolvasás esetén

### Pálya nyers változatának átalakítása a saját reprezentációnkra

- `MapParser` implementálása
  - Sorok és oszlopok számának beállítása konstruktorban
  - Ezáltal a parserünk dinamikusabb lesz
  - Tesztelni is egyszerűbb lesz
