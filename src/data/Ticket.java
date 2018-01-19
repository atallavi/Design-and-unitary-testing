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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return name.equals(ticket.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
