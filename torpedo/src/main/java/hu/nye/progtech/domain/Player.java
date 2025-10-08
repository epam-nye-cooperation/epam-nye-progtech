package hu.nye.progtech.domain;

public final class Player {

    private final String name;

    public Player(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
            "name='" + name + '\'' +
            '}';
    }
}
