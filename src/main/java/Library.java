import java.util.ArrayList;

public class Library {
    private ArrayList<Book> Shelf = new ArrayList<>();
    private Customer customer;
    private static int MonthlyCost = 10;

    public ArrayList<Book> getShelf() {
        return Shelf;
    }

    public void addBookToList(Book book){
        Shelf.add(book);
    }

    public void addGrade(Book book, int num){
    book.setGrade(num);
    }

    public void addComment(Book book, String coment){
        book.setComment(coment);
    }
    public int calculatePrice(Book book) {
        return MonthlyCost * book.getQuantity();
    }

    public void lendBook(Book book) {
        int amountToPay = calculatePrice(book);
        if (book.isBorrowed()) {
            customer.pay(amountToPay);
        }
    }



}

