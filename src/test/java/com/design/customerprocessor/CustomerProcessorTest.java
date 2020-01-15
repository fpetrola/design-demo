package com.design.customerprocessor;

import org.junit.Assert;
import org.junit.Test;

public class CustomerProcessorTest extends BaseTest {

    @Test
    public void testCustomerNameWithComma() throws Exception {

        CustomerDataSet dataSet = new CustomerDataSet("\"Chavo, del ocho\"", "Chimoltrufia", "Chompiras", "Corrientes", "Rivadavia", "Cordoba", "Santa fe", "100", "201", "2", "1000", "2000", "3000");

        String input = createInput(dataSet);
        String result = createResult(dataSet);

        String output = executeProcessor(input, result);

        Assert.assertEquals(result, output);
    }

    @Test
    public void testAddressNumberWithComma() throws Exception {

        CustomerDataSet dataSet = new CustomerDataSet("Chavo", "Chimoltrufia", "Chompiras", "Corrientes", "Rivadavia", "Cordoba", "Santa fe", "\"100\"", "201", "2", "1000", "2000", "3000");

        String input = createInput(dataSet);
        String result = createResult(dataSet);

        String output = executeProcessor(input, result);

        Assert.assertEquals(result, output);
    }

    @Test(expected = RuntimeException.class)
    public void testSQlInjectionFromCsv() throws Exception {

        String result;
        String output;
        CustomerDataSet dataSet = new CustomerDataSet("Chavo", "Chimoltrufia", "Chompiras", "Corrientes", "Rivadavia", "Cordoba", "Santa fe", "100", "201", "9' or '1'='1", "1000", "2000", "3000");

        String input = createInput(dataSet);
        result = createResult(dataSet);

        output = executeProcessor(input, result);
    }

    @Test
    public void testPlainCsv() throws Exception {

        CustomerDataSet dataSet = getDefaultDataSet();

        String input = createInput(dataSet);
        String result = createResult(dataSet);

        String output = executeProcessor(input, result);

        Assert.assertEquals(result, output);
    }

}