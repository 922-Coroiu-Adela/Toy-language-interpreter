package gui.a7.Model.Values;

import gui.a7.Model.Types.stringType;
import gui.a7.Model.Types.iType;

public class stringValue implements iValue
{
    public String value;

    public stringValue(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public String toString()
    {
        return value;
    }

    public iType getType()
    {
        return new stringType();
    }

    public boolean equals(Object other)
    {
        if (other instanceof stringValue)
            return ((stringValue)other).getValue().equals(value);
        else
            return false;
    }

    public iValue deepCopy()
    {
        return new stringValue(this.value);
    }

}
