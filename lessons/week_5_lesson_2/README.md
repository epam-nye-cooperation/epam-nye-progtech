# 5. hét, 2. óra

## Sudoku - 2. rész

### Parser kiegészítése

- A `MapParser` implementáció eddig naívan működött
- Előfordulhat, hogy a beolvasott pálya nem megfelelő
  - Nincs elég sora
  - Nincs elég oszlopa (soronként)
  - Nem csak 0-9-ig terjedő számokat tartalmaz
- A `MapParser` kiegészítése a fentebbi feltételek validálásával
- Hiba esetén egyedi, `MapParseException` dobása

### MapVO validálása

- Továbbra sem lehetünk biztosak abban, hogy a beolvasott és beparseolt pályánk érvényes
- Nem érvényes, ha nem teljesíti a Sudoku játék szabályait
  - Egy sorban egy szám többször is előfordul
  - Egy oszlopban egy szám többször is előfordul
  - Egy dobozban egy szám többször is előfordul
- Validációs logika
  - Vegyük a pálya egy adott sorát/oszlopát/dobozát, és ezen belül az összes számot, mint listát
  - Szűrjük ki a 0 elemeket a listából
  - Ha a fennmaradó listában csak különböző elemek vannak, érvényes a pálya adott része, ellenkező esetben nem
- A fenti feltételek ellenőrzésére validáció készítése
  - `MapValidator` interface
  - Az egyes implementációk fogják ellenőrizni az egyes feltételeket
    - `MapByRowValidator`
    - `MapByColumnValidator`
    - `MapByBoxValidator`
  - Validációs hiba esetén `MapValidationException` dobása
- Az újrahasználható műveletek kimozgatása külső, util osztályokba
  - `CollectionUtil`
  - `MapUtil`
- Egy új segéd modellosztály, mely a pálya dobozainak (3x3-as részeinek) határait írja le
