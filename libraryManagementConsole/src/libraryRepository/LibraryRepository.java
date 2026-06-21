package libraryRepository;

import Entity.BookBorrowEntity;
import Entity.BookEntity;
import Entity.UserEntity;

import java.util.List;

public interface LibraryRepository {

    UserEntity findUserById(String id);

    BookEntity findBookById(String id);

    void addBook(BookEntity book);

    void addBorrowBook(String userId, BookEntity book);

    void addUser(UserEntity user);

    List<BookBorrowEntity> getBorrowedBooks();

    List<BookBorrowEntity> findBorrowedBooksById(String id);

    List<BookBorrowEntity> findBorrowedBooksByIdAndBorrowedTrue(String id);

    List<BookBorrowEntity> findOverDueBorrowedBooks(String id);

}
