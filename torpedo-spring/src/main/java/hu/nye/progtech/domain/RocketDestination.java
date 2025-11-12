package hu.nye.progtech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class RocketDestination {

    private final int row;
    private final int col;

}
