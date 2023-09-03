package org.example;
import java.util.*;
public class Main {
    static ArrayList<Member> people = new ArrayList<>();
    static ArrayList<List> abcx = new ArrayList<>();
    static ArrayList<String> abcdx =new ArrayList<>();
    static ArrayList<String> nam =new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Library Portal Initialized….");
        System.out.println("---------------------------------\n");
        System.out.println("1. Enter as a librarian");
        System.out.println("2. Enter as a member");
        System.out.println("3. Exit");
        Librarian lib = new Librarian();
        Scanner input1 = new Scanner(System.in);
        int p = input1.nextInt();
        int c = 1;
        int b = 1;
        while (c == 1) {
            switch (p) {
                case 1:
                    while (b == 1) {
                        lib.abc();
                        Scanner inp = new Scanner(System.in);
                        switch (inp.nextInt()) {
                            case 1:
                                lib.register();
                                break;
                            case 2:
                                lib.removeMember();
                                break;
                            case 3:
                                lib.book_add();
                                break;
                            case 4:
                                lib.remove_book();
                                break;
                            case 5:
                                lib.view_book_fine(people,nam,lib);
                                break;
                            case 6:
                                lib.view_book();
                                break;
                            case 7:
                                b = 2;
                                break;
                            default:
                                System.out.println("Invalid input. Try again");
                        }
                    }
                    break;
                case 2:
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Name:");
                    String name1 = scanner.nextLine();
                    System.out.print("Phone No:");
                    long ph_no_1 = scanner.nextLong();
                    List<String> bcs = Librarian.members;
                    if (bcs.isEmpty()){
                        System.out.println("no members registered");
                        b=2;
                        break;
                    }else {
                        boolean memf = false;
                        for (int i = 0; i < bcs.size(); i++) {
                            String[] memDetails1 = bcs.get(i).split("[!<>]");
                            String p1 = bcs.get(i);
                            if (p1.startsWith(name1) && memDetails1[2].equals(Long.toString(ph_no_1))) {
                                memf=false;
                                Member mem1 = new Member(memDetails1[3],lib.books);
                                for (int j = 0; j < people.size(); j++) {
                                    if (Objects.equals(memDetails1[3], nam.get(j))) {
                                        mem1 = people.get(nam.indexOf(String.valueOf(memDetails1[3])));
                                    }
                                }
                                List<Timer> timaa = new ArrayList<>();
                                Scanner inp2 = new Scanner(System.in);
                                while (b == 1) {
                                    mem1.abc();
                                    switch (inp2.nextInt()) {
                                        case 1:
                                            mem1.list_book();
                                            break;
                                        case 2:
                                            int t=0;
                                            for (String s : abcdx) {
                                                if (Objects.equals(s, memDetails1[3])) {
                                                    t++;
                                                }
                                            }
                                            if (t<2) {
                                                timaa = mem1.issue_book(lib.books);
                                                if (timaa != null) {
                                                    abcx.add(timaa);
                                                    abcdx.add(memDetails1[3]);
                                                }
                                            }
                                            else{
                                                System.out.println("You can not borrow more than 2 books ");
                                            }
                                            break;
                                        case 3:
                                            for (int h=0;h< abcx.size();h++){
                                                if (Objects.equals(memDetails1[3], abcdx.get(h))){
                                                    timaa = abcx.get(h);
                                                }
                                            }
                                            mem1.return_book(lib.books, timaa);
                                            try {
                                                abcdx.remove(abcx.indexOf(timaa));
                                                abcx.remove(timaa);

                                            }catch (Exception e){
                                                System.out.println();
                                            }
                                            break;
                                        case 4:
                                            mem1.pay_fine();
                                            break;
                                        case 5:
                                            b = 2;
                                            break;
                                        default:
                                            System.out.println("Invalid input. Try again");
                                    }
                                }
                                people.add(mem1);
                                nam.add(memDetails1[3]);
                            } else {
                                memf=true;
                            }

                            }
                        if (memf){
                            System.out.println("Member with Name: " + name1 + " and Phone No: " + ph_no_1 + " doesn't exist.");
                        }
                    }



                    break;
                case 3:
                    c = 2;
                    break;
                default:
                    System.out.println("Invalid input. Try again");
                    b=2;
                    break;
            }
            if (b == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("1. Enter as a librarian");
                System.out.println("2. Enter as a member");
                System.out.println("3. Exit");
                p = input.nextInt();
            }
            b = 1;
        }
        System.out.println("---------------------------------\n" +
                "Thanks for visiting!\n" +
                "---------------------------------\n");
    }
}
