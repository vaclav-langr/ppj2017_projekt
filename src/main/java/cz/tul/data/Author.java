package cz.tul.data;

import javax.persistence.*;
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
    private Date registered;

    public Author(){}

    public Author(String userName) {
        this.userName = userName;
    }

    public Author(String userName, Date registered) {
        this.userName = userName;
        this.registered = registered;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getRegistered(){
        return registered;
    }

    public void setRegistered(Date registered){
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Author{" +
                "user_name='" + userName + '\'' +
                ", registered=" + registered +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        Author temp = (Author)obj;
        return getUserName().equals(temp.getUserName());
    }

    @PrePersist
    public void prePersist(){
        setRegistered(new Date());
    }
}
