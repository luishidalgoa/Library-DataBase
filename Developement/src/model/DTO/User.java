package model.DTO;

import Utils.Utils;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private Copy borrowedBook = null;
    private final String Dni;
    private final String name;
    private final String lastName;
    private final int telephone;

    public User(String dni, String name, String lastName, int telephone) {
        this.Dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.telephone = telephone;
    }

    public Copy getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Copy borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public String getDni() {
        return Dni;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return telephone == user.telephone && Objects.equals(borrowedBook, user.borrowedBook) && Objects.equals(Dni, user.Dni) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public String toString() {
        return Utils.verde + "User :\n\t" +
                "Borrowed book: " + (borrowedBook != null ? borrowedBook.getTitle() : "None") + "\n\tCode Book: " + (borrowedBook != null ? borrowedBook.getCode() : "None") + "\n\t" +
                "Dni: '" + Dni + "\n\t" +
                "Name: '" + name + "\n\t" +
                "Last name: '" + lastName + "\n\t" +
                "Telephone: " + telephone + "\n\t" + Utils.b;
    }
}
