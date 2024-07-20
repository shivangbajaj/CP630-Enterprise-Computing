package ec.stats.jpa;

import javax.persistence.*;

@Entity
@Table(name = "ecuser")
public class User {
    
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "role")
    private int role;

    public User() {
		// TODO Auto-generated constructor stub
	}
    
    // Getters and setters
    public User(String name, String password, int role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
