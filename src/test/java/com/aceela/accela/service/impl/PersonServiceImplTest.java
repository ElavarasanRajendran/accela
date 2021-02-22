package com.aceela.accela.service.impl;

import com.aceela.accela.data.Address;
import com.aceela.accela.data.Person;
import com.aceela.accela.exception.AddressNotAvailableException;
import com.aceela.accela.exception.UserNotFoundException;
import com.aceela.accela.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    public void testAddPerson() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        when(personRepository.save(any())).thenReturn(person);

        Person newPerson = personService.addPerson(person);

        Assertions.assertNotNull(newPerson);
        Assertions.assertEquals(newPerson.getId(), person.getId());
        Assertions.assertEquals(newPerson.getFirstName(), person.getFirstName());
        Assertions.assertEquals(newPerson.getLastName(), person.getLastName());

        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void testGetPerson() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");

        when(personRepository.getById(person.getId())).thenReturn(person);

        Person newPerson = personService.getPerson("ID1");
        Assertions.assertNotNull(newPerson);
        Assertions.assertEquals(newPerson.getId(), person.getId());
        Assertions.assertEquals(newPerson.getFirstName(), person.getFirstName());
        Assertions.assertEquals(newPerson.getLastName(), person.getLastName());

        verify(personRepository, times(1)).getById(person.getId());

    }

    @Test
    public void testUpdatePersonDetails() {

        personService.updatePersonDetails("ID1", "John", "Mooney");

        verify(personRepository, times(1))
                .updatePersonDetails("ID1","John", "Mooney");
    }

    @Test
    public void testDeletePersonById() {
        personService.deletePersonById("ID1");

        verify(personRepository, times(1))
                .deleteById("ID1");

    }

    @Test
    public void testCountRowsOfPerson() {
        when(personRepository.count()).thenReturn(2L);
        long count = personService.countRowsOfPerson();
        Assertions.assertEquals(count, 2);

        verify(personRepository, times(1)).count();
    }

    @Test
    public void testGetAllPersons() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");

        Person person1 = new Person();
        person1.setId("ID1");
        person1.setFirstName("Steve");
        person1.setLastName("Smith");

        personList.add(person1);
        personList.add(person);

        when(personRepository.findAll()).thenReturn(personList);
        List<Person> personList1 = personService.getAllPersons();

        Assertions.assertEquals(personList1.size(), 2);
        verify(personRepository, times(1)).findAll();

        Assertions.assertNotNull(personList1);
        Assertions.assertNotNull(personList1.get(0));

        Assertions.assertEquals(personList1.get(0).getId(), person.getId());
        Assertions.assertEquals(personList1.get(0).getFirstName(), person.getFirstName());
        Assertions.assertEquals(personList1.get(0).getLastName(), person.getLastName());

        Assertions.assertEquals(personList1.get(1).getId(), person1.getId());
        Assertions.assertEquals(personList1.get(1).getFirstName(), person1.getFirstName());
        Assertions.assertEquals(personList1.get(1).getLastName(), person1.getLastName());
    }


    @Test
    public void testAddNewAddress_WithNoExistingAddressForPerson() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");

        when(personRepository.getById("ID1")).thenReturn(person);
        List<Address> addressList = new ArrayList<>();
        addressList.add(new
                Address("AD1", "Thomas Street", "Limerick", "Munster", "D02 AY1"));

        when(personRepository.save(person)).thenReturn(person);

        Person returnPerson = personService.addNewAddressForPerson("ID1", new
                Address("AD1", "Thomas Street", "Limerick", "Munster", "D02 AY1"));

        Assertions.assertNotNull(returnPerson);
        Assertions.assertNotNull(returnPerson.getAddressList());
        Assertions.assertEquals(returnPerson.getAddressList().size(), 1);

        Assertions.assertEquals(returnPerson.getAddressList().get(0).getId(), "AD1");
        Assertions.assertEquals(returnPerson.getAddressList().get(0).getStreet(), "Thomas Street");
        Assertions.assertEquals(returnPerson.getAddressList().get(0).getCity(), "Limerick");
        Assertions.assertEquals(returnPerson.getAddressList().get(0).getState(), "Munster");
        Assertions.assertEquals(returnPerson.getAddressList().get(0).getPostalCod(), "D02 AY1");

        verify(personRepository, times(1)).getById("ID1");
        verify(personRepository, times(1)).save(person);

    }

    @Test
    public void testAddNewAddress_WithExistingAddressForPerson() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        List<Address> addressList = new ArrayList<>();
        addressList.add(new
                Address("AD1", "Thomas Street", "Limerick", "Munster", "D02 AY1"));
        person.setAddressList(addressList);

        when(personRepository.getById("ID1")).thenReturn(person);

        Person newPerson = new Person();
        newPerson.setId("ID1");
        newPerson.setFirstName("Steve");
        newPerson.setLastName("Smith");
        List<Address> newAddressList = new ArrayList<>();
        newAddressList.add(new
                Address("AD1", "Thomas Street", "Limerick", "Munster", "D02 AY1"));

        newAddressList.add(new
                Address("AD2", "Kevin Street", "Cork", "Munster", "C02 CY1"));
        newPerson.setAddressList(newAddressList);

        when(personRepository.save(person)).thenReturn(newPerson);

        Person returnPerson = personService.addNewAddressForPerson("ID1", new
                Address("AD2", "Kevin Street", "Cork", "Munster", "C02 CY1"));

        Assertions.assertNotNull(returnPerson);
        Assertions.assertNotNull(returnPerson.getAddressList());
        Assertions.assertEquals(returnPerson.getAddressList().size(), 2);

        Assertions.assertEquals(returnPerson.getAddressList().get(0).getId(), "AD1");
        Assertions.assertEquals(returnPerson.getAddressList().get(0).getStreet(), "Thomas Street");
        Assertions.assertEquals(returnPerson.getAddressList().get(0).getCity(), "Limerick");
        Assertions.assertEquals(returnPerson.getAddressList().get(0).getState(), "Munster");
        Assertions.assertEquals(returnPerson.getAddressList().get(0).getPostalCod(), "D02 AY1");

        Assertions.assertEquals(returnPerson.getAddressList().get(1).getId(), "AD2");
        Assertions.assertEquals(returnPerson.getAddressList().get(1).getStreet(), "Kevin Street");
        Assertions.assertEquals(returnPerson.getAddressList().get(1).getCity(), "Cork");
        Assertions.assertEquals(returnPerson.getAddressList().get(1).getState(), "Munster");
        Assertions.assertEquals(returnPerson.getAddressList().get(1).getPostalCod(), "C02 CY1");

        verify(personRepository, times(1)).getById("ID1");
        verify(personRepository, times(1)).save(person);

    }

    @Test
    public void testAddNewAddressForPerson_ForUserNotFoundException() {

        when(personRepository.getById("ID1")).thenReturn(null);

        Assertions.assertThrows(UserNotFoundException.class, () -> personService.addNewAddressForPerson("ID1", new
                Address("AD2", "Kevin Street", "Cork", "Munster", "C02 CY1")));

        verify(personRepository, times(1)).getById("ID1");
    }

    @Test
    public void testDeleteAddressForUser_ForUserNotFoundException() {
        when(personRepository.getById("ID1")).thenReturn(null);

        Assertions.assertThrows(UserNotFoundException.class, () -> personService.deleteAddressForUser("ID1", "AD1"));
        verify(personRepository, times(1)).getById("ID1");
    }


    @Test
    public void testDeleteAddressForUser_ForNoAddressPresentException() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");

        when(personRepository.getById("ID1")).thenReturn(person);

        Assertions.assertThrows(AddressNotAvailableException.class, () -> personService.deleteAddressForUser("ID1", "AD1"));
        verify(personRepository, times(1)).getById("ID1");
    }

    @Test
    public void testDeleteAddressForUser_ForNoAddressPresent() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        List<Address> newAddressList = new ArrayList<>();
        newAddressList.add(new
                Address("AD1", "Thomas Street", "Limerick", "Munster", "D02 AY1"));
        person.setAddressList(newAddressList);

        when(personRepository.getById("ID1")).thenReturn(person);
        when(personRepository.save(any())).thenReturn(person);

        Person newPerson = personService.deleteAddressForUser("ID1", "AD1");
        Assertions.assertEquals(newPerson.getAddressList().size(),0);

        verify(personRepository, times(1)).getById("ID1");
        verify(personRepository,times(1)).save(person);
    }

    @Test
    public void testDeleteAddressForUser_ForAddressPresent() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        List<Address> newAddressList = new ArrayList<>();
        newAddressList.add(new
                Address("AD1", "Thomas Street", "Limerick", "Munster", "D02 AY1"));
        person.setAddressList(newAddressList);

        when(personRepository.getById("ID1")).thenReturn(person);


        Person person1 = new Person();
        person1.setId("ID1");
        person1.setFirstName("Steve");
        person1.setLastName("Smith");
        person1.setAddressList(new ArrayList<>());

        when(personRepository.save(any())).thenReturn(person1);

        Person newPerson = personService.deleteAddressForUser("ID1", "AD1");
        Assertions.assertEquals(newPerson.getAddressList().size(),0);

        verify(personRepository, times(1)).getById("ID1");
        verify(personRepository,times(1)).save(person1);
    }

    @Test
    public void testUpdateAddressForUser_ForUserNotFoundException() {
        when(personRepository.getById("ID1")).thenReturn(null);

        Assertions.assertThrows(UserNotFoundException.class, () -> personService.updateAddressForUser("ID1", new
                Address("AD1", "Thomas Street", "Limerick", "Munster", "D02 AY1") ));
        verify(personRepository, times(1)).getById("ID1");
    }


    @Test
    public void testUpdateAddressForUser_ForNoAddressPresentException() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");

        when(personRepository.getById("ID1")).thenReturn(person);

        Assertions.assertThrows(AddressNotAvailableException.class, () -> personService.updateAddressForUser("ID1", new
                Address("AD1", "Thomas Street", "Limerick", "Munster", "D02 AY1")));
        verify(personRepository, times(1)).getById("ID1");
    }

    @Test
    public void testUpdateAddressForUser_ForAddressPresent() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        List<Address> newAddressList = new ArrayList<>();
        newAddressList.add(new
                Address("AD1", "Thomas Street", "Limerick", "Munster", "D02 AY1"));
        person.setAddressList(newAddressList);

        when(personRepository.getById("ID1")).thenReturn(person);


        Person person1 = new Person();
        person1.setId("ID1");
        person1.setFirstName("Steve");
        person1.setLastName("Smith");
        person1.setAddressList(new ArrayList<>());
        List<Address> addressList = new ArrayList<>();
        addressList.add(new
                Address("AD1", "Kevin Street", "Cork", "Munster", "C02 CY1"));
        person1.setAddressList(addressList);

        when(personRepository.save(any())).thenReturn(person1);

        Person newPerson = personService.updateAddressForUser("ID1", new
                Address("AD1", "Kevin Street", "Cork", "Munster", "C02 CY1"));

        Assertions.assertEquals(newPerson.getAddressList().size(),1);

        verify(personRepository, times(1)).getById("ID1");
        verify(personRepository,times(1)).save(person1);
    }
}
