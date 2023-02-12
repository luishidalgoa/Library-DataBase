package model.DTO;

import Utils.Utils;
import model.Element;

import java.io.Serializable;

public class Magazine extends Element implements Serializable {
    private final int editionNumber;

    public Magazine(String code, String title, int editionNumber){
        super(code,title);
        this.editionNumber=editionNumber;
    }
    public int getEditionNumber() {
        return editionNumber;
    }

    @Override
    public String toString() {
        return Utils.verde+"Magazine: " +
                super.toString()+
                "Edition Number: " + editionNumber+"\t\n"+Utils.b;
    }
}
