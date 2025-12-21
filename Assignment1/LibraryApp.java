import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private final List<Book> books;
    private final Scanner scanner;

    public LibraryApp() {
        books = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to Library App!\n");
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice (1-7): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    printBooks();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1-7.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("1. Print all books");
        System.out.println("2. Add new book");
        System.out.println("3. Search books by title");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Delete a book by id");
        System.out.println("7. Quit");
    }

    private void printBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void addBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        try {
            Book newBook = new Book(title, author, year);
            books.add(newBook);
            System.out.println("Book added successfully! ID: " + newBook.getID()); // Fixed: getID()
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchBooks() {
        System.out.print("Enter title to search: ");
        String searchTerm = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with that title.");
        }
    }

    private void borrowBook() {
        System.out.print("Enter book ID to borrow: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Book book : books) {
            if (book.getID() == id) {
                if (book.getAvailable()) {
                    book.markAsBorrowed();
                    System.out.println("Book borrowed successfully!");
                } else {
                    System.out.println("Book is already borrowed!");
                }
                return;
            }
        }
        System.out.println("Book with ID " + id + " not found!");
    }

    private void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Book book : books) {
            if (book.getID() == id) {
                if (!book.getAvailable()) {
                    book.markAsReturned();
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book was not borrowed!");
                }
                return;
            }
        }
        System.out.println("Book with ID " + id + " not found!");
    }

    private void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Loop using index
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getID() == id) {
                books.remove(i);
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book with ID " + id + " not found!");
    }
}