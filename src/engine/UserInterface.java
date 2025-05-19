package engine;

import engine.classes.*;
import game.Player;

public class UserInterface {
    private static final Text moneyLabel = new Text("MONEY:", Text.OFFSET, Text.OFFSET, 5, Color.WHITE);

    private Text money;

    private Player player;
    private Renderer renderer;

    public UserInterface(Player player, Renderer renderer) {
        this.player = player;
        this.renderer = renderer;

        money = new Text("$0", 200, Text.OFFSET, 5, Color.YELLOW);
    }

    public void update() {
        money.text = "$"+player.getMoney();
    }

    public void render() {
        update();

        text(moneyLabel);
        text(money);
    }

    public void text(Text txt) {
        renderer.renderText(txt.text, txt.x, txt.y, txt.size, txt.color);
    }
}
