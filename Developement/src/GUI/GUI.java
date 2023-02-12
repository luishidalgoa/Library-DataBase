package GUI;

import Interfaces.*;
import Utils.Utils;
import jdk.jshell.execution.Util;

public final class GUI implements iGUI {
    public void mainMenu() {
        System.out.println(
                Utils.magenta+"Main menu\t\n"
                        + "0. Save and Exit\t\n"
                        + "1. Menu element\t\n"
                        + "2. Menu users\t\n"
                        + "3. Menu borrowed"+Utils.b);
    }

    @Override
    public void menuElement() {
        System.out.println(
                Utils.magenta+"Menu element\t\n"
                        + "0. Back\t\n"
                        + "1. Add element\t\n"
                        + "2. Search element\t\n"
                        + "3. Edit element\t\n"
                        + "4. Remove element"+Utils.b);
    }
    @Override
    public void TypeElement(){
        System.out.println(
                Utils.magenta+"Element type\t\n"
                        + "0. Back\t\n"
                        + "1. Book\t\n"
                        + "2. Magazine\t\n"+Utils.b);
    }
    @Override
    public void BookType(){
        System.out.println(
                Utils.magenta+"Element type\t\n"
                        + "0. Back\t\n"
                        + "1. New book\t\n"
                        + "2. Copy\t\n"+Utils.b);
    }
    @Override
    public void menuUsers() {
        System.out.println(
                Utils.magenta+"Menu users\t\n"
                        + "0. Back\t\n"
                        + "1. Add user\t\n"
                        + "2. Search user\t\n"+Utils.b);
    }

    @Override
    public void menuBorrowed() {
        System.out.println(
                Utils.magenta+"Menu elements\t\n"
                        + "0. Back\t\n"
                        + "1. Borrow book copy\t\n"
                        + "2. Return copy borrowed"+ Utils.b);
    }

}
