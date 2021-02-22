package com.aceela.accela;

import com.aceela.accela.data.Address;
import com.aceela.accela.data.Person;
import com.aceela.accela.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

@Service
@Slf4j
public class Client implements CommandLineRunner {

    private final PersonService personService;

    public Client(final PersonService personService) {
        this.personService = personService;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n1. Add Person \n 2. Edit Person \n 3.Delete Person \n4.Add Address to Person \n5.Edit Address \n6.Delete Address \n7.Count number of Persons \n8. List All Persons \n9. Exit \n");
        System.out.println("Enter Choice:");
        Scanner choiceSc= new Scanner(System.in);
        int choice= choiceSc.nextInt();
        while(choice != 9) {
            Scanner sc= new Scanner(System.in);
            switch (choice) {
                case 1: {
                    Person person = new Person();
                    System.out.println("Enter Person Id:");
                    String id = sc.nextLine();
                    person.setId(id);
                    System.out.println("Enter the Person First Name");
                    String firstName = sc.nextLine();
                    person.setFirstName(firstName);
                    System.out.println("Enter the Person Last Name");
                    String lastName = sc.nextLine();
                    person.setLastName(lastName);
                    personService.addPerson(person);
                    break;
                }
                case 2: {
                    System.out.println("Enter Person Id to be updated:");
                    String personId  = sc.nextLine();
                    System.out.println("Enter the new First Name:");
                    String firstName = sc.nextLine();
                    System.out.println("Enter the new last Last Name:");
                    String lastName = sc.nextLine();

                    personService.updatePersonDetails(personId, firstName, lastName);
                    System.out.println("The person details after update is"+ personService.getPerson(personId));
                    break;
                }
                case 3: {
                    System.out.println("Enter the person Id to be deleted:");
                    String personId  = sc.nextLine();
                    personService.deletePersonById(personId);
                    break;
                }
                case 4: {
                    System.out.println("Enter person Id for whom address needs to be added:");
                    String personId  = sc.nextLine();
                    Address address = new Address();
                    System.out.println("Enter the Address ID:");
                    address.setId(sc.nextLine());
                    System.out.println("Enter the Street:");
                    address.setStreet(sc.nextLine());
                    System.out.println("Enter the City:");
                    address.setCity(sc.nextLine());
                    System.out.println("Enter the State:");
                    address.setState(sc.nextLine());
                    System.out.println("Enter the Postal Code:");
                    address.setPostalCod(sc.nextLine());

                    Person person = personService.addNewAddressForPerson(personId, address);
                    System.out.println("Person Details after Adding new address is: "+ person.toString());
                    break;
                }
                case 5: {
                    System.out.println("Enter the person Id for which the address is to be updated: ");
                    String personId  = sc.nextLine();
                    Address address = new Address();
                    System.out.println("Enter the Address ID to be updated:");
                    address.setId(sc.nextLine());
                    System.out.println("Enter the New Street :");
                    address.setStreet(sc.nextLine());
                    System.out.println("Enter the New City:");
                    address.setCity(sc.nextLine());
                    System.out.println("Enter the New State:");
                    address.setState(sc.nextLine());
                    System.out.println("Enter the New Postal Code:");
                    address.setPostalCod(sc.nextLine());

                    Person person = personService.updateAddressForUser(personId, address);
                    System.out.println("Person Details after updating the address is:"+ person.toString());
                    break;
                }
                case 6: {
                    System.out.println("Enter the person Id for which the address is to be deleted:");
                    String personId  = sc.nextLine();
                    System.out.println("Enter the Address ID to be Deleted:");
                    String addressId = sc.nextLine();

                    Person person = personService.deleteAddressForUser(personId, addressId);
                    System.out.println("Person Details after deleting the address is: "+ person.toString());
                    break;
                }
                case 7: {
                    System.out.println("The number of persons are: " + personService.countRowsOfPerson());
                    break;
                }
                case 8: {
                    List<Person> personList = personService.getAllPersons();
                    if(CollectionUtils.isEmpty(personList)) {
                        System.out.println("No persons available to be displayed");
                        break;
                    } else {
                        System.out.println("The person details are:");
                        personList.stream().filter(Objects::nonNull).forEach( person -> {
                            System.out.println(person.toString());
                        });
                        break;
                    }
                }
                case 9: {
                    System.out.println("Exiting the program");
                    exit(0);
                }
                default: {
                    System.out.println("Invalid choice");
                    break;
                }
            }
            System.out.println("1. Add Person \n 2. Edit Person \n 3.Delete Person \n4.Add Address to Person \n5.Edit Address \n6.Delete Address \n7.Count number of Persons \n8. List All Persons \n9. Exit \n");
            System.out.println("Enter Choice:");
            choice= sc.nextInt();
        }

    }
}
