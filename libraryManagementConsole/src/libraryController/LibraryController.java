package libraryController;


import Entity.BookBorrowEntity;
import Entity.UserEntity;
import libraryService.LibraryService;

import java.util.List;

public class LibraryController {


    LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public UserEntity login(String id, String password) {

        UserEntity user = libraryService.login(id, password);

        if (user == null) {
            System.out.println("User not loggedIn");
            return null;
        }

        return user;
    }

    public void createUser(String name, String password, String role) {
        libraryService.createUser(name, password, role);
        System.out.println("User created successfully!");
    }


    public void createBook(String name, int count) {
        libraryService.createBook(name, count);
        System.out.println("Book created successfully!");
    }

    public void editBook(String id, String name, int count) {
        libraryService.editBook(id, name, count);
        System.out.println("Book edited successfully!");
    }

    public void lendBook(String userId, String bookId) {
        libraryService.lendBook(userId, bookId);
    }

    public void returnBook(String userId,String bookId) {
        libraryService.returnBook(userId,bookId);
    }


    public void getBookById(String id) {
        libraryService.getBookById(id);
    }

    public void getCurrentBorrowedBooks() {
        List<BookBorrowEntity> lst = libraryService.getCurrentBorrowedBooks();
        if (lst.isEmpty()) {
            System.out.println("No current borrowed books!");
            return;
        }
        for (BookBorrowEntity book : lst) {
            System.out.println("Book ID: " + book.getBookId() + " - User Name: " + book.getUserId() + "- Returned: " + book.isReturned() + " - Due Date: " + book.getDueDate());
        }
    }

    public void getAllOverDueBooks() {
        List<BookBorrowEntity> lst = libraryService.getAllOverDueBooks();
        if (lst.isEmpty()) {
            System.out.println("No books over due!");
            return;
        }
        for (BookBorrowEntity book : lst) {
            System.out.println("Book ID: " + book.getBookId() + " - User Name: " + book.getUserId() + "- Returned: " + book.isReturned() + " - Due Date: " + book.getDueDate());
        }
    }


    public void getBorrowedBooksById(String id) {
        List<BookBorrowEntity> lst = libraryService.getBorrowedBooksById(id);
        if (lst.isEmpty()) {
            System.out.println("No books borrowed!");
            return;
        }
        for (BookBorrowEntity book : lst) {
            System.out.println("Book ID: " + book.getBookId() + " - User Name: " + book.getUserId() + "- Returned: " + book.isReturned() + " - Due Date: " + book.getDueDate());
        }
    }

    public void getAllCurrentBorrowedBooksById(String id) {

        List<BookBorrowEntity> lst = libraryService.getAllCurrentBorrowedBooksById(id);
        if (lst.isEmpty()) {
            System.out.println("No books borrowed!");
            return;
        }
        for (BookBorrowEntity book : lst) {
            System.out.println("Book ID: " + book.getBookId() + " - User Name: " + book.getUserId() + "- Returned: " + book.isReturned() + " - Due Date: " + book.getDueDate());
        }

    }


}
