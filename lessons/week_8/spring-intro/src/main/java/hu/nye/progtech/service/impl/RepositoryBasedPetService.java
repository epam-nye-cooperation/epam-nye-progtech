package hu.nye.progtech.service.impl;

import java.util.List;

import hu.nye.progtech.entity.Pet;
import hu.nye.progtech.repository.PetRepository;
import hu.nye.progtech.service.PetService;

public class RepositoryBasedPetService implements PetService {

    private PetRepository petRepository;

    public RepositoryBasedPetService(PetRepository petRepository) {
        System.out.println("RepositoryBasedPetService is being created (in the constructor)");
        this.petRepository = petRepository;
    }

    public void init() {
        System.out.println("RepositoryBasedPetService is created");
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

}
