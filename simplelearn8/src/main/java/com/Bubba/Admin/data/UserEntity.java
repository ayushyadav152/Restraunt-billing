package com.Bubba.Admin.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 7950128034037178224L;
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length=50)
    private String firstName;

    @Column()
    private String lastName;



    @Column(nullable=false, length=120, unique=true)
    private String email;

    @Column(nullable = false)
    private String Role;

    @Column(nullable=false, unique=true)
    private String userId;

    @Column(nullable=false, unique=true)
    private String Password;

    @Column
    private String encryptedPassword;

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
}