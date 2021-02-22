package com.aceela.accela.service.impl;

import com.aceela.accela.common.PersonConstants;
import com.aceela.accela.data.Address;
import com.aceela.accela.data.Person;
import com.aceela.accela.exception.AddressNotAvailableException;
import com.aceela.accela.exception.UserNotFoundException;
import com.aceela.accela.repository.PersonRepository;
import com.aceela.accela.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override

    public Person getPerson(String id) {
       return personRepository.getById(id);
    }

    @Override
    public void updatePersonDetails(String id, String firstName, String lastName) {
        Person person = personRepository.getById(id);
        if(Objects.nonNull(person)) {
            throw new UserNotFoundException(PersonConstants.USER_NOT_FOUND_MESSAGE);
        }
        personRepository.updatePersonDetails(id, firstName, lastName);
    }

    @Override
    public void deletePersonById(String id) {
        Person person = personRepository.getById(id);
        if(Objects.nonNull(person)) {
            throw new UserNotFoundException(PersonConstants.USER_NOT_FOUND_MESSAGE);
        }
        personRepository.deleteById(id);
    }

    @Override
    public long countRowsOfPerson() {
        return personRepository.count();
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person addNewAddressForPerson(String id, Address address) {
        Person person = personRepository.getById(id);
        if(Objects.nonNull(person)) {
            List<Address> addressList = person.getAddressList();
            if (!Objects.nonNull(addressList)) {
                addressList = new ArrayList<>();
            }
            addressList.add(address);
            person.setAddressList(addressList);
        } else {
            throw new UserNotFoundException(PersonConstants.USER_NOT_FOUND_MESSAGE);
        }
        return personRepository.save(person);
    }

    @Override
    public Person deleteAddressForUser(String personId, String addressId) {
        Person person = personRepository.getById(personId);
        if(Objects.nonNull(person)) {
            List<Address> addressList = person.getAddressList();
            if (CollectionUtils.isEmpty(addressList)) {
                throw new AddressNotAvailableException(PersonConstants.ADDRESS_NOT_FOUND_MESSAGE);
            }
            addressList.removeIf(address -> address.getId().equalsIgnoreCase(addressId));
            person.setAddressList(addressList);
        } else {
            throw new UserNotFoundException(PersonConstants.USER_NOT_FOUND_MESSAGE);
        }
        return personRepository.save(person);
    }

    @Override
    public Person updateAddressForUser(String personId, Address updatedAddress) {
        Person person = personRepository.getById(personId);
        if(Objects.nonNull(person)) {
            List<Address> addressList = person.getAddressList();
            if(CollectionUtils.isEmpty(addressList)) {
                throw new AddressNotAvailableException(PersonConstants.ADDRESS_NOT_FOUND_MESSAGE);
            }
            addressList.stream().filter(Objects::nonNull).forEach(address -> {
                if(address.getId().equalsIgnoreCase(updatedAddress.getId())) {
                    address.setStreet(updatedAddress.getStreet());
                    address.setCity(updatedAddress.getCity());
                    address.setState(updatedAddress.getState());
                    address.setPostalCod(updatedAddress.getPostalCod());
                }
            });
        } else {
            throw new UserNotFoundException(PersonConstants.USER_NOT_FOUND_MESSAGE);
        }
        return personRepository.save(person);
    }
}
