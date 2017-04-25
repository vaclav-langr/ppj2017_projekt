package cz.tul.data;

import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="Author")
public class Author {

    @Id
    @Column(name="user_name")
    private String userName;

    @Column(name="registered")
    private LocalDateTime registered;

    public Author(){}

    public Author(String userName) {
        this.userName = userName;
    }

    public Author(String userName, LocalDateTime registered) {
        this.userName = userName;
        this.registered = registered;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getRegistered(){
        return registered;
    }

    public void setRegistered(LocalDateTime registered){
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Author{" +
                "userName='" + userName + '\'' +
                ", registered=" + registered +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!userName.equals(author.userName)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + (registered != null ? registered.hashCode() : 0);
        return result;
    }

    @PrePersist
    public void prePersist(){
        setRegistered(LocalDateTime.now());
    }
}
