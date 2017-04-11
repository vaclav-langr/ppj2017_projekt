package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Transactional
public class ImageRatingDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(ImageRating rating) {
        session().save(rating);
    }

    public boolean exists(Image image, Author image_rating_author) {
        Criteria crit = session().createCriteria(ImageRating.class);
        crit.add(Restrictions.eq("image", image));
        crit.add(Restrictions.eq("image_rating_author", image_rating_author));

        ImageRating rating = (ImageRating) crit.uniqueResult();
        return rating != null;
    }

    public List<ImageRating> getAllImageRatings() {
        Criteria crit = session().createCriteria(ImageRating.class);
        return crit.list();
    }

    public void deleteImageRatings() {
        session().createQuery("DELETE FROM ImageRating").executeUpdate();
    }
}
