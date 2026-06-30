import Entity.BookBorrowEntity;
import Entity.UserEntity;
import libraryController.LibraryController;
import libraryRepository.InMemoryLibraryRepository;
import libraryRepository.LibraryRepository;
import libraryService.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start");

        Scanner sc = new Scanner(System.in);

        LibraryRepository libraryRepository = new InMemoryLibraryRepository();

        LibraryService libraryService = new LibraryService(libraryRepository);

        LibraryController libraryController = new LibraryController(libraryService);


        UserEntity user = null;

        boolean end = true;

        while (end) {
            System.out.println("Library Management System");


            if (user == null) {
                System.out.print("Enter UserId: ");
                String id = sc.nextLine();
                System.out.println();
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                System.out.println();
                user = libraryController.login(id, password);
            }

            while (user != null) {
                System.out.println("Operations");
                if ("ADMIN".equals(user.getRole())) {
                    System.out.println("1) Create Book");
                    System.out.println("2) Create User");
                    System.out.println("3) Edit Book");
                    System.out.println("4) Lend Book");
                    System.out.println("5) Return Book");
                    System.out.println("6) Get Book by ID");
                    System.out.println("7) Get Currently Borrowed Books");
                    System.out.println("8) Get All Over Dued Books");
                    System.out.println("9) Get Borrowed Books by Id");
                    System.out.println("10) Get All Currently Borrowed Books by ID");
                    System.out.println("11) Logout");
                    System.out.println("12) End session");
                    int choice = Integer.parseInt(sc.nextLine());

                    switch (choice) {
                        case 1: {
                            System.out.print("Enter Book name: ");
                            String name = sc.nextLine();
                            System.out.println();
                            System.out.print("Enter Book count: ");
                            int count = Integer.parseInt(sc.nextLine());
                            System.out.println();
                            libraryController.createBook(name, count);
                            break;
                        }
                        case 2: {
                            System.out.print("Enter the name: ");
                            String name = sc.nextLine();
                            System.out.println();
                            System.out.print("Enter the password: ");
                            String password = sc.nextLine();
                            System.out.println();
                            System.out.print("Enter the role: ");
                            String role = sc.nextLine();
                            libraryController.createUser(name, password, role);
                            System.out.println();
                            break;

                        }

                        case 3: {
                            System.out.print("Enter the Book ID: ");
                            String bookId = sc.nextLine();
                            System.out.println();
                            System.out.print("Enter Book name: ");
                            String name = sc.nextLine();
                            System.out.println();
                            System.out.print("Enter Book count: ");
                            int count = Integer.parseInt(sc.nextLine());
                            System.out.println();
                            libraryController.editBook(bookId, name, count);
                            break;
                        }
                        case 4: {
                            System.out.print("Enter User ID: ");
                            String userId = sc.nextLine();
                            System.out.println();
                            System.out.print("Enter the Book ID: ");
                            String bookId = sc.nextLine();
                            System.out.println();
                            libraryController.lendBook(userId, bookId);
                            break;
                        }
                        case 5: {
                            System.out.print("Enter User ID: ");
                            String userId = sc.nextLine();
                            System.out.println();
                            System.out.print("Enter the Book ID: ");
                            String bookId = sc.nextLine();
                            System.out.println();
                            libraryController.returnBook(userId,bookId);
                            break;
                        }
                        case 6: {
                            System.out.print("Enter the Book ID: ");
                            String bookId = sc.nextLine();
                            System.out.println();
                            libraryController.getBookById(bookId);
                            break;
                        }

                        case 7: {
                            libraryController.getCurrentBorrowedBooks();
                            break;
                        }

                        case 8: {
                            libraryController.getAllOverDueBooks();
                            break;
                        }

                        case 9: {
                            System.out.print("Enter User ID: ");
                            String userId = sc.nextLine();
                            System.out.println();
                            libraryController.getBorrowedBooksById(userId);
                            break;
                        }

                        case 10: {
                            System.out.print("Enter User ID: ");
                            String userId = sc.nextLine();
                            System.out.println();
                            libraryController.getAllCurrentBorrowedBooksById(userId);
                            break;
                        }
                        case 11: {
                            user = null;
                            System.out.println("User logged out!");
                            break;
                        }

                        case 12: {
                            System.out.println("Session ended");
                            end = false;
                            user=null;
                            break;
                        }

                        default: {
                            System.out.println("Invalid Choice!");
                            break;
                        }
                    }


                } else if ("USER".equals(user.getRole())) {
                    System.out.println("1) Get My Borrowed Books");
                    System.out.println("2) Get All Currently Borrowed Books");
                    System.out.println("3) Logout");
                    System.out.println("4) End session");

                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1: {
                            libraryController.getBorrowedBooksById(user.getId());
                            System.out.println();
                            break;
                        }

                        case 2: {
                            libraryController.getAllCurrentBorrowedBooksById(user.getId());
                            System.out.println();
                            break;
                        }
                        case 3: {
                            user = null;
                            System.out.println("User logged out!");
                            break;
                        }
                        case 4: {
                            System.out.println("Session ended");
                            end = false;
                            user=null;
                            break;
                        }
                        default: {
                            System.out.println("Invalid Choice!");
                            break;
                        }
                    }
                }
            }

        }

    }

}