# 8. hét, 1. óra

## Spring - 1. rész

### Rövid prezentáció

- Elérhető itt: [introduction_to_spring_framework.pptx](../../slides/introduction_to_spring_framework.pptx)

### Általános fogalmak átbeszélése

- Inversion of Control (IoC)
  - Fogalom átbeszélése
  - Különbség könyvtár és framework között
  - Példa: JUnit5 (`@Test`, `@BeforeEach`, `@AfterEach`)
- Dependency Injection (DI)
  - IoC egy lehetséges megvalósítása
  - DI fajtái
    - Constructor alapú
    - Setter alapú
    - Interface alapú
  - Egyszerű DI implementálás

### Spring Project

- Rövid ismertetés
- Hivatalos oldal megtekintése
  - https://spring.io/
- Moduláris felépítés
  - Számunkra fontos: Spring Framework - Core
- Nyílt forráskódú
  - https://github.com/spring-projects/

### Spring Framework

- A Spring IoC konténere
  - `BeanFactory` (nem fogjuk használni, de érdemes tudni)
  - `ApplicationContext`
- Bean fogalma
- Spring konfiguráció
  - XML konfiguráció (a kezdetektől fogva, esetünkben csak említés szintjén)
  - Annotáció alapú konfiguráció
  - Java konfiguráció
- Annotáció alapú konfiguráció
  - Spring stereotypes
    - `@Repository`
    - `@Service`
  - `AnnotationConfigApplicationContext`
    - Package név, mint konstruktor paraméter
- Bean elkérése az `ApplicationContext`-től
  - Típus alapján
  - Azonosító (és típus) alapján
  - Több azonos típusú bean esetén az ütközés kezelése
    - `@Primary` annotáció
    - Egyértelmű típus használata
    - Bean azonosító használata
