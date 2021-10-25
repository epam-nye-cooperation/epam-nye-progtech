package hu.nye.progtech.service.impl;

import java.util.List;

import hu.nye.progtech.entity.Pet;
import hu.nye.progtech.repository.PetRepository;
import hu.nye.progtech.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RepositoryBasedPetService implements PetService {

    private PetRepository petRepository;

    @Autowired
    public RepositoryBasedPetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

}
