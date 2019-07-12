package potgieter.game.models;

public enum RoundModeEnum {
    NORMAL(0),
    FREE(1);

    int i;

    RoundModeEnum(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
