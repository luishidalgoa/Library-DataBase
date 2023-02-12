package Controller;

import GUI.GUI;
import Interfaces.*;
import Utils.*;
import model.DTO.*;
import model.*;

import java.util.ArrayList;
import java.util.List;

public final class Controller implements iController {

    static SerializationUtils s = new SerializationUtils();
    static Library library = Library.getLibrary();
    private final iGUI GUI = new GUI();

    /**
     * Metodo encargado de inicializar el programa y la serializacion ademas de finalizar el programa
     * @throws Exception
     */
    @Override
    public void Start() throws Exception {
        //se cargaran los datos al iniciar
        library.setElements((ArrayList<Element>) s.deserialize("Book.bin"));
        library.setUsers((ArrayList<User>) s.deserialize("Users.bin"));
        mainMenuStart();
        //se guardara al salir
        s.serialize(library.getElements(), "Book.bin");
        s.serialize(library.getUsers(), "Users.bin");
        System.out.println(Utils.verde + "Saved successfully" + Utils.b);
    }

    /**
     * Metodo encargado de mostrar el menu inicial y controlar las opciones
     */
    @Override
    public void mainMenuStart() {
        boolean end = false;
        do {
            GUI.mainMenu();
            switch (Read.readInt("Enter a option valid")) {
                case 0:
                    end = true;
                    break;
                case 1:
                    menuElementStart();
                    break;
                case 2:
                    menuUserStart();
                    break;
                case 3:
                    menuBorrowedStart();
                    break;
                default:
                    System.out.println("please, enter a valid option");
                    break;
            }
        } while (!end);
    }

    @Override
    public void menuElementStart() {
        boolean end = false;
        do {
            GUI.menuElement();
            switch (Read.readInt("Enter a option valid")) {
                case 0:
                    end = true;
                    break;
                case 1:
                    GUI.TypeElement();
                    switch (Read.readInt("Enter a option valid")) {
                        case 0:
                            break;
                        case 1:
                            GUI.BookType();
                            switch (Read.readInt("Enter a option valid")) {
                                case 0:
                                    break;
                                case 1:
                                    Book newBook = new Book(Read.readString("Enter the book code"), Read.readString("Enter the title of the book"), Read.readString("Enter the ISBN of the book"), Read.readDate("Enter the publication date"));
                                    addElement(newBook);
                                    break;
                                case 2:
                                    library.addElement(Read.readString("Enter a ISBN of the book what you want create the copy"), Read.readInt("Enter the book case number for this copy"));
                                    break;
                            }
                            break;
                        case 2:
                            Magazine newMagazine = new Magazine(Read.readString("Enter the magazine code"), Read.readString("enter the title of the magazine"), Read.readInt("Enter the issue number of the magazine"));
                            addElement(newMagazine);
                            break;
                        default:
                            System.out.println("you didn´t selected any option valid");
                    }
                    break;
                case 2:
                    searchElement();
                    break;
                case 3:
                    updateElement();
                    break;
                case 4:
                    removeElement();
                    break;
                default:
                    System.out.println("please, enter a valid option");
                    break;
            }
        } while (!end);
    }

    @Override
    public void menuUserStart() {
        boolean end = false;
        do {
            GUI.menuUsers();
            switch (Read.readInt("Enter a option valid")) {
                case 0:
                    end = true;
                    break;
                case 1:
                    addUser(new User(Read.readDNI("Enter the DNI user"), Read.readString("Enter the name user"), Read.readString("Enter the last name of the user"), Read.readInt("Enter the telephone number of the user")));
                    break;
                case 2:
                    searchUser();
                    break;
                default:
                    System.out.println("please, enter a valid option");
                    break;
            }
        } while (!end);
    }

    @Override
    public void menuBorrowedStart() {
        boolean end = false;
        do {
            GUI.menuBorrowed();
            switch (Read.readInt("Enter a option valid")) {
                case 0:
                    end = true;
                    break;
                case 1:
                    borrowedCopy();
                    break;
                case 2:
                    returnBorrowed();
                    break;
                default:
                    System.out.println("please, enter a valid option");
                    break;
            }
        } while (!end);
    }

    /**
     * Metodo que llamara al metodo addElement de library
     * @param element
     */
    @Override
    public void addElement(Element element) {
        if (library.addElement(element)) {
            System.out.println("has been create the element successful");
        } else {
            System.out.println("Error. Could not create ");
        }
    }

    /**
     * Metodo que llamara a los metodos de busqueda de los elementos por titulo o codigo
     */
    @Override
    public void searchElement() {
        String search = Read.readString("Enter a code of the book or Title. If it is a title you will have to write the title preceded by ' - '");
        if (search.contains("-")) {
            List<Element> e = library.searchElementTitle(search);
            if (e.size() > 0) {
                for (Element aux : e) {
                    System.out.println(aux + "\t");
                }
            } else {
                System.out.println(Utils.rojo + "Not found" + Utils.b);
            }
        } else if (!search.contains("-")) {
            Element e = library.searchElement(search);
            if (e != null) {
                System.out.println(Utils.verde + e + Utils.b);
            } else {
                System.out.println(Utils.rojo + "Not found" + Utils.b);
            }
        } else {
            System.out.println(Utils.rojo + "The search hasn´t been possible" + Utils.b);
        }
    }

    @Override
    public void updateElement() {
        if (library.updateElement(Read.readString("Enter a code of the book"))) {
            System.out.println("Ok. Successful update the book");
        } else {
            System.out.println(Utils.rojo + "Not found" + Utils.b);
        }
    }

    @Override
    public void removeElement() {
        String code = Read.readString("Enter book code");
        if (library.removeElement(code)) {
            System.out.println(Utils.verde + "Successful remove");
        } else {
            System.out.println("Error. Could not remove ");
        }
    }

    @Override
    public void addUser(User u) {
        if (library.addUser(u)) {
            System.out.println("has been create the user successful");
        } else {
            System.out.println("Error. Could not create ");
        }
    }

    @Override
    public void searchUser() {
        User u = library.searchUser(Read.readString("Enter the dni of the user"));
        if (u != null) {
            System.out.println(u);
        } else {
            System.out.println(Utils.rojo + "Not found" + Utils.b);
        }
    }

    @Override
    public void borrowedCopy() {
        Element e=library.searchElement(Read.readString("Enter the code of the Copy"));
        if(e instanceof Copy){
            Copy c = (Copy) e;
            User u = library.searchUser(Read.readDNI("Enter the DNI of the user that you want search"));
            if (c != null && u != null) {
                if (!c.isBorrowed() && u.getBorrowedBook() == null) {
                    if (library.borrowedCopy(u, c)) {
                        System.out.println("OK. The operation it been successful");
                    }
                } else {
                    System.out.println("ERROR.It has not been possible to update the borrowed"); //fallar si el libro ya esta prestado o no es una Copia
                }
            }else{
                System.out.println(Utils.rojo+"ERROR.It has not been possible to update the borrowed"+Utils.b);
            }
        }else{
            System.out.println(Utils.rojo+"The Element with code entered isn´t a copy"+Utils.b);
        }
    }

    public void returnBorrowed() {
        Element e=library.searchElement(Read.readString("Enter the code of the Copy"));
        if(e instanceof Copy){
            Copy c = (Copy) e;
            User u = library.searchUser(Read.readDNI("Enter the DNI of the user that you want search"));
            if ((c.isBorrowed()) && u.getBorrowedBook() != null) {
                if (library.returnCopy(u, c)) {
                    System.out.println("OK. The operation it been successful");
                }
            } else {
                System.out.println(Utils.rojo+"ERROR.It has not been possible to update the borrowed"+Utils.b);
            }
        }else{
            System.out.println(Utils.rojo+"The Element with code entered isn´t a copy"+Utils.b);
        }
    }

}
