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
    private String user_name;

    @Column(name="registered")
    private Date registered;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Image> imageSet;

    public Author(){}

    public Author(String user_name) {
        this.user_name = user_name;
    }

    public Author(String user_name, Date registered) {
        this.user_name = user_name;
        this.registered = registered;
    }

    public String getUser_name(){
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
                "user_name='" + user_name + '\'' +
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
        return getUser_name().equals(temp.getUser_name());
    }

    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }
}
