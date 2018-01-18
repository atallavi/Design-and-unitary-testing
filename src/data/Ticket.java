package data;

import java.util.Objects;

public class Ticket {

    private String name;

    public Ticket (String name) {
        this.name = name;
    }

    @Override
    public String toString () {
        return this.name;
    }

    @Override
    public boolean equals (Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        if (this.name.equals(other.toString())) return true;
        return true;
    }
    @Override
    public int hashCode () {
        return Objects.hash(name);
    }
}
