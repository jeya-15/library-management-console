package libraryService;

import Entity.BookBorrowEntity;
import Entity.BookEntity;
import Entity.UserEntity;
import libraryRepository.LibraryRepository;

import java.util.List;
import java.util.Scanner;

public class LibraryService {

    LibraryRepository libraryRepository;
    Scanner scanner;

    public LibraryService(LibraryRepository libraryRepository, Scanner scanner) {
        this.libraryRepository = libraryRepository;
        this.scanner = scanner;
    }

    public UserEntity login(String id, String password) {
        UserEntity user = libraryRepository.findUserById(id);
        if (user == null) {
            System.out.println("User Id not found!");
            return null;
        }
        if (user.getPassword().equals(password)) {
            System.out.println("User " + user.getName() + "logged in!");
            return user;
        }
        System.out.println("Password Wrong for User ID " + id);
        return null;
    }

    public void createUser(String name, String password, String role) {
        UserEntity user = new UserEntity(name, password, role);
        libraryRepository.addUser(user);
        System.out.println("User added successfully! " + user.getId() + " " + user.getName());
    }

    public void createBook(String name, int count) {
        BookEntity book = new BookEntity(name, count);
        libraryRepository.addBook(book);
        System.out.println("Book added successfully: " + book.getId() + " " + book.getName());
    }

    public void editBook(String id, String name, int count) {
        BookEntity book = libraryRepository.findBookById(id);
        if (book == null) {
            System.out.println("Book " + id + " not found");
            return;
        }
        book.setName(name);
        book.setCount(count);
        System.out.println("Book updated successfully: " + book.getId() + " " + book.getName());
    }

    public void lendBook(String userId, String bookId) {
        UserEntity user = libraryRepository.findUserById(userId);
        if (user == null) {
            System.out.println("User " + userId + " not found");
            return;
        }

        BookEntity book = libraryRepository.findBookById(bookId);
        if (book == null) {
            System.out.println("Book " + bookId + " not found");
            return;
        }

        if (user.getLimit() <= 0) {
            System.out.println("User can took maximum of 5 books only!");
            return;
        }

        if (book.getCount() <= 0) {
            System.out.println("Book not available!");
            return;
        }

        BookBorrowEntity bookBorrow = new BookBorrowEntity(userId, bookId);
        book.setCount(book.getCount() - 1);
        user.setLimit(user.getLimit() - 1);
        System.out.println("Book lend successfully!");

    }

    public void returnBook(String userId) {

        UserEntity user = libraryRepository.findUserById(userId);
        if (user == null) {
            System.out.println("User " + userId + " not found");
            return;
        }
        List<BookBorrowEntity> borrowBooks = libraryRepository.findBorrowedBooksByIdAndCurrentBorrowed(userId);

        if (borrowBooks.isEmpty()) {
            System.out.println("No books borrowed!");
            return;
        }

        for (int i = 0; i < borrowBooks.size(); i++) {
            BookBorrowEntity borrowBook = borrowBooks.get(i);
            BookEntity book = libraryRepository.findBookById(borrowBook.getBookId());
            System.out.println(i + 1 + ") " + book.getId() + ": " + book.getName() + " Due Date is " + borrowBook.getDueDate());
        }
        System.out.print("Enter the book id to return :");
        String bookId = scanner.nextLine();

        BookBorrowEntity bookBorrow = null;

        for (BookBorrowEntity b : borrowBooks) {
            if (b.getBookId().equals(bookId)) {
                bookBorrow = b;
                break;
            }
        }
        bookBorrow.setReturned(true);
        BookEntity book = libraryRepository.findBookById(bookId);
        book.setCount(book.getCount() + 1);
        user.setLimit(user.getLimit() + 1);

        System.out.println("Book returned Successfully");
    }

    public void getBookById(String id) {
        BookEntity book = libraryRepository.findBookById(id);
        if (book == null) {
            System.out.println("Book " + id + " not found");
            return;
        }
        System.out.println("Book ID: " + book.getId() + " - Book Name: " + book.getName() + " - Book Count: " + book.getCount());

    }

    public List<BookBorrowEntity> getCurrentBorrowedBooks() {
        return libraryRepository.findBorrowedBooksAndCurrentBorrowed();
    }

    public List<BookBorrowEntity> getAllOverDueBooks() {
        return libraryRepository.findOverDueBorrowedBooks();
    }

    public List<BookBorrowEntity> getBorrowedBooksById(String id) {
        UserEntity user = libraryRepository.findUserById(id);
        if (user == null) {
            System.out.println("User Id not found!");
            return List.of();
        }
        return libraryRepository.findBorrowedBooksById(id);
    }

    public List<BookBorrowEntity> getAllCurrentBorrowedBooksById(String id) {
        UserEntity user = libraryRepository.findUserById(id);
        if (user == null) {
            System.out.println("User Id not found!");
            return List.of();
        }
        return libraryRepository.findBorrowedBooksByIdAndCurrentBorrowed(id);
    }


}
