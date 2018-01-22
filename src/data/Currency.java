package data;

import java.util.Objects;

/**
 * Currency class represents the name of a badge.
 */
public class Currency {

    private String name;

    /**
     * @param name the name of the the badge.
     */
    public Currency(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        return name.equals(currency.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString () {
        return this.name;
    }

}
