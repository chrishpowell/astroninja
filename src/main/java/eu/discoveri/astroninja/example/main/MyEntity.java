package eu.discoveri.astroninja.example.main;

//import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * MyEntity
 */
@Entity
public class MyEntity //implements Serializable
{
    //private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    private long id;
    private String name;

    MyEntity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
