package com.example.demo.repository;

import com.example.demo.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class AuthorRepositoryImpl  implements AuthorRepository {


//
//    protected static Map<Long, Author> authors;
//
//    AuthorRepositoryImpl() {
//        authors = new HashMap<>(3);
//        authors.put(AUTHOR_1, new Author(AUTHOR_1, "Joanne", "Rowling"));
//        authors.put(AUTHOR_2, new Author(AUTHOR_1, "Herman", "Melville"));
//        authors.put(AUTHOR_3, new Author(AUTHOR_1, "Anne", "Rice"));
//    }
//
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        restTemplate = builder.build();
//        return restTemplate;
//    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ServiceRegistry serviceRegistry;

    private static  String restRoot = ServiceRegistry.getRestRoot(Author.class);


    @Override
    public Author findOne(Long id) {
        return findAuthorById(id);
    }

    @Override
    public Iterable<Author> findAll() {

        Author[] authors = restTemplate.getForObject(restRoot + "/getAuthors", Author[].class);
        return Arrays.asList(authors);
    }

    @Override
    public Author findAuthorById(Long id) {
        return restTemplate.getForObject(
                restRoot+"getAuthorById?id="+id, Author.class);
    }

    @Override
    public long count() {
        return restTemplate.getForObject(
                restRoot+"authorAmount", Long.class);
    }
}