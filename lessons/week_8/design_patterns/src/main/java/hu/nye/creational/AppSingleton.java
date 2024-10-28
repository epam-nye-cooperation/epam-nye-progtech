package hu.nye.creational;

import com.github.javaparser.ast.CompilationUnit;
import hu.nye.behavioral.AirTrafficControlMediator;
import hu.nye.behavioral.CarTemplated;
import hu.nye.behavioral.JavaSourceVisitor;
import hu.nye.behavioral.LogChainOfResponsibility;
import hu.nye.structural.PlayerAdapter;
import hu.nye.structural.bridge.*;
import hu.nye.structural.composite.Circle;
import hu.nye.structural.composite.CompositeGraphic;
import hu.nye.structural.composite.Square;
import hu.nye.structural.proxy.BankAccountImpl;
import hu.nye.structural.proxy.BankAccountProxy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static hu.nye.behavioral.LogChainOfResponsibility.Logger.Level.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppSingleton {
    @Getter(lazy = true)
    private static final AppSingleton INSTANCE = new AppSingleton();

    public static void main(String[] args) {
        getINSTANCE().creationalSingleton();
    }

    private void creationalSingleton() {
        creationalFactory();
        creationalBuilderPrototype();
        structuralAdapter();
        structuralComposite();
        structuralProxy();
        structuralBridge();
        behavioralTemplateMethod();
        behavioralMediator();
        behavioralChainOfResponsibility();
        behavioralIterator();
        behavioralVisitor();
    }

    private void creationalFactory() {
        System.out.println("-------------- creationalFactory ------------");
        DocumentEditorFactory.DocumentEditor editor = DocumentEditorFactory.create();
        //editor.open(new File("C:\\Users\\tamas_horvath\\workspace\\udv-dk\\pom.xml"));
        System.out.println("Your editor is: " + editor.getExecutable());
    }

    private void creationalBuilderPrototype() {
        System.out.println("-------------- creationalBuilderPrototype ------------");
        Document doc = Document.builder()
                .title("a")
                .body("World")
                .build();
        Document other = doc.clone();
        //other.setBody("Világ");
        System.out.println(other);
        System.out.println(doc);
        System.out.println(doc == other);
    }

    private void structuralAdapter() {
        System.out.println("-------------- structuralAdapter ------------");
        PlayerAdapter player = new PlayerAdapter();
        player.play(new File("A.vlc"));
        player.play(new File("A.mp4"));
    }

    private void structuralComposite() {
        System.out.println("-------------- structuralComposite ------------");
        CompositeGraphic graphic1 = new CompositeGraphic("one", List.of(new Circle(2), new Square(3)));
        CompositeGraphic graphic2 = new CompositeGraphic("two", List.of(new Circle(5), new Square(6)));
        CompositeGraphic graphicAll = new CompositeGraphic("all", List.of(graphic1, graphic2));
        graphicAll.draw();
        graphic1.draw();
    }

    @SneakyThrows
    private void structuralProxy() {
        System.out.println("-------------- structuralProxy ------------");
        BankAccountImpl account = new BankAccountImpl();
        BankAccountProxy ownerProxy = new BankAccountProxy(account, BankAccountProxy.Role.OWNER);
        BankAccountProxy readerProxy = new BankAccountProxy(account, BankAccountProxy.Role.READER);
        ownerProxy.setPII("Béla");
        try {
            ownerProxy.transact(BigDecimal.valueOf(-10));
            System.err.println("Balance went negative? " + account);
        } catch (IllegalStateException e) {
            System.out.println(ownerProxy + ": " + e.getMessage());
        }
        readerProxy.transact(BigDecimal.valueOf(100));
        try {
            readerProxy.transact(BigDecimal.valueOf(-1));
            System.err.println("Reader could remove balance!");
        } catch (IllegalAccessException e) {
            System.out.println(readerProxy + ": " + e.getMessage());
        }
        ownerProxy.transact(BigDecimal.valueOf(-30));
    }

    private void structuralBridge() {
        System.out.println("-------------- structuralBridge ------------");
        TV sony = new SonyTV();
        TV samsung = new SamsungTV();
        TVControl basicSonyRc = new BasicRemote(sony);
        TVControl advSamsungRc = new AdvancedRemote(samsung);

        advSamsungRc.powerOn();
        advSamsungRc.setChannel(83);
        advSamsungRc.setVolume(34);
        advSamsungRc.setChannel(82);
        advSamsungRc.setVolume(33);
        advSamsungRc.powerOff();
        advSamsungRc.powerOn();

        basicSonyRc.powerOn();
        basicSonyRc.setVolume(15);
        basicSonyRc.setChannel(55);
        basicSonyRc.powerOff();
        basicSonyRc.powerOn();
    }

    private void behavioralTemplateMethod() {
        System.out.println("-------------- behavioralTemplateMethod ------------");
        CarTemplated car = new CarTemplated() {
            @Override
            public void use() {
                System.out.print("see the city... ");
            }
        };
        car.drive();
        CarTemplated tweaked = new CarTemplated() {
            @Override
            public boolean start() {
                boolean nitroOk = RandomUtils.nextBoolean();
                if (nitroOk) {
                    System.out.print("** turn on nitro fuel ** ");
                    super.start();
                } else {
                    System.out.print("you notice nitro tank is empty... ");
                }
                return nitroOk;
            }

            public void use() {
                System.out.print("win formula one... ");
            }
        };
        tweaked.drive();
    }

    private void behavioralMediator() {
        System.out.println("-------------- behavioralMediator ------------");
        AirTrafficControlMediator atc = new AirTrafficControlMediator();

        AirTrafficControlMediator.Airplane boeing737 = atc.new Airplane("Boeing737");
        AirTrafficControlMediator.Airplane airbusA320 = atc.new Airplane("AirbusA320");
        AirTrafficControlMediator.Airplane cessna172 = atc.new Airplane("Cessna172");

        boeing737.send("Climbing to 10,000 ft.");
        airbusA320.send("Descending to 5,000 ft.");
        cessna172.send("On final approach, runway 27.");
    }

    private void behavioralChainOfResponsibility() {
        System.out.println("-------------- behavioralChainOfResponsibility ------------");
        LogChainOfResponsibility.Logger logger = LogChainOfResponsibility.getLogger();

        logger.logMessage(INFO, "This is an information.");
        logger.logMessage(DEBUG, "This is a debug level information.");
        logger.logMessage(ERROR, "This is an error information.");
    }

    private void behavioralVisitor() {
        System.out.println("-------------- behavioralVisitor ------------");
        JavaSourceVisitor printer = new JavaSourceVisitor();
        CompilationUnit unit = printer.getMySource();
        printer.visit(unit, null);
    }

    private void behavioralIterator() {
        System.out.println("-------------- behavioralIterator ------------");
        Iterable<?> iterable = Arrays.asList("A,B,C".split(","));
        Iterator<?> iter = iterable.iterator();
        int iteration = 0;
        while (iter.hasNext()) {
            iteration++;
            System.out.print("Item " + iteration + " = " + iter.next() + ", ");
        }
        System.out.println("finished\nCustom iterator for numbers 1..10");
        iter = new Iterator<Integer>() {
            int start = 0;

            @Override
            public Integer next() {
                return ++start;
            }

            @Override
            public boolean hasNext() {
                return start < 10;
            }
        };
        while (iter.hasNext()) {
            System.out.print(iter.next() + ", ");
        }
        System.out.println("finished");
    }
}
