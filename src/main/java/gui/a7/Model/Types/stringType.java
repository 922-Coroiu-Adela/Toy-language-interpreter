package gui.a7.Model.Types;

import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.stringValue;

public class stringType implements iType
{
    public boolean equals(Object other)
    {
        return other instanceof stringType;
    }

    public iType getType()
    {
        return new stringType();
    }

    public String toString()
    {
        return "string";
    }

    public iValue getDefaultValue()
    {
        return new stringValue("");
    }
}
