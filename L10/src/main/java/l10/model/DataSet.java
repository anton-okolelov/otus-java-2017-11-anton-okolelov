package l10.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract public class DataSet {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

}
