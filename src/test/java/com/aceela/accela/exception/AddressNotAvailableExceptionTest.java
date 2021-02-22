package com.aceela.accela.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressNotAvailableExceptionTest {
    @Test
    public void testUserException() {
        try{
            throwAddressNotAvailableException();
        } catch (Exception exception) {
            assertEquals("Address not valid", exception.getMessage());

        }
    }

    private void throwAddressNotAvailableException() throws AddressNotAvailableException {
        throw new AddressNotAvailableException("Address not valid");
    }
}
