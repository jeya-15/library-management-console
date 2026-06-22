package Entity;

import java.time.LocalDateTime;

public class BookBorrowEntity {

    private String userId;
    private String bookId;
    private boolean returned = false;
    private LocalDateTime dueDate;

    public BookBorrowEntity(String userId, String bookId) {
        this.userId = userId;
        this.bookId = bookId;
        this.dueDate = LocalDateTime.now().plusDays(14);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
