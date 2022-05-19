package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;
    private final PublisherRepository publisherRepo;

    public BootStrapData(AuthorRepository authorRepo, BookRepository bookRepo, PublisherRepository publisherRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher bloom = new Publisher("Bloomsbury Publishing", "1385 Broadway, Fifth Floor",
                "New York", "NY", 10018);

        publisherRepo.save(bloom);

        Author rowling = new Author("J.K.", "Rowling");

        Book two = new Book("Harry Potter and The Chamber of Secrets", "53499");
        Book one = new Book("Harry Potter and The Sorcerer's Stone", "53498");

        rowling.getBooks().add(one);
        rowling.getBooks().add(two);
        one.getAuthors().add(rowling);
        two.getAuthors().add(rowling);
        one.setPublisher(bloom);
        bloom.getBooks().add(one);
        authorRepo.save(rowling);
        bookRepo.save(one);
        publisherRepo.save(bloom);
        two.setPublisher(bloom);
        bloom.getBooks().add(two);
        bookRepo.save(two);

        System.out.println("Number of authors - " + authorRepo.count());
        System.out.println("Number of books - " + bookRepo.count());
        System.out.println("Number of publishers - " + publisherRepo.count());
        System.out.println("Number of books by " + bloom.getName() + " - " + bloom.getBooks().size());
    }
}
