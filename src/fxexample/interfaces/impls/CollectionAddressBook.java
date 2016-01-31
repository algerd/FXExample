
package fxexample.interfaces.impls;

import fxexample.interfaces.AddressBook;
import fxexample.objects.Person;
import java.util.ArrayList;

public class CollectionAddressBook implements AddressBook {
    
    private ArrayList<Person> personList = new ArrayList<>(); 

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
    
    public ArrayList<Person> getPersonList() {
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
        personList.add(new Person("Иван", "234523523"));
        personList.add(new Person("Роман", "457323225"));
        personList.add(new Person("Антон", "23434564"));
        personList.add(new Person("Джон", "457443325"));
        personList.add(new Person("Джек", "3253535346"));
        personList.add(new Person("Алиса", "3633653535"));
        personList.add(new Person("Боб", "34643643663"));     
    }
     
} 
