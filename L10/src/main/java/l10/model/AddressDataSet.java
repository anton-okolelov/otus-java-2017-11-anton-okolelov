package l10.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressDataSet extends DataSet {
    @Column
    private String street;

    public AddressDataSet() {

    }

    public AddressDataSet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
