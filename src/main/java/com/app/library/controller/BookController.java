package com.app.library.controller;


import com.app.library.form.BookForm;
import com.app.library.model.Book;
import com.app.library.service.BookService;
import com.app.library.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String showBookList(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/new")
    public String showNewBookAddForm(Model model) {
        model.addAttribute("book", BookForm.builder().build());
        model.addAttribute("categories", categoryService.findAll());

        return "book/form";
    }


    @PostMapping
    public String saveBook(@Valid @ModelAttribute("book") BookForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "book/form";
        }
        categoryService.findById(form.getCategoryId()).ifPresent(category -> {
            bookService.save(Book.builder()
                    .id(form.getId())
                    .category(category)
                    .name(form.getName())
                    .author(form.getAuthor())
                    .releaseDate(LocalDate.parse(form.getReleaseDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .build());
        });

        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(Model model, @PathVariable(name = "id") long id) {
        bookService.findById(id).ifPresent(form-> {
            BookForm bookForm = BookForm.builder()
                    .id(form.getId())
                    .categoryId(form.getCategory().getId())
                    .name(form.getName())
                    .author(form.getAuthor())
                    .releaseDate(form.getReleaseDate() != null? form.getReleaseDate().toString(): null )
                    .build();
            model.addAttribute("book",bookForm);
            model.addAttribute("categories", categoryService.findAll());
        });
        return "book/form";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") long id) {
        bookService.delete(id);
        return "redirect:/book";
    }

}
