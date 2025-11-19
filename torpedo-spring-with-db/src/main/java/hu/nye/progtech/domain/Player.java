package hu.nye.progtech.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public final class Player {

    private final String name;

    public Player(final String name) {
        this.name = name;
    }
}
