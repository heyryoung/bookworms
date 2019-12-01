package kr.bookworm.bookwormapp.entity;

public enum BookSearchCategory {
    TITLE(1, "title"), ISBN(2, "isbn"), PUBLISHER(3, "publisher"), PERSON(4, "person");

    private int id;
    private String value;

    BookSearchCategory(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
