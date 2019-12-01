package kr.bookworm.bookwormapp.entity;

import java.util.Collection;

public enum BookCategory {
    TITLE(1, "title"), ISBN(2, "isbn"), PUBLISHER(3, "publisher"), PERSON(4, "person");

    private int id;
    private String value;

    BookCategory(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
