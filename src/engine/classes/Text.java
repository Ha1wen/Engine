package engine.classes;

public class Text {
    public String text;
    public float x;
    public float y;
    public float size;
    public Color color;

    public static float OFFSET = 20;

    public Text(String text, float x, float y, float size, Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }
}
