package enums;

public enum Items {
    PLAYSTATION_CAMERA("Playstation 5 HD Camera White"),
    NINTENDO_MONOPOLY("Monopoly For Nintendo Switch SWITCH"),
    XBOX_SERIES_X("Microsoft Xbox Series X 1TB");


    private final String item;

    Items(final String item) {
        this.item = item;
    }

    public String item() {
        return item;
    }

    @Override
    public String toString() {
        return this.item;
    }
}
