
package address_book.interfaces.impls;

import address_book.interfaces.AddressBook;
import address_book.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAddressBook implements AddressBook {
    
    private ObservableList<Person> personList = FXCollections.observableArrayList(); 
    
    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    // используется, если применять бд
    public void update(Person person) {      
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }
    
    public ObservableList<Person> getPersonList() {
        return personList;
    } 
    
    public void print() {
        int number = 0;
        System.out.println();
        for(Person person : personList) {
            number++;
            System.out.println(
                number + 
                ") fio = " + person.getFio() + 
                "; phone = " + person.getPhone()
            );           
        }
    }
    
    public void fillTestData() {
        personList.add(new Person("Иван Петов", "234523523"));
        personList.add(new Person("Роман Иванов", "457323225"));
        personList.add(new Person("Антон Романов", "23434564"));
        personList.add(new Person("Джон Смит", "457443325"));
        personList.add(new Person("Джек Воробей", "3253535346"));
        personList.add(new Person("Алиса Селезнёва", "3633653535"));
        personList.add(new Person("Боб Майлс", "34643643663"));     
    }
     
} 
