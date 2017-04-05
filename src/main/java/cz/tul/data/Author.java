package cz.tul.data;

import java.util.Date;

/**
 * Created by vaclavlangr on 03.04.17.
 */
public class Author {
    private String user_name;
    private Date registered;

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
}
