/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.model;

import java.util.Objects;

/**
 *
 * @author Rodrigo Arze
 */
public final class Car {

    private final double combustibleWantedInCash;
    private final String combustibleType;

    public Car(int cash, String combustibleType) {
        this.combustibleWantedInCash = cash;
        this.combustibleType = combustibleType;
    }

    public double getCombustibleWantedInCash() {
        return combustibleWantedInCash;
    }

    public String getCombustibleType() {
        return combustibleType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.combustibleWantedInCash) ^ (Double.doubleToLongBits(this.combustibleWantedInCash) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.combustibleType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (Double.doubleToLongBits(this.combustibleWantedInCash) != Double.doubleToLongBits(other.combustibleWantedInCash)) {
            return false;
        }
        return Objects.equals(this.combustibleType, other.combustibleType);
    }
}
