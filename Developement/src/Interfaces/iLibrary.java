package Interfaces;

import model.DTO.Copy;
import model.Element;
import model.DTO.User;

public interface iLibrary {
    Element searchElement(String search);
    boolean removeElement(String search);
    boolean updateElement(String code);
    boolean addElement(Element element);
    boolean addUser(User u);
    User searchUser(String DNI);
    boolean borrowedCopy(User user, Copy copy);
    boolean returnCopy(User user,Copy copy);
}
