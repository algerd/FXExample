
package address_book.interfaces;

import address_book.objects.Person;

public interface AddressBook {
    
    // добавить запись
    void add(Person person);
    
    // изменить запись
    void update(Person person);
    
    // удалить запись
    void delete(Person person);
    
}
