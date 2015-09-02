package people;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import Utils.PasswordEncoder;

/**
 * Created by Pikachu on 9/1/15.
 */
@Entity
@Table(name = "identity")
public class Identity {
    @Id // Id is required to identify the primary key in the table
    private String userName;

    private String password;

    private String role;

    public Identity() {};

    public Identity(String userName, String password, String role) {
        this.userName = userName;
        this.role = role;
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        this.password = passwordEncoder.encodePassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return role;
    }

    public void setAuthority(String authority) {
        this.role = authority;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
