package com.vi.hellomongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController 
{
@Autowired
// web+spring data mongoDB only simple dependency not reactive no jpa no maria
// MongoRepository acts as JpaRepository all methods are automatically available 
// into the postman id must be sent "id":90990 like this not using _id note this all unique values
// must have to create database as well as collection 
// mongorepository doesnot create collection automatically in noSql like Sql
BookRepository brepo;
@GetMapping("/all")
public List<Book> showbook()
{
return brepo.findAll();	// use deleteAll()
}

@GetMapping("/{id}")  // Ok working fine important 
public Book bybookid(@PathVariable int id)
{
return brepo.findById(id);	// use deleteById() also same way 
}
@RequestMapping("/save")
public Book bynameauthor(@RequestBody Book book)
{
brepo.save(book);
return book;
}
@RequestMapping("/del/{id}")
public String byid(@PathVariable int id)
{
	brepo.deleteById(id);
	return "book deleted "+id;
}
@RequestMapping("/byauthor/{authorname}")
public List<Book> byauthor(@PathVariable String authorname)
{
	return brepo.findByAuthor(authorname);
}
}
