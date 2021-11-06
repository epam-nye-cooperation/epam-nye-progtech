# 1. hét, 2. óra
## Verzió kezelés / Git alapok
* GitHub regisztráció
* repo létrehozás
* command line, Tortoise Git, IDE

### Command line	
* git clone https://github.com/lamfalusy/nye-progtech.git
* git status
* [create a new file]
* git status
* git add .
* git status
* git commit "commit message"
* git status
* git push
* git status
* git log
* [check logs in UI]
* [modify in UI]
* git status
* git push
* git pull
* git log

### Tortoise Git		
* commit
* push
* pull
* show log
* clone

### IDE		
* commit
* [show gitignore]
    * .DS_Store
    * .idea
* push (login required)
* pull

## Java project kezelése tisztán JDK eszközökkel
* 	JDK / JRE / JVM
* 	java –version (Java verzió kiíratása)
* 	java Main.java (Main.java osztály lefuttatása)
* 	javac Main.java (Main.java osztály lefordítása köztes bájtkódra)
* 	[megnyitni NotePad++-ban a Main.class-t]
* 	javap -c Main.class (Lefordított Main.class osztály visszafejtése)
* 	java -cp . Main (Lefordított Main.class osztály lefuttatása)

* 	jar cf hello-world.jar Main.class
* 	[hello-world.jar megvizsgálása]
* 	java -cp hello-world.jar Main

* 	jar cfe hello-world.jar Main Main.class
* 	java -jar hello-world.jar
	
* 	[add Greeting Generator class]
* 	javac GreetingGenerator.java
* 	javac -cp . Main.java
* 	java -cp . Main
* 	jar cfe hello-world.jar Main Main.class GreetingGenerator.class
* 	java -jar hello-world.jar

* 	[https://github.com/indvd00m/java-ascii-render]
* 	javac -cp .;ascii-render-2.2.0.jar;ascii-render-api-2.2.0.jar Main.java
* 	java -cp .;ascii-render-2.2.0.jar;ascii-render-api-2.2.0.jar Main.java
* 	[jar kicsomagolása, com és fonts folder kirakása]
* 	jar cfm hello-world.jar MANIFEST.MF Main.class GreetingGenerator.class .\com\ .\fonts\

