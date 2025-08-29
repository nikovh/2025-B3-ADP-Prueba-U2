package com.develop.nvh;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {

    private final Calculadora calculadora = new Calculadora();
    
    @Test
    public void testSumar() {
        //Arrange
        int a = 2;
        int b = 3;
        int esperado = 5;

        //Act
        int resultado = calculadora.sumar(a, b);

        //Assert
        assertEquals(esperado, resultado);
    }

    @Test
    public void testRestar() {
        //Arrange
        int a = 5;
        int b = 3;
        int esperado = 2;

        //Act
        int resultado = calculadora.restar(a, b);

        //Assert
        assertEquals(esperado, resultado);
    }
}