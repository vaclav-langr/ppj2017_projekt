package cz.tul.services;

import cz.tul.data.Author;
import cz.tul.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vaclavlangr on 25.04.17.
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void create(Author author){
        authorRepository.save(author);
    }

    public boolean exists(String userName){
        return authorRepository.exists(userName);
    }

    public List<Author> getAllAuthors(){
        List<Author> authors = StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
        if(authors.size() == 0) {
            return null;
        }
        return authors;
    }

    public void deleteAuthors(){
        authorRepository.deleteAll();
    }
}
