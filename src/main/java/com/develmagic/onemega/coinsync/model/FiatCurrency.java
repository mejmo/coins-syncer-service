package com.develmagic.onemega.coinsync.model;

public enum FiatCurrency {
    USD("USD"), EUR("EUR");

    String name;

    FiatCurrency(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
