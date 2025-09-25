package cz.cvut.fel;

import java.util.Objects;

public class Coord {
    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord copy(){
        return new Coord(x, y);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void moveRight(){
        this.x += 1;
    }

    public void moveLeft(){
        this.x -= 1;
    }

    public void moveUp(){
        this.y -= 1;
    }

    public void moveDown(){
        this.y += 1;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coord{" + "x=" + x + ", y=" + y + '}';
    }
}
