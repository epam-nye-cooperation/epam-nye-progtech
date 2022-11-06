# Gyakorló feladat - 10. hét

## Témakör: JDBC, SQL

Feladatok:

1. Fejleszd tovább a `hello-world` alkalmazást úgy, hogy az üdvözlő szövegek előtagja adatbázisból legyen kiolvasva!
1. Add hozzá a H2 drivert mint dependency-t a projekthez
1. Az adatbázis felépítése
	1. Adott 1 tábla, ami legalább 2 mezőt tartalmaz, ezek a
		1. `language` : egy adott nyelv kódja (pl.: `hun`, `eng`, stb.)
		1. `greeting` : az üdvözlő üzenet adott nyelven (pl.: a `hun` nyelvkód esetén ez lehet `Szia, `)
1. Így a teljes üdvözlő üzenet előállításának folyamata
	1. Parancssori argumentumként adott a nyelvkód és egy név
	1. Üdvözlő üzenet előtagjának lekérése az adatbázisból, a nyelvkód alapján
	1. Üdvözlő üzenet előtagját összefűzve a kapott névvel, megkapjuk a teljes üdvözlő üzenetet
