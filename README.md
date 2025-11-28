>> **Tech Stack**

Java 17

Spring Boot 3.5.4

Spring Web

Spring Data JPA

Hibernate Validation

MapStruct (DTO ↔ Entity mapping)

PostgreSQL

Lombok

SLF4J Logging

**Features**

✔ Add one or multiple books
✔ Update existing books
✔ Remove a book
✔ View all books
✔ View a specific book by ID
✔ Borrow a book (transaction created)
✔ Return a book
✔ DTO mapping using MapStruct
✔ Global exception handling
✔ Logging with SLF4J

Port used: 8082

Available Endpoints (Simple Overview)
**>> Books**
Method	Endpoint	Description
POST	/books	Add new books (List of BookDTO)
GET	/books	Get all books
GET	/books/{id}	Get a book by ID
DELETE	/books/{id}	Remove a book by ID
PUT	/books/{id}	Update book (BookDTO + ID)

**>> Transactions**
Method	Endpoint	Description
POST	/books/borrow	Borrow a book (TransactionDTO)
POST	/books/return/{bookId}	Return a borrowed book

**Recommended Project Structure**
src/main/java/com/dd/books
 ├── controller
 ├── service
 ├── service/impl
 ├── repository
 ├── entity
 ├── dto
 ├── mapper
 ├── exception
 ├── util
 └── BooksTransactionsApplication.java
