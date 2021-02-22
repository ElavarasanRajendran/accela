package com.aceela.accela.data;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class PersonTest {
    @Test
    public void testPerson() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Person.class);
    }
}
