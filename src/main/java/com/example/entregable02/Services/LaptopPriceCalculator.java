package com.example.entregable02.Services;

import com.example.entregable02.Entities.Laptop;

public class LaptopPriceCalculator {

    public double calculatorPrice(Laptop laptop){
        double igv = 0.18;
        return laptop.getPrice() + laptop.getPrice() * igv;
    }
}
