# 10. hét, 1. óra

## JDBC - 1. rész

### Rövid prezentáció

- Elérhető itt: [introduction_to_jdbc.pptx](../../slides/introduction_to_jdbc.pptx)

### SQL lekérdezések a példa adatbázishoz

- Elérhető itt: [db_init.sql](./db_init.sql)

### Általános JDBC ismertető

- Mi az a JDBC?
  - Java DataBase Connectivity
- JDBC architektúra átbeszélése
  - 2 fő réteg
    - JDBC API: Java app - JDBC manager
    - JDBC Driver API: JDBC manager - Driver
  - Legfontosabb komponensek
    - DriverManager
    - Driver
    - Connection
    - Statement
    - ResultSet
    - SQLException
- Egy JDBC alkalmazás folyama

### A H2 adatbázis ismertetése

- Egy könnyed, nyílt forráskódú Java adatbázis
- Ideális teszteléshez
- Különöző módok
  - Embedded
  - In-memory
  - Server
- https://www.h2database.com/

### H2 telepítése és használata

- H2 Console beindítása
- Új adatbázis létrehozása
- Csatlakozás H2 adatbázishoz a H2 Console segítségével
- Rövid SQL ismétlés, adatbázis inicializálása

### Egyszerű példa alkalmazás készítése

- Maven projekt
- H2 driver beszerzése
  - https://mvnrepository.com/artifact/com.h2database/h2
- Csatlakozás H2 adatbázishoz
  - Embedded móddal
  - Server móddal (a korábban a H2 Console-al létrehozott adatbázishoz)
- SQL lekérdezések futtatása JDBC segítségével
