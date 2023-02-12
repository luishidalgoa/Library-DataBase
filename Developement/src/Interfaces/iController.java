package Interfaces;

import model.DTO.User;
import model.Element;

public interface iController {
    void Start()throws Exception;
    void mainMenuStart();
    void menuElementStart();
    void menuUserStart();
    void menuBorrowedStart();
    void addElement(Element element);
    void searchElement();
    void updateElement();
    void removeElement();
    void addUser(User u);
    void searchUser();
    void borrowedCopy();
    void returnBorrowed();
}
