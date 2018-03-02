package l10.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    @Column
    String name;

    @Column
    int age;

    @OneToOne(cascade = {CascadeType.ALL})
    AddressDataSet address;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

    public AddressDataSet getAddress() {
        return this.address;
    }
}
