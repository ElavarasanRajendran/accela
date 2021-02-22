package com.aceela.accela.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNotFoundExceptionTest {
    @Test
    public void testUserException() {
        try{
            throwUserNotFoundException();
        } catch (UserNotFoundException exception) {
            assertEquals("User not valid", exception.getMessage());

        }
    }

    private void throwUserNotFoundException() throws UserNotFoundException {
        throw new UserNotFoundException("User not valid");
    }
}
