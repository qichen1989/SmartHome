package ui;

public enum ButtonNameList {
    GO_TO_REPORT ("Current Smart Home Report"),
    GENERATE_REPORT ("Current Smart Home Report"),
    ON ("On"),
    OFF ("Off");

    private final String name;

    ButtonNameList(String name) {
        this.name = name;
    }

    // return name value of this button
    public String getValue(){
        return name;
    }
}
