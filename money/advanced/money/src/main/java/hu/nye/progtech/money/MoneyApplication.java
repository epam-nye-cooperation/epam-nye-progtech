package hu.nye.progtech.money;

import hu.nye.progtech.money.domain.Money;
import hu.nye.progtech.money.service.MoneyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Currency;

@SpringBootApplication
public class MoneyApplication {

    public static void main(String[] args) {
// Példa arra, hogy hogyan tudunk elkérni egy Spring Bean-t/objektumot és hazsnálni azt.
//        ConfigurableApplicationContext context = SpringApplication.run(MoneyApplication.class, args);
//        MoneyService moneyService = context.getBean(MoneyService.class);
//        Money money1 = new Money(1000.0, Currency.getInstance("HUF"));
//        Money money2 = new Money(1.0, Currency.getInstance("USD"));
//        Money money3 = new Money(1.0, Currency.getInstance("USD"));
//        System.out.println(moneyService.add(money1, money2));
//        System.out.println(moneyService.add(money1, money3));

        SpringApplication.run(MoneyApplication.class, args);
    }

}
