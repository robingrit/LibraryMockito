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
        if (book.isBorrowed() !=true) {
            customer.pay(calculatePrice(book));
            book.setBorrowed(true);
        }
        else{
            throw new IllegalStateException();
        }


    }

    public ArrayList<Book> SearchBook(String input){

        ArrayList<Book> SearchBookList = new ArrayList<>();
        for (Book item : getShelf()) {
            if (item.getAuthor().equals(input)) {
                //System.out.println(item.getBookName());

                SearchBookList.add(item);

            }
            else if (item.getReleaseDate().equals(input)){
                SearchBookList.add(item);
            }
            else if(item.getBookName().equals(input)){
                SearchBookList.add(item);
            }
        }
        return SearchBookList;

    }


}

