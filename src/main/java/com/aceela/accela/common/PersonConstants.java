package com.aceela.accela.common;

public class PersonConstants {

    private PersonConstants() {
        // Prevent Instantiation;
    }

    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USER_NOT_FOUND_MESSAGE = "User id entered cannot be found. Please check the user id entered";
    public static final String ADDRESS_NOT_FOUND_MESSAGE = "Address cannot be found, Please enter the correct address id";

    public static final String GET_ALL_PERSON_QUERY_STRING = "SELECT * FROM Person";
    public static final String UPDATE_PERSON_DETAILS_BY_ID = "UPDATE Person PER set PER.firstName =:firstName, PER.lastName =:lastName where PER.id =:id";
}
