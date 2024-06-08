package com.vi.hellomongo;


// apex,visualforce ,lightening 
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository  // must be this annotation 
public interface BookRepository extends MongoRepository<Book, Integer> // this is not reactive repository
{
	// Book is the collection name 
	// Integer is the unique number in mongodb 
	// be careful while inserting data use _id field i.e unique 
	Book findById(int id);
	// all default parameter functions can be used directly in controller
	// but all parameterized functions must be defined into the repository.
	//Book findByBookNameAndAuthorName(String bookname,String authorname);
	
	// below is the position based query annotation example i.e json format 
	//@Query("{'id' : ?0}") // all fields with matching id field 
	//Book findById(int id);
	
	@Query(value="{'authorName': ?0}")  // works fine OK
   List<Book> findByAuthor(String author);	// all fields with matching author name only 
	//@Query(value="{'authorName':?0}",fields="{authorName:1,bookName:1,_id:0}")
	// _id field is excluded (0) for exclusion value 
	// in the above Id will be displayed but will show 0 note this imp	
	//List<Book> byAuth(String author); // function name is valid customized method 
	//@Query("{ authorName : { $regex : ?0 } }")
    //List<Book> byAuth(String authorName);
	// any matching text in author name will be display 
	// it can be single letter, word,pattern  etc will be displayed 
	// this is smart work for fast searching into the mongodb 
}


