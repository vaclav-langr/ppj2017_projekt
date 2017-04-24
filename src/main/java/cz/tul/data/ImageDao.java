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
public class ImageDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(Image image) {
        image.setCreated(new Date());
        session().save(image);
    }

    public boolean exists(int imageId) {
        Criteria crit = session().createCriteria(Image.class);
        crit.add(Restrictions.idEq(imageId));

        Image image = (Image) crit.uniqueResult();
        return image != null;
    }

    public List<Image> getAllImages() {
        Criteria crit = session().createCriteria(Image.class);
        return crit.list();
    }

    public void update(Image image) {
        image.setUpdated(new Date());
        session().update(image);
    }

    public void deleteImages() {
        session().createQuery("DELETE FROM Tag").executeUpdate();
        session().createQuery("DELETE FROM ImageRating").executeUpdate();
        session().createQuery("DELETE FROM CommentRating").executeUpdate();
        session().createQuery("DELETE FROM Comment").executeUpdate();
        session().createQuery("DELETE FROM Image").executeUpdate();
    }
}
