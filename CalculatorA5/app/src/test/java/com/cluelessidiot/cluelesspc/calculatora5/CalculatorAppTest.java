package com.cluelessidiot.cluelesspc.calculatora5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorAppTest {

    @Test
    public void isExpressionCorrect(){
        assertEquals(65,28*2+18/2);
    }

    @Test(timeout = 100)
    public void testForModulus(){ assertEquals(34,33+9%4); }

}
