package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Transactional
public class TagDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(Tag tag) {
        session().save(tag);
    }

    public boolean exists(Image image, String tag) {
        Criteria crit = session().createCriteria(Tag.class);
        crit.add(Restrictions.eq("image", image));
        crit.add(Restrictions.eq("tag", tag));

        Tag t = (Tag) crit.uniqueResult();
        return t != null;
    }

    public List<Tag> getAllTags() {
        Criteria crit = session().createCriteria(Tag.class);
        return crit.list();
    }

    public void deleteTags() {
        session().createQuery("DELETE FROM Tag").executeUpdate();
    }
}
