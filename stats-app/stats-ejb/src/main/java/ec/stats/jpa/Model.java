package ec.stats.jpa;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ecmodel")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] object;

    private String className;

    private Timestamp date;

    public Model() {
        // Default constructor
    }

    public Model(String name, byte[] object, String className, Timestamp date) {
        this.name = name;
        this.object = object;
        this.className = className;
        this.date = date;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getObject() {
        return object;
    }

    public void setObject(byte[] object) {
        this.object = object;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
