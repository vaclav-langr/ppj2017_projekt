package cz.tul.app;

import cz.tul.data.*;
import cz.tul.repositories.AuthorRepository;
import cz.tul.repositories.ImageRepository;
import cz.tul.repositories.TagRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
@ComponentScan("cz.tul.services")
@EnableJpaRepositories(basePackages = "cz.tul.repositories")
public class Main {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }


    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        AuthorRepository authorRepository = ctx.getBean(AuthorRepository.class);
        Author author = authorRepository.save(new Author("pepa"));

        ImageRepository imageRepository = ctx.getBean(ImageRepository.class);
        Image image = imageRepository.save(new Image(author, "url", "name"));

        TagRepository tagRepository = ctx.getBean(TagRepository.class);
        tagRepository.save(new Tag(image.getImageId(), "tag1"));

        List<String> tags = new ArrayList<String>();
        tags.add("tag1");
        System.out.println(imageRepository.findByTags(tags));
    }

}