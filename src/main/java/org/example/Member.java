package org.example;
import java.util.*;
public class Member {
    public int fines = 0;
    private boolean hasOverdueBooks = false;
    public Member(String s, List<String> availableBooks) {
    }
    public List<String> borrowedBooks = new ArrayList<>();
    public Map<Integer, Timer> bookTimers = new HashMap<>();
    void abc() {
        System.out.println("List Available Books\n" +
                "1. List My Books\n" +
                "2. Issue book\n" +
                "3. Return book\n" +
                "4. Pay Fine\n" +
                "5. Back");
    }
    void list_book() {
        if (borrowedBooks.isEmpty()){
            System.out.println("No books borrowed");
        }else {
            System.out.println("Borrowed Books:");
            for (String borrowedBookInfo : borrowedBooks) {
                String[] borrowedBookDetails = borrowedBookInfo.split("[!<>]");
                int borrowedBookId = Integer.parseInt(borrowedBookDetails[0]);
                String title = borrowedBookDetails[1];
                String author = borrowedBookDetails[2];
                System.out.println("Book ID: " + borrowedBookId + "\nTitle: " + title + "\nAuthor: " + author + "\n");
            }
        }
    }

    List<Timer> issue_book(List<String> books) {
        for(String borrowedBookInfo : borrowedBooks){
            String[] borrowedBookDetails = borrowedBookInfo.split("[!<>]");
            int borrowedBookId = Integer.parseInt(borrowedBookDetails[0]);
            Timer timer = bookTimers.get(borrowedBookId);


            if (timer != null) {
                long elapsedTime = timer.displayCurrentElapsedTime();
                System.out.println(elapsedTime);
                if (elapsedTime > 10){
                    hasOverdueBooks=true;
                }
            }
        }

        if (hasOverdueBooks) {
            System.out.println("You have unpaid fines or overdue books. Please pay your fines and return overdue books before borrowing more books.");
            return null;
        }
        if (fines>0) {
            System.out.println("You have unpaid fines or overdue books. Please pay your fines and return overdue books before borrowing more books.");
            return null;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Books available to you are");
        List<String> availableBooks = new ArrayList<>();
        for (String bookInfo : books) {
            String[] bookDetails = bookInfo.split("[!<>]");
            int id = Integer.parseInt(bookDetails[0]);
            int copies = Integer.parseInt(bookDetails[3]);
            if (copies > 0) {
                String title = bookDetails[1];
                String author = bookDetails[2];
                System.out.println("Book ID: " + id + "\nTitle: " + title + "\nAuthor: " + author + "\nCopies: " + copies + "\n");
                availableBooks.add(bookInfo);
            }
        }
        if (availableBooks.isEmpty()) {
            System.out.println("No books are available for borrowing at the moment.");
            return null;
        }
        if (!hasOverdueBooks) {
            System.out.print("Enter the book Id to issue: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();
            for (String bookInfo : books) {
                String[] bookDetails = bookInfo.split("[!<>]");
                int p = books.indexOf(bookInfo);
                int id = Integer.parseInt(bookDetails[0]);
                if (id == bookId) {
                    String title = bookDetails[1];
                    String author = bookDetails[2];
                    int copies = Integer.parseInt(bookDetails[3]);
                    if (copies > 0) {
                        System.out.println("Book successfully issued");
                        Timer timer = new Timer();
                        timer.start();
                        bookTimers.put(bookId, timer);
                        borrowedBooks.add(bookInfo);
                        bookDetails[3] = Integer.toString(copies - 1);
                        String abcs = bookId + "!" + title + "<" + author + ">" + bookDetails[3];
                        books.set(p, abcs);
                        return new ArrayList<>(bookTimers.values());
                    } else {
                        System.out.println("No more copies of this book are available for borrowing.");
                        return null;
                    }
                }
            }
            System.out.println("Book with ID " + bookId + " not found in available books.");
            return null;
        }
        System.out.println("return previous book before borrowing");
        return null;
    }
    void return_book(List<String> books, List<Timer> timers) {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You haven't borrowed any books.");
        } else {
            System.out.println("Borrowed Books:");
            for (String borrowedBookInfo : borrowedBooks) {
                String[] borrowedBookDetails = borrowedBookInfo.split("[!<>]");
                int borrowedBookId = Integer.parseInt(borrowedBookDetails[0]);
                String title = borrowedBookDetails[1];
                String author = borrowedBookDetails[2];
                System.out.println("Book ID: " + borrowedBookId + "\nTitle: " + title + "\nAuthor: " + author + "\n");
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the book ID to return: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();
            boolean bookFound = false;
            for (String borrowedBookInfo : borrowedBooks) {
                String[] borrowedBookDetails = borrowedBookInfo.split("[!<>]");
                int borrowedBookId = Integer.parseInt(borrowedBookDetails[0]);
                if (borrowedBookId == bookId) {
                    for (String libraryBookInfo : books) {
                        int xc = books.indexOf(libraryBookInfo);
                        String[] libraryBookDetails = libraryBookInfo.split("[!<>]");
                        int libraryBookId = Integer.parseInt(libraryBookDetails[0]);
                        if (libraryBookId == bookId) {
                            int availableCopies = Integer.parseInt(libraryBookDetails[3]);
                            libraryBookDetails[3] = Integer.toString(availableCopies + 1);
                            String datq = bookId + "!" + libraryBookDetails[1] + "<" + libraryBookDetails[2] + ">" + libraryBookDetails[3];
                            books.set(xc, datq);
                            break;
                        }
                    }
                    borrowedBooks.remove(borrowedBookInfo);
                    System.out.println("Book with ID " + bookId + " has been returned.");
                    bookFound = true;
                    break;
                }
            }


            if (!bookFound) {
                System.out.println("You haven't borrowed a book with ID " + bookId + ".");

            } else {
                Timer timer = bookTimers.get(bookId);
                if (timer != null) {
                    timer.stop();
                    long elapsedTime = timer.getElapsedTime();
                    if (elapsedTime > 10) {
                        int overdueSeconds = (int) (elapsedTime - 10);
                        int fineAmount = overdueSeconds * 3;
                        fines += fineAmount;
                        hasOverdueBooks = true;
                        System.out.println("You have been fined Rs " + fineAmount + " for being " + overdueSeconds + " seconds late.");
                    }
                    else{
                        hasOverdueBooks=false;
                    }
                }
            }
        }
    }
    void pay_fine() {
        if (fines ==0){
            System.out.println("No fine to be paid");

        }else {
            System.out.println("fine of "+ fines+" is paid");
            hasOverdueBooks=false;
            fines = 0;
        }
    }
}
