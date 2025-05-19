package engine.classes;

public class Color {
    public float r, g, b;

    public static final Color WHITE  = new Color(1f, 1f, 1f);
    public static final Color BLACK  = new Color(0f, 0f, 0f);
    public static final Color RED    = new Color(1f, 0f, 0f);
    public static final Color GREEN  = new Color(0f, 1f, 0f);
    public static final Color BLUE   = new Color(0f, 0f, 1f);
    public static final Color YELLOW = new Color(1f, 1f, 0f);
    public static final Color CYAN   = new Color(0f, 1f, 1f);
    public static final Color MAGENTA= new Color(1f, 0f, 1f);
    public static final Color GRAY   = new Color(0.5f, 0.5f, 0.5f);

    public Color() {
        this(1f, 1f, 1f); // Default: white
    }

    public Color(float r, float g, float b) {
        this.r = clamp(r);
        this.g = clamp(g);
        this.b = clamp(b);
    }

    public Color(int r, int g, int b) {
        this.r = clamp(r / 255f);
        this.g = clamp(g / 255f);
        this.b = clamp(b / 255f);
    }

    public Color copy() {
        return new Color(r, g, b);
    }

    private float clamp(float value) {
        return Math.max(0f, Math.min(1f, value));
    }

    @Override
    public String toString() {
        return String.format("Color(%.2f, %.2f, %.2f)", r, g, b);
    }
}
