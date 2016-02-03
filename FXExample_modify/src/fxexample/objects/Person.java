
package fxexample.objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    
    private StringProperty fio = new SimpleStringProperty("");
    private StringProperty phone = new SimpleStringProperty("");
    
    public Person() {}
    
    public Person(String fio, String phone) {
        this.fio = new SimpleStringProperty(fio);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getFio() {
        return fio.get();
    }

    public void setFio(String fio) {
        this.fio.set(fio);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    
    public StringProperty fio() {
        return fio;
    }
    
    public StringProperty phone() {
        return phone;
    }
    
    @Override
    public String toString() {
        return "Person{" + "fio=" + fio + ", phone=" + phone + '}';
    }
    
}
