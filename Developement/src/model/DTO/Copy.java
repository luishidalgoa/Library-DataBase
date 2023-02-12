package model.DTO;

import Utils.Utils;

import java.io.Serializable;
import java.util.Date;

public class Copy extends Book implements Serializable {
    private final int bookCase;
    private User borrowedUser;
    private boolean borrowed=false;
    private Date borrowedDate=null;
    public Copy(Book book,int bookCase){
        super(book.getCode(),book.getTitle(),book.getISBN(),book.getPublicationDate());
        this.bookCase=bookCase;
    }
    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
    public boolean isBorrowed() {
        return borrowed;
    }
    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }
    public void setBorrowedUser(User borrowedUser) {
        this.borrowedUser = borrowedUser;
    }
    @Override
    public String toString() {
        return Utils.verde + "Copy: \n\t" +
                "Code: " + code + "\n\t" +
                "Title: " + title + "\n\t" +
                "publication date: " + publicationDate + "\n\t" +
                "ISBN: " + ISBN + "\n\t" +
                "Book case: " + bookCase + "\n\t" +
                "Borrowed: " + borrowed + "\n\t" +
                "Borrowed date: " + borrowedDate + "\n\t" +
                "Borrowed user: " + (borrowedUser != null ? borrowedUser.getName() + "\n\tDni: " + borrowedUser.getDni() : "None") + "\t\n" + Utils.b;
        /*
         * Al investigar he descubierto que usando una condicion ternaria para asi no tener que usar un if-else si el
         * campo borrowed User esta vacio y no pete
         */

    }
}
