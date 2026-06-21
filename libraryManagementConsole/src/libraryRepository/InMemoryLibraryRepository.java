package libraryRepository;

import Entity.BookBorrowEntity;
import Entity.BookEntity;
import Entity.UserEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLibraryRepository implements LibraryRepository {


    Map<String, UserEntity> users = new HashMap<>();

    Map<String, BookEntity> books = new HashMap<>();

    List<BookBorrowEntity> borrowedBooks = new ArrayList<>();

    int userIdNumber = 1;

    int bookIdNumber = 1;

    @Override
    public UserEntity findUserById(String id) {
        UserEntity user = users.get(id);
        if(user==null){
            System.out.println("User not found!");
        }
        return user;
    }

    @Override
    public BookEntity findBookById(String id) {
        BookEntity book = books.get(id);
        if(book==null){
            System.out.println("Book not found!");
        }
        return book;
    }

    @Override
    public void addBook(BookEntity book) {
        String IdNumber = String.format("%s5",bookIdNumber).replace(' ','0');
        String bookId = "BOOK"+IdNumber;
        book.setId(bookId);
        books.put(bookId,book);
    }

    @Override
    public void addBorrowBook(String userId, BookEntity book) {
        BookBorrowEntity bookBorrow = new BookBorrowEntity(userId,book.getId());
        borrowedBooks.add(bookBorrow);
    }

    @Override
    public void addUser(UserEntity user) {
        String IdNumber = String.format("%5s",userIdNumber).replace(' ','0');
        String userId = "USER"+IdNumber;
        user.setId(userId);
        users.put(userId,user);
    }

    @Override
    public List<BookBorrowEntity> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public List<BookBorrowEntity> findBorrowedBooksById(String id) {
        return borrowedBooks.stream().filter(book->book.getUserId().equals(id)).toList();
    }

    @Override
    public List<BookBorrowEntity> findBorrowedBooksByIdAndBorrowedTrue(String id) {
        return borrowedBooks.stream().filter(book->!book.isReturned()&&book.getUserId().equals(id)).toList();
    }

    @Override
    public List<BookBorrowEntity> findOverDueBorrowedBooks(String id) {
        return borrowedBooks.stream().filter(book->!book.isReturned()&&book.getDueDate().isBefore(LocalDateTime.now())).toList();
    }

}
