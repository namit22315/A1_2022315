#Library Management System

Welcome to the Library Management System! This Java console application reimagines library management by offering librarians and members a modern and efficient way to interact with the library's resources. It combines simplicity with innovation to enhance the library experience.

## Object-Oriented Programming (OOP) Implementation

This library management system is structured using Object-Oriented Programming (OOP) principles to create a modular and organized codebase. Let's take a look at how OOP is implemented in the code and the structure of method calls:

### 1. **Classes**

The system is divided into several classes, each with its specific responsibilities:

- `Main`: The main class that contains the entry point of the application. It handles user input and navigation.

- `Librarian`: Represents a librarian and contains methods for librarian-specific actions like member registration, book management, and viewing member details.

- `Member`: Represents a library member and contains methods for member-specific actions like borrowing books, returning books, and paying fines.

- `Timer`: A utility class used for tracking time, particularly for calculating fines on overdue books.

### 2. **Objects and Instantiation**

- **Librarian and Member Objects**: In the `Main` class, objects of the `Librarian` and `Member` classes are created as `lib` and `mem1`, respectively, for interaction with librarian and member-specific functionality.

- **Timer Object**: In the `Member` class, a `Timer` object is created to measure the time a book is borrowed to calculate fines.

### 3. **Method Calls**

#### Librarian Actions

1. **Registering a Member**: In the `Main` class, the `lib.register()` method is called to register a new member.

2. **Removing a Member**: The `lib.removeMember()` method is called to remove a member.

3. **Adding a Book**: The `lib.book_add()` method is used to add books to the library.

4. **Removing a Book**: The `lib.remove_book()` method is called to remove a book from the library.

5. **Viewing Member Details**: To view all members along with their books and fines, the `lib.view_book_fine()` method is called.

6. **Viewing All Books**: To view all books in the library, the `lib.view_book()` method is used.

#### Member Actions

1. **Listing Available Books**: In the `Member` class, the `list_book()` method lists available books.

2. **Listing Borrowed Books**: The `list_book()` method is also used to list the books a member has borrowed.

3. **Borrowing a Book**: The `issue_book()` method is called to borrow books. It checks for overdue books and fines before allowing borrowing.

4. **Returning a Book**: To return books and calculate fines, the `return_book()` method is used.

5. **Paying Fines**: Fines are paid using the `pay_fine()` method.

### 4. **Encapsulation**

- Member data and functionality are encapsulated within the `Librarian` and `Member` classes, promoting data privacy and reducing code complexity.

- In the `Member` class, borrowed books, book timers, and fines are encapsulated, ensuring that member-specific data is protected.


### 5. **Polymorphism**

Polymorphism allows different classes to be treated as instances of their base class. In this system, polymorphism is not explicitly demonstrated, but it could be used to handle different types of library members or books in a more complex library system.

