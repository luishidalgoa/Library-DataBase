package model.DTO;

import Utils.Utils;
import model.Element;

import java.io.Serializable;
import java.util.Date;

public class Book extends Element implements Cloneable, Serializable {
    protected final Date publicationDate;
    protected final String ISBN;

    /**
     * Constructor de libros
     * @param code codigo del libro en la biblioteca
     * @param title titulo del libro
     * @param ISBN del libro
     * @param publicationDate fecha publicacion
     */
    public Book(String code, String title, String ISBN, Date publicationDate){
        super(code,title);
        this.ISBN=ISBN;
        this.publicationDate=publicationDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }
    public String getISBN() {
        return ISBN;
    }

    @Override
    public String toString() {
        return Utils.verde+"Book: " +
                super.toString()+
                "publication date: "+ publicationDate+"\n\t"+
                "ISBN: "+ISBN+"\t\n"+Utils.b;
    }
}
