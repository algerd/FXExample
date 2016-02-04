package code.makery.address.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the list of persons to XML.
 * JAXB requires the top most class to be annotated with @XmlRootElement. 
 * personData is of class ObservableList and we can't put any annotations to ObservableList. 
 * So we need to create another class that is only used to hold our list of Persons for saving to XML.
 * @XmlRootElement defines the name of the root element.
 * @XmlElement is an optional name we can specify for the element.
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Person> persons;

    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}