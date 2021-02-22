package com.aceela.accela.service;

import com.aceela.accela.data.Address;
import com.aceela.accela.data.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {

    Person addPerson(Person person);

    Person getPerson(String id);

    void updatePersonDetails(String id, String firstName, String lastName);

    void deletePersonById(String id);

    long countRowsOfPerson();

    List<Person> getAllPersons();

    Person addNewAddressForPerson(String id, Address address);

    Person deleteAddressForUser(String personId, String addressId);

    Person updateAddressForUser(String personId, Address updatedAddress);
}
