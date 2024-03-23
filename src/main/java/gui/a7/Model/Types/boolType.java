package gui.a7.Model.Types;

import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.boolValue;

public class boolType implements  iType
{
    ///the function equals is used to compare two objects
    public boolean equals(Object another)
    {
        return another instanceof boolType;
    }

    ///the function toString is used to return the type of the object as a string
    public String toString()
    {
        return "bool";
    }

    ///the function getDefaultValue is used to return the default value of the object
    @Override
    public iValue getDefaultValue()
    {
        return new boolValue(false);
    }

    ///the function getType is used to return the type of the object
    public iType getType()
    {
        return new boolType();
    }
}
