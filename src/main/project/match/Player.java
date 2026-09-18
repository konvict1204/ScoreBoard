package project.match;

import lombok.Getter;

import java.util.Objects;

public class Player {
    @Getter
    String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
    @Override
    public String toString() {
        return name;
    }
}
