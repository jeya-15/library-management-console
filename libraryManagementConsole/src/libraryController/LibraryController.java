package libraryController;

import Entity.BookBorrowEntity;
import Entity.BookEntity;
import Entity.UserEntity;
import libraryRepository.LibraryRepository;

import java.util.List;

public class LibraryController {


    UserEntity CreateUser(String name, String password){
     return null;
    }

    UserEntity login(String id, String password){
        return null;
    }

    BookEntity createBook(String name,int count){
        return null;
    }

    BookEntity editBook(String id,String name, int count){
        return null;
    }

    void lendBook(String userId, String bookId){
        return;
    }

    void returnBook(String UserId){
        return;
    }

    List<BookEntity> getAllBooks(){
        return List.of();
    }

    BookEntity getBookById(){
        return null;
    }

    List<BookBorrowEntity> getCurrentBorrowedBooks(){
        return List.of();
    }

    List<BookBorrowEntity> getAllOverDueBooks(){
        return List.of();
    }

    UserEntity getUser(String id){
        return null;
    }

    List<BookBorrowEntity> getBorrowedBooksById(String id){
        return null;
    }

    List<BookBorrowEntity> getAllCurrentBorrowedBooksById(String id){
        return null;
    }


}
