package libraryController;


import Entity.BookBorrowEntity;
import Entity.BookEntity;
import Entity.UserEntity;
import libraryService.LibraryService;

import java.util.List;

public class LibraryController {


    LibraryService libraryService;

    UserEntity login(String id, String password) {

        UserEntity user = libraryService.login(id,password);

        if(user==null){
            System.out.println("User not loggedIn");
            return null;
        }

        return user;
    }

    void CreateUser(String name, String password, String role) {
        libraryService.createUser(name,password,role);
        System.out.println("User created successfully!");
        return;
    }


    void createBook(String name, int count) {
        libraryService.createBook(name,count);
        System.out.println("Book created successfully!");
        return;
    }

    void editBook(String id, String name, int count) {
        libraryService.editBook(id,name,count);
        System.out.println("Book edited successfully!");
        return;
    }

    void lendBook(String userId, String bookId) {
        libraryService.lendBook(userId,bookId);
        return;
    }

    void returnBook(String userId) {
        libraryService.returnBook(userId);
        return;
    }


    void getBookById(String id) {
        libraryService.getBookById(id);
        return ;
    }

    void getCurrentBorrowedBooks() {
        List<BookBorrowEntity> lst = libraryService.getCurrentBorrowedBooks();
        if(lst.isEmpty()) {
            System.out.println("No current borrowed books!");
            return;
        }
        for(BookBorrowEntity book:lst){
            System.out.println("Book ID: "+book.getBookId()+" - User Name: "+book.getUserId()+"- Returned: "+book.isReturned()+" - Due Date: "+book.getDueDate());
        }
        return;
    }

    void getAllOverDueBooks() {
        List<BookBorrowEntity> lst = libraryService.getAllOverDueBooks();
        if(lst.isEmpty()) {
            System.out.println("No books over due!");
            return;
        }
        for(BookBorrowEntity book:lst){
            System.out.println("Book ID: "+book.getBookId()+" - User Name: "+book.getUserId()+"- Returned: "+book.isReturned()+" - Due Date: "+book.getDueDate());
        }
        return;
    }



    void getBorrowedBooksById(String id) {
        List<BookBorrowEntity> lst = libraryService.getBorrowedBooksById(id);
        if(lst.isEmpty()) {
            System.out.println("No books borrowed!");
            return;
        }
        for(BookBorrowEntity book:lst){
            System.out.println("Book ID: "+book.getBookId()+" - User Name: "+book.getUserId()+"- Returned: "+book.isReturned()+" - Due Date: "+book.getDueDate());
        }
        return;
    }

    void getAllCurrentBorrowedBooksById(String id) {

        List<BookBorrowEntity> lst = libraryService.getAllCurrentBorrowedBooksById(id);
        if(lst.isEmpty()) {
            System.out.println("No books borrowed!");
            return;
        }
        for(BookBorrowEntity book:lst){
            System.out.println("Book ID: "+book.getBookId()+" - User Name: "+book.getUserId()+"- Returned: "+book.isReturned()+" - Due Date: "+book.getDueDate());
        }
        return;

    }


}
