package hu.nye.progtech.repository;

import java.util.Collections;
import java.util.List;

import hu.nye.progtech.entity.Pet;
import org.springframework.stereotype.Repository;

@Repository
public class PetRepository {

    public List<Pet> findAll() {
        return Collections.emptyList();
    }

}
