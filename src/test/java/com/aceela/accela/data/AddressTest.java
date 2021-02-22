package com.aceela.accela.data;


import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class AddressTest {
    @Test
    public void testAddress() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Address.class);
    }
}
