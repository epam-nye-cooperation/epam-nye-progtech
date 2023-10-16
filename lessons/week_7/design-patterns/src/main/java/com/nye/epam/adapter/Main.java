package com.nye.epam.adapter;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Peg example

        RoundHole roundHole = new RoundHole(100);
        RoundPeg roundPeg = new RoundPeg(99);

        if (roundHole.fits(roundPeg)) {
            System.out.println("Round fits round!");
        }

        SquarePeg squarePeg = new SquarePeg(5);

        SquarePegAdapter adapter = new SquarePegAdapter(squarePeg);

        if (roundHole.fits(adapter)) {
            System.out.println("Square fits round!");
        }

    }

}
