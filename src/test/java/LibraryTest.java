import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Nested
class LibraryTest {
    @InjectMocks
    private Library library;
    @Mock
    private Book book;
    @Mock
    private Customer customer;

    @Captor
    private ArgumentCaptor<Integer> payArgumentCaptor;

    @Nested
    class SearchTest {
        @Test
        public void SearchByAuthor() {
            Book book = new Book("Moby Dick", "Herman Melville", "1851-10-18", "Sea", 1);
            Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "1954-07-29", "Fantasy", 1);
            Book book2 = new Book("Harry Potter", "J.K. Rowling", "1999", "Magic", 1);
            library.addBookToList(book);
            library.addBookToList(book1);
            library.addBookToList(book2);
            String actual = null;
            String expected = "Moby Dick";
            for (Book item : library.getShelf()) {
                if (item.getAuthor().equals("Herman Melville")) {
                    actual = item.getBookName();
                    System.out.println(item.getBookName());
                }
            }
            assertEquals(expected, actual);
        }

        @Test
        public void SearchByBookName() {
            Book book = new Book("Moby Dick", "Herman Melville", "1851-10-18", "Sea", 1);
            Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "1954-07-29", "Fantasy", 1);
            Book book2 = new Book("Harry Potter", "J.K. Rowling", "1999", "Magic", 1);
            library.addBookToList(book);
            library.addBookToList(book1);
            library.addBookToList(book2);
            String actual = null;
            String expected = "The Lord of the Rings";
            for (Book item : library.getShelf()) {
                if (item.getBookName().equals(expected)) {
                    actual = item.getBookName();


                    System.out.println(item.getBookName());
                }
            }

            assertEquals(expected, actual);
        }

        @Test
        public void SearchByBookRealeaseDate() {
            Book book = new Book("Moby Dick", "Herman Melville", "1851-10-18", "Sea", 1);
            Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "1954-07-29", "Fantasy", 1);
            Book book2 = new Book("Harry Potter", "J.K. Rowling", "1999", "Magic", 1);
            library.addBookToList(book);
            library.addBookToList(book1);
            library.addBookToList(book2);
            String actual = null;
            String expected = "1954-07-29";
            for (Book item : library.getShelf()) {
                if (item.getReleaseDate().equals(expected)) {
                    actual = item.getReleaseDate();
                    System.out.println(item.getBookName());
                }
            }

            assertEquals(expected, actual);
        }
    }

    @Test
    public void ChangeGradeTest(){
        Book book = new Book("Moby Dick", "Herman Melville", "1851-10-18", "Sea", 1);
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "1954-07-29", "Fantasy", 1);
        Book book2 = new Book("Harry Potter", "J.K. Rowling", "1999", "Magic", 1);
        library.addBookToList(book);
        library.addBookToList(book1);

        System.out.println(book2.getGrade());

        library.addGrade(book2,1);
        System.out.println(book2.getGrade());
        library.addBookToList(book2);
        int actual = book2.getGrade();
        int expected = 1;

        assertEquals(expected, actual);
    }
    @Test
    public void CommentOnBook(){
        Book book = new Book("Moby Dick", "Herman Melville", "1851-10-18", "Sea", 1);
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "1954-07-29", "Fantasy", 1);
        Book book2 = new Book("Harry Potter", "J.K. Rowling", "1999", "Magic", 1);

        String comment = "This book was awesome";

        library.addComment(book2, comment);

        System.out.println(book2.getComment());
        library.addBookToList(book2);
        String actual = book2.getComment();
        String expected = comment;

        assertEquals(expected, actual);
    }

    @Test
    public void TestIfBookisOccipied(){
        Book book = new Book("Moby Dick", "Herman Melville", "1851-10-18", "Sea", 1);
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "1954-07-29", "Fantasy", 1);
        Book book2 = new Book("Harry Potter", "J.K. Rowling", "1999", "Magic", 1);


        //System.out.println(book.isBorrowed());

        book2.setBorrowed(true);
        Boolean actual = book2.isBorrowed();
        Boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void borrowTwoBooks(){
        Book book = new Book("Moby Dick", "Herman Melville", "1851-10-18", "Sea", 1);
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "1954-07-29", "Fantasy", 1);
        Book book2 = new Book("Harry Potter", "J.K. Rowling", "1999", "Magic", 1);

        book1.setBorrowed(true);
        book2.setBorrowed(true);

        library.lendBook(book1);
        library.lendBook(book2);

        verify(customer, times(2)).pay(payArgumentCaptor.capture());
        List<Integer> actualValues = payArgumentCaptor.getAllValues();
        List<Integer> expectedValues = Arrays.asList(10, 10);


        assertEquals(actualValues, expectedValues);
    }

    @ParameterizedTest
    @DisplayName("Parameterized Test")
    @ValueSource(ints = {1, 2, 33, 4, 5, 11})
    public void testingParametrizedTest(int books){
        Book book = new Book("Moby Dick", "Herman Melville", "1851-10-18", "Sea", books);

        int actual = library.calculatePrice(book);
        int expected = 10 * books;
        System.out.println(actual);
        assertEquals(expected, actual);
    }


}