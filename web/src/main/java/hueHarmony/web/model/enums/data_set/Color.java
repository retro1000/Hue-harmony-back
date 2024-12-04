package hueHarmony.web.model.enums.data_set;

public enum Color {
    // Custom Colors
    BROWN("#A52A2A"),
    PEACH_PUFF("#FFDAB9"),
    PINK("#FFC0CB"),
    BEIGE("#F5F5DC"),
    CORNSILK("#FFF8DC"),
    DEEP_PINK("#FF007F"),
    HOT_PINK("#FF69B4"),
    ANTIQUE_WHITE("#FAEBD7"),
    DARK_SALMON("#FF8C69"),
    GHOST_WHITE("#F8F8FF"),
    PAPAYA_WHIP("#FFEFD5"),
    TAN("#D2B48C"),
    LIGHT_PINK("#FFC8C8"),
    OLD_LACE("#FDF5E6"),
    ORANGE_RED("#FF4500"),
    WHEAT("#F5DEB3"),

    // Basic Colors
    BLACK("#000000"),
    WHITE("#FFFFFF"),
    RED("#FF0000"),
    GREEN("#00FF00"),
    BLUE("#0000FF"),
    CYAN("#00FFFF"),
    MAGENTA("#FF00FF"),
    YELLOW("#FFFF00"),
    ORANGE("#FFA500"),
    PURPLE("#800080"),
    GRAY("#808080"),
    SILVER("#C0C0C0"),
    GOLD("#FFD700"),
    NAVY("#000080"),
    TEAL("#008080"),
    MAROON("#800000");

    private final String hexCode;

    Color(String hexCode) {
        this.hexCode = hexCode;
    }


    // Static method to get hex code by color name
    public static String getHexCode(String colorName) {
        try {
            return Color.valueOf(colorName.toUpperCase()).hexCode;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid color name: " + colorName);
        }
    }
}
