package hu.nye.progtech.configuration;

import hu.nye.progtech.entity.PetMicroChip;
import hu.nye.progtech.repository.PetRepository;
import hu.nye.progtech.service.PetService;
import hu.nye.progtech.service.impl.RepositoryBasedPetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PetRepository petRepository(PetMicroChip petMicroChip) {
        System.out.println(petMicroChip);
        return new PetRepository();
    }

    @Bean(initMethod = "init")
    public PetService petService(PetRepository petRepository, PetMicroChip petMicroChip) {
        System.out.println(petMicroChip);
        return new RepositoryBasedPetService(petRepository);
    }

}
