package booman_fx.controller;

import java.util.Objects;

public class Pair {
    private final int x;
    private final int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair pair)) return false;
        return getX() == pair.getX() && getY() == pair.getY();
    }

    public boolean equals(int x, int y) {
        return (this.x == x && this.y == y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
