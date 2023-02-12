package model;

import Utils.Utils;

import java.io.Serializable;
import java.util.Objects;

public class Element implements Serializable {
    protected String code;
    protected String title;
    public Element(String code ,String title){
        this.code=code;
        this.title=title;
    }

    public String getCode() {
        return code;
    }
    public String getTitle() {
        return title;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return Utils.verde+"\n\tCode: " + code + "\n\t" +
                "Title: " + title +"\n\t";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(code, element.code) && Objects.equals(title, element.title);
    }
}
