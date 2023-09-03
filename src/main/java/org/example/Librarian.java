package org.example;
import java.util.*;
public class Librarian {
    public static List<String> members = new ArrayList<>();
    public List<String> books = new ArrayList<>();
    public List<String> hcv = new ArrayList<>();
    private int bookIdCounter = 1;
    private int generateUniqueBookId() {
        return bookIdCounter++;
    }
    void abc() {
        System.out.println("1. Register a member\n" +
                "2. Remove a member\n" +
                "3. Add a book\n" +
                "4. Remove a book\n" +
                "5. View all members along with their books and fines to be paid\n" +
                "6. View all books\n" +
                "7. Back");
    }
    private int RandomMemberId() {
        int randomId;
        do {
            randomId = new Random().nextInt(1000000) + 1;
        } while (isMemberIdUsed(randomId));
        return randomId;
    }
    private boolean isMemberIdUsed(int memberId) {
        for (String member : members) {
            if (member.contains("Member ID: " + memberId)) {
                return true;
            }
        }
        return false;
    }

    void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.print("Enter phone number: ");
        long ph_no = scanner.nextLong();
        int x=0;
        for (String member : members) {
            String q = name + "!" + age + "<" + ph_no + ">";
            if (member.startsWith(q)) {
                System.out.println("member already registered");
                x = 2;
                break;
            }
        }
        if (x==0){
            int newMemberId = RandomMemberId();
            String data = name + "!" + age + "<" + ph_no + ">" + " Member ID: " + newMemberId;
            members.add(data);
            System.out.println("Data appended successfully for the new member with ID: " + newMemberId);
        }
    }

    void removeMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Member ID to remove: ");
        int memberIdToRemove = scanner.nextInt();
        scanner.nextLine();
        for (int c=0;c< members.size();c++){
            String[] memq=members.get(c).split("[!<>]");
            String pa = " Member ID: " + memberIdToRemove;
            if (pa.equals(memq[3])){
                members.removeIf(line -> line.contains("Member ID: " + memberIdToRemove));
                System.out.println("Member with Member ID: " + memberIdToRemove + " removed successfully");
                return;
            }
        }

        System.out.println("Member with Member ID: " + memberIdToRemove + " does not exist");
    }

    void book_add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter author name:");
        String author = scanner.nextLine();
        System.out.println("Enter the number of copies:");
        int number = scanner.nextInt();
        for (int ax = 0; ax < number; ax++) {
            int bookId = generateUniqueBookId();
            String dat = bookId + "!" + title + "<" + author + ">" + 1;
            books.add(dat);
            hcv.add(dat);
            System.out.println("Book Added Successfully! with id: " + bookId);
        }
    }


    void remove_book() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Member ID to remove: ");
        int bookidtoremove = scanner.nextInt();
        scanner.nextLine();
        for (String book : books) {
            String[] mema = book.split("[!<>]");
            if (Objects.equals(mema[0], Integer.toString(bookidtoremove))) {
                members.removeIf(line -> line.contains("Member ID: " + bookidtoremove));
                System.out.println("Book with Book ID: " + bookidtoremove + " removed successfully");
                return;
            }
        }
        System.out.println("Book with Book ID: " + bookidtoremove + " does not exist");
    }
    void view_book() {
        for (String line : books) {
            String[] bookInfo = line.split("[!<>]");
            String title = bookInfo[1];
            String author = bookInfo[2];
            int bkid = Integer.parseInt(bookInfo[0]);
            System.out.println("Book ID: " + bkid + "\nTitle: " + title + "\nAuthor: " + author+"\n");
        }
    }

    void view_book_fine(List<Member> cvx,ArrayList<String> nam,Librarian lib) {


        for (String member : members) {

            List<String> aq = new ArrayList<>();
            String[] memDetails1 = member.split("[!<>]");
            System.out.println("name: " + memDetails1[0]);
            System.out.println("age:" + memDetails1[1]);
            System.out.println("ph no." + memDetails1[2]);
            System.out.println(memDetails1[3]);
            Member t1 = new Member(memDetails1[3], lib.books);
            for (int j = 0; j < nam.size(); j++) {
                if (Objects.equals(memDetails1[3], nam.get(j))) {

                    t1 = cvx.get(nam.indexOf(memDetails1[3]));
                }
                    try {
                        for(int q=0;q<t1.borrowedBooks.size();q++) {
                            if (!aq.contains(t1.borrowedBooks.get(q))){
                            aq.add(t1.borrowedBooks.get(q));
                        }
                        }
                    } catch (Exception e) {
                    }
            }
            if (aq.size()!=0) {
                System.out.println("borrowed books: \n");
                for (int z = 0; z < aq.size(); z++) {
                    String[] memDetails2 = aq.get(z).split("[!<>]");
                    System.out.println("book id: " + memDetails2[0] + " title: " + memDetails2[1] + " Author" + memDetails2[2]);
                }
            }
            else{
                System.out.println("no books borrowed");
            }
            System.out.println("fines: " + t1.fines +"\n\n");
        }
    }
}
