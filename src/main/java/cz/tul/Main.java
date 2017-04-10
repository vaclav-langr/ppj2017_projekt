package cz.tul;

import cz.tul.data.*;
import cz.tul.provisioning.Provisioner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
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

    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        AuthorDao authorDao = ctx.getBean(AuthorDao.class);

        List<Author> authors = authorDao.getAllAuthors();
        System.out.println(authors);

    }

}