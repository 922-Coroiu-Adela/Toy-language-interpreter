package gui.a7;

import javafx.beans.property.SimpleStringProperty;

public class TripleTableEntry
{
    private final SimpleStringProperty index;
    private final SimpleStringProperty value;

    private final SimpleStringProperty list;

    public TripleTableEntry(String index, String value, String list)
    {
        this.index = new SimpleStringProperty(index);
        this.value = new SimpleStringProperty(value);
        this.list = new SimpleStringProperty(list);
    }

    public SimpleStringProperty getKey() {
        return index;
    }

    public SimpleStringProperty getValue() {
        return value;
    }

    public SimpleStringProperty getList() {
        return list;
    }
}
