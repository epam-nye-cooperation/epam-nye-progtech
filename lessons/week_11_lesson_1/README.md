# 11. hét, 1. óra

## JDBC - 3. rész

### Játékállás mentésének és felolvasásának befejezése

- Util osztály készítése a játékállás `String`-gé alakításához
  - `MapToStringUtil`
- Pálya beolvasásának refaktorálása
  - RawMap modell osztály bevezetése
  - A meglévő osztályok, interfacek átformálása ennek támogatására
    - `MapReader`
    - `MapParser`
    - `MapReaderFacade`
- `JdbcGameSavesRepository` implementálásának befejezése, kipróbálása
- Spring beanek létrehozása az újonnan született komponensekhez
  - Legfontosabbak az adatbázisműveletekhez kapcsolódó beanek: `RepositoryConfig`

### Megjegyzés

- A unit tesztek az idő hiányában nem lettek módosítva a refaktorálás után, de normál körülmények között ezeket is fixálni kell!
