package cz.tul.app;

import cz.tul.data.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
public class Main {

    @Bean
    public AuthorDao authorDao() {
        return new AuthorDao();
    }

    @Bean
    public CommentDao commentDao(){
        return new CommentDao();
    }

    @Bean
    public CommentRatingDao commentRatingDao(){
        return new CommentRatingDao();
    }

    @Bean
    public ImageDao imageDao(){
        return new ImageDao();
    }

    @Bean
    public ImageRatingDao imageRatingDao(){
        return new ImageRatingDao();
    }

    @Bean
    public TagDao tagDao(){
        return new TagDao();
    }

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        AuthorDao authorDao = ctx.getBean(AuthorDao.class);
        authorDao.create(new Author("pepa"));

        List<Author> authors = authorDao.getAllAuthors();
        System.out.println(authors);
    }

}