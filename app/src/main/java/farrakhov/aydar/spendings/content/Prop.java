package farrakhov.aydar.spendings.content;

/**
 * Created by aydar farrakhov on 30.01.17.
 */

public class Prop {

    private String name;

    private String value;

    private String type;

    public Prop(String name, String value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
