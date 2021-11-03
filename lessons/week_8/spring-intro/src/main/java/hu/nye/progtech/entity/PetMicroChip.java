package hu.nye.progtech.entity;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "singleton")
public class PetMicroChip {

    private UUID chipId;

    public PetMicroChip() {
        this.chipId = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "PetMicroChip{" +
                "chipId=" + chipId +
                '}';
    }
}
