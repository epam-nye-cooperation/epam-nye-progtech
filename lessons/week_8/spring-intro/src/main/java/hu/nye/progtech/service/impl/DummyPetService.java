package hu.nye.progtech.service.impl;

import java.util.List;

import hu.nye.progtech.entity.Pet;
import hu.nye.progtech.service.PetService;

public class DummyPetService implements PetService {

    @Override
    public List<Pet> findAll() {
        return null;
    }

}
