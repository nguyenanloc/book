package com.boeing.book;


import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    public List<Book> all() {
        return this.bookRepository.findAll();
    }

    @PostMapping("")
    public Book create(@RequestBody Book book){
        return this.bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable long id) {
//
//        if (this.bookRepository.existsById(id)) {
//            this.bookRepository.deleteById(id);
//
//        }
//        else
//            throw new RuntimeException("Book not found with id=" + id);

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id=" + id));

        Book deleteBook = bookRepository.deleteById(id);
        return deleteBook;
    }



    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book bookDetails){

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id=" + id));


        book.setTitle(bookDetails.getTitle());
        book.setPublishedDate(bookDetails.getPublishedDate());

        Book updateBook = bookRepository.save(book);
        return updateBook;
    }

}
