package hu.nye.progtech.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public final class Player {

    private String name;

    public Player(final String name) {
        this.name = name;
    }
}
