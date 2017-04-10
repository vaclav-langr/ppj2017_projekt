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
public class CommentDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(Comment comment) {
        comment.setCreated(new Date());
        session().save(comment);
    }

    public boolean exists(int comment_id) {
        Criteria crit = session().createCriteria(Comment.class);
        crit.add(Restrictions.idEq(comment_id));
        Comment comment = (Comment) crit.uniqueResult();
        return comment != null;
    }

    public List<Comment> getAllComments() {
        Criteria crit = session().createCriteria(Comment.class);
        return crit.list();
    }

    public void update(Comment comment) {
        comment.setUpdated(new Date());
        session().update(comment);
    }

    public void deleteComments() {
        session().createQuery("DELETE FROM Comment").executeUpdate();
    }
}
