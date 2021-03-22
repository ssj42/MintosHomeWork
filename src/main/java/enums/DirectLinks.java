package enums;

public enum DirectLinks {
    KSENUKAI("https://www.ksenukai.lv");

    private final String link;

    DirectLinks(final String link) {
        this.link = link;
    }

    public String link() {
        return link;
    }

    @Override
    public String toString() {
        return this.link;
    }
}
