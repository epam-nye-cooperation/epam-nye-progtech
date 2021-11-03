package hu.nye.progtech;

import java.util.List;

import hu.nye.progtech.entity.Pet;
import hu.nye.progtech.repository.PetRepository;
import hu.nye.progtech.service.PetService;
import hu.nye.progtech.service.impl.DummyPetService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("hu.nye.progtech");

        PetRepository petRepository = applicationContext.getBean(PetRepository.class);

        List<Pet> petList = petRepository.findAll();
        System.out.println(petList);

        PetService petService = applicationContext.getBean(PetService.class);
        List<Pet> petList1 = petService.findAll();
        System.out.println(petList1);

        /*DummyPetService dummyPetService = applicationContext.getBean("dummyPetService", DummyPetService.class);
        System.out.println(dummyPetService.findAll());*/

    }

}
