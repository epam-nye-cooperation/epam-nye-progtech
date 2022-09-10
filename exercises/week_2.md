# Gyakorló feladat - 2. hét

## Témakör: Maven

Feladatok:

1. Csináljatok egy `hello-world.jar` futtatható JAR file-t, ami bekéri a neved és egy nyelvet, és az adott nyelven üdvözöl
1. Az alkalmazás legalább 3 nyelvet támogasson, köztük a magyart
	1. Ismeretlen nyelv esetén írjon ki hibát
	1. Ha a felhasználó nem ad meg nyelvet, akkor alapértelmezetten a magyar nyelvet használjuk
1. Példa input és output
    1. `java -jar hello-world.jar Csaba` -> `Szia Csaba!`
    1. `java -jar hello-world.jar Csaba hun` -> `Szia, Csaba!`
    1. `java -jar hello-world.jar Csaba eng` -> `Hello, Csaba!`
    1. `java -jar hello-world.jar Csaba haw` -> `Aloha, Csaba!`
1. Bónusz
	1. Oszd szét a fenti Maven projektet 2 külön részre
		1. Egy könyvtár
			1. Biztosít egy osztályt, benne egy metódussal, ami a kapott név és nyelv paraméterekből előállítja az üdvözlő üzenetet
		1. Egy futtatható alkalmazás
			1. A fenti könyvtárat függőségként használja
			1. Feladata, hogy az input paramétereket begyűjtse, majd a könyvtárunk metódusának továbbadja őket, mint paramétereket, és végül kiírja az eredményt
	1. A megoldást töltsd fel GitHub-ra
