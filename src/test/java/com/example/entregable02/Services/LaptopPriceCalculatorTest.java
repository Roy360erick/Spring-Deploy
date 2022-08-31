package com.example.entregable02.Services;

import com.example.entregable02.Entities.Laptop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class LaptopPriceCalculatorTest {

    @Test
    void calculatorPrice() {
        Laptop laptop = new Laptop("Lenovo","4GB","1TB",1000, LocalTime.now());

        LaptopPriceCalculator calculator = new LaptopPriceCalculator();
        double result = calculator.calculatorPrice(laptop);

        System.out.println(result);
        assertTrue(result > 0);
        assertEquals(1180.0,result);

    }
}