package model;

import model.DTO.*;
import Interfaces.*;

import java.util.ArrayList;
import java.util.List;

import Utils.*;

public class Library implements iLibrary {
    private static Library instance;
    private final static int Limit = 200;
    private ArrayList<Element> Elements;
    private ArrayList<User> Users;

    private Library() {

    }

    /**
     * Metodo encargado de buscar por codigo
     * @param search codigo a buscar
     * @return devuelve el elemento encontrado
     */
    public Element searchElement(String search) {
        if (Elements != null) {
            for (Element e : Elements) {
                if (e.getCode().equalsIgnoreCase(search)) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * Metodo encargado de buscar por titulo
     * @param search Titulo a buscar
     * @return devuelve una lista de objetos
     */
    public List<Element> searchElementTitle(String search) {
        if (Elements != null) {
            List<Element> returned = new ArrayList<>();
            for (Element e : Elements) {
                if (Utils.TitleChecker(search, e)) {
                    returned.add(e);
                }
            }
            if (returned.size() > 0) {
                return returned;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * Metodo el cual buscara que el objeto con el codigo buscado en el ArrayList. si lo encuentra entonces pedira
     * al usuario confirmar si realmente desea eliminarlo. Si es asi lo eliminara. si no no realizara nada
     *
     * @param search codigo del elemento a eliminar
     * @return devuelve true si se ha conseguido elimnar y false si no
     */
    public boolean removeElement(String search) {
        if (Elements != null && search != null) {
            Element e = searchElement(search);
            if (e != null) {
                if (Utils.confirm("Are you sure you want to delete this book?" + "\n" + e)) {
                    Elements.remove(e);
                    return true;
                }
                return false;
            }
            System.out.println(Utils.rojo + "Not found" + Utils.b);
            return false;
        }
        System.out.println(Utils.rojo + "Not found" + Utils.b);
        return false;
    }

    /**
     * Por coherencia he decidido solo permitir modificar el titulo de los elementos y o su codigo
     * @param search codigo del elemento a buscar
     * @return devuelve true si se ha podido actualizar y false de lo contrario
     */
    public boolean updateElement(String search) {
        if (Elements != null) {
            Element e = searchElement(search);
            if (e != null) {
                boolean end = false;
                do {
                    System.out.println("The Element search it´s found. What do you want to do");
                    switch (Read.readInt("0. Back\t\n" + "1. Update to code\t\n" + "2. Update to title")) {
                        case 0:
                            return false;
                        case 1:
                            ChangeCode(e, Read.readString(Utils.amarillo + "Enter the new code" + Utils.b));
                            break;
                        case 2:
                            if (e instanceof Book) {
                                e.setTitle(Read.readString(Utils.amarillo + "Enter a new title" + Utils.b));
                            } else {
                                System.out.println(Utils.rojo + "Only books can be changed their titles" + Utils.b);
                            }
                            break;
                        default:
                            System.out.println(Utils.rojo + "please, enter a valid option" + Utils.b);
                            break;
                    }
                } while (!end);
                return true;
            }
        }
        return false;
    }

    /**
     * 1/2 Sobrecarga. Metodo encarga de verificar si es un libro, revista o copia. y verificar que puede ser creado o no
     * @param e recive el elemento
     * @return devuelve true si se ha podido agregar y false de lo contrario
     */
    @Override
    public boolean addElement(Element e) {
        if (Elements.size() < Limit) {
            if (e != null) {
                for (Element aux : Elements) {
                    if (aux.getCode().equalsIgnoreCase(e.getCode())) {
                        System.out.println("This code is already exist. Please enter other code valid");
                        return false;
                    }
                }
                if (e instanceof Magazine) {//Crear revista
                    for (Element aux : Elements) {
                        if (aux instanceof Magazine) {
                            if (((Magazine) aux).getEditionNumber() == ((Magazine) e).getEditionNumber()) {
                                System.out.println("The edition number already exist. The book is " + aux.getTitle());
                                return false;
                            }
                        }
                    }
                    Elements.add(e);
                    System.out.println("Book created. OK");
                    return true;
                }
                if (e instanceof Book) { //CREAR LIBRO
                    for (Element aux : Elements) {
                        if (aux instanceof Book) {
                            if (((Book) aux).getISBN().equalsIgnoreCase(((Book) e).getISBN())) {
                                System.out.println("The ISBN book already exist. The book is " + aux.getTitle());
                                return false;
                            }
                        }
                    }
                    Elements.add(e);
                    System.out.println("Book created. OK");
                    return true;
                }
            }
        } else {
            System.out.println("The library it´s full of Elements");
            return false;
        }
        return false;
    }

    /**
     * Sobrecarga 2/2. Generara una copia a partir de un ISBN
     * @param ISBN Importa el isbn del nuevo elemento
     * @param bookCase estanteria en la que se guardara
     * @return devuelve true si se ha completado y false si no
     */
    public boolean addElement(String ISBN, int bookCase) {
        if (Elements != null) {
            for (Element e : Elements) {
                if (e instanceof Book) {
                    if (((Book) e).getISBN().equalsIgnoreCase(ISBN)) {
                        Element newCopy = new Copy(((Book) e), bookCase);
                        if (ChangeCode(newCopy, Read.readString("Enter the new code"))) {
                            Elements.add(newCopy);
                            System.out.println("Copy of the book created. OK");
                        } else {
                            System.out.println("Error. Couldn't create it");
                        }
                        return true;
                    }
                }
            }
            System.out.println(Utils.rojo + "The book whit ISBN " + ISBN + " is not already exist" + Utils.b);
            return false;
        }
        return false;
    }

    /**
     * Comprueba que el elemento al cual cambiaremos el codigo. no exista el nuevo codigo a introducir
     * @param e elemento
     * @param newCode codigo nuevo
     * @return devuelve true si se ha completado y false de lo contrario
     */
    public boolean ChangeCode(Element e, String newCode) {
        for (Element element : Elements) {
            if (element.getCode().equalsIgnoreCase(newCode)) {
                System.out.println("This code are ready exist.");
                return false;
            }
        }
        e.setCode(newCode);
        return true;
    }

    /**
     * Este metodo agrera al arrayList un nuevo usuario
     * @param u objeto usuario
     * @return devuelve un booleano true o false dependiendo de si se ha podido crear o no
     */
    @Override
    public boolean addUser(User u) {
        if (u != null) {
            for (User aux : Users) {
                if (aux.getDni().equals(u.getDni())) {
                    return false;
                }
            }
            Users.add(u);
            return true;
        }
        return false;
    }

    /**
     * Metodo el cual busca a un usuario por el DNI
     * @param DNI recive el dni
     * @return devuelve el usuario
     */
    @Override
    public User searchUser(String DNI) {
        if (Users != null) {
            for (User aux : Users) {
                if (aux.getDni().equals(DNI)) {
                    return aux;
                }
            }
        }
        return null;
    }

    /**
     * Este metodo prestara el libro al usuario. y se encargara de almacenar un registro del usuario en la copia y
     * un registro del libro en el usuario para asi saber quien lo tiene el libro y que libro tiene el usuario
     * @param u usuario al cual sera prestado
     * @param c copia la cual sera prestado
     * @return devuelve true o false
     */
    public boolean borrowedCopy(User u, Copy c) {
        if (Elements != null && Users != null && u != null && c != null) {
            if(u.getBorrowedBook()==null && !c.isBorrowed()){
                c.setBorrowed(true);
                c.setBorrowedUser(u);
                u.setBorrowedBook(c);
                c.setBorrowedDate(Read.readDate("Enter the Date of the borrowed"));
                return true;
            }else{
                System.out.println(Utils.rojo+"ERROR."+Utils.b);
            }
        }
        return false;
    }

    /**
     * Devolvera el libro siempre y cuando la copia este prestada y el usuario tenga algun libro
     * @param u usuario al cual sera prestado
     * @param c copia la cual sera prestado
     * @return devuelve true o false
     */
    public boolean returnCopy(User u, Copy c) {
        if (Elements != null && Users != null && u != null && c != null) {
            if(u.getBorrowedBook()!=null && c.isBorrowed()){
                c.setBorrowed(false);
                c.setBorrowedUser(null);
                u.setBorrowedBook(null);
                c.setBorrowedDate(null);
                return true;
            }
        }
        return false;
    }

    public static Library getLibrary() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public ArrayList<Element> getElements() {
        return Elements;
    }

    public void setElements(ArrayList<Element> elements) {
        Elements = elements;
    }

    public ArrayList<User> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<User> users) {
        Users = users;
    }

}
