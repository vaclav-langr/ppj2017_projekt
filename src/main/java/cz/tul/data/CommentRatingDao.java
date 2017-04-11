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
public class CommentRatingDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }
    @Transactional
    public void create(CommentRating rating) {
        session().save(rating);
    }

    public boolean exists(Comment comment_id, Author comment_rating_author) {
        Criteria crit = session().createCriteria(CommentRating.class);
        crit.add(Restrictions.eq("comment", comment_id));
        crit.add(Restrictions.eq("comment_rating_author", comment_rating_author));

        CommentRating commentRating = (CommentRating) crit.uniqueResult();
        return commentRating != null;
    }

    public List<CommentRating> getAllCommentRatings() {
        Criteria crit = session().createCriteria(CommentRating.class);
        return crit.list();
    }

    public void deleteCommentRatings() {
        session().createQuery("DELETE FROM CommentRating").executeUpdate();
    }
}
