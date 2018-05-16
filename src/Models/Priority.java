package Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Priority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long ID;
    private String name;

    public Priority(){ }

    public Priority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Priority{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
