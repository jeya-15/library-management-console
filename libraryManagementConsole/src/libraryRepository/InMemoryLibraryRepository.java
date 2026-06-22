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


    private final Map<String, UserEntity> users = new HashMap<>();

    private final Map<String, BookEntity> books = new HashMap<>();

    private final List<BookBorrowEntity> borrowedBooks = new ArrayList<>();

    int userIdNumber = 1;

    int bookIdNumber = 1;

    {
        UserEntity admin = new UserEntity("Jeya", "jeya", "ADMIN");
        users.put("1", admin);
    }

    @Override
    public UserEntity findUserById(String id) {

        return users.get(id);
    }

    @Override
    public BookEntity findBookById(String id) {

        return books.get(id);
    }

    @Override
    public void addBook(BookEntity book) {
        String IdNumber = String.format("%s05", bookIdNumber).replace(' ', '0');
        String bookId = "BOOK" + IdNumber;
        book.setId(bookId);
        books.put(bookId, book);
    }

    @Override
    public void addBorrowBook(String userId, BookEntity book) {
        BookBorrowEntity bookBorrow = new BookBorrowEntity(userId, book.getId());
        borrowedBooks.add(bookBorrow);
    }

    @Override
    public void addUser(UserEntity user) {
        String IdNumber = String.format("%5s", userIdNumber).replace(' ', '0');
        String userId = "USER" + IdNumber;
        user.setId(userId);
        users.put(userId, user);
    }


    @Override
    public List<BookBorrowEntity> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public List<BookBorrowEntity> findBorrowedBooksById(String id) {
        return borrowedBooks.stream().filter(book -> book.getUserId().equals(id)).toList();
    }

    @Override
    public List<BookBorrowEntity> findBorrowedBooksByIdAndCurrentBorrowed(String id) {
        return borrowedBooks.stream().filter(book -> !book.isReturned() && book.getUserId().equals(id)).toList();
    }

    @Override
    public List<BookBorrowEntity> findBorrowedBooksAndCurrentBorrowed() {
        return borrowedBooks.stream().filter(book -> !book.isReturned()).toList();
    }

    @Override
    public List<BookBorrowEntity> findOverDueBorrowedBooks() {
        return borrowedBooks.stream().filter(book -> !book.isReturned() && book.getDueDate().isBefore(LocalDateTime.now())).toList();
    }

}
