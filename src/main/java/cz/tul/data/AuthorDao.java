package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Transactional
public class AuthorDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(Author author) {
        author.setRegistered(new Date());
        session().save(author);
    }

    public boolean exists(String user_name) {
        Criteria crit = session().createCriteria(Author.class);
        crit.add(Restrictions.idEq(user_name));
        Author author = (Author) crit.uniqueResult();
        return author != null;
    }

    public List<Author> getAllAuthors() {
        Criteria crit = session().createCriteria(Author.class);
        return crit.list();
    }

    public void deleteAuthors() {
        session().createQuery("DELETE FROM Image_Author").executeUpdate();
        session().createQuery("DELETE FROM Comment_Author").executeUpdate();
        session().createQuery("DELETE FROM Comment").executeUpdate();
        session().createQuery("DELETE FROM Image").executeUpdate();
        session().createQuery("DELETE FROM Author").executeUpdate();
    }
}
