package gui.a7.Model.Values;

import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.boolType;

public class boolValue implements iValue
{
    boolean value;

    ///the constructor of the class
    public boolValue(boolean value)
    {
        this.value = value;
    }

    ///the function getValue is used to return the value of the object
    public boolean getValue()
    {
        return value;
    }

    ///the function toString is used to return the value of the object as a string
    public String toString()
    {
        return String.valueOf(value);
    }

    ///the function getType is used to return the type of the object
    public iType getType()
    {
        return new boolType();
    }

    ///the function equals is used to compare two objects
    public boolean equals(Object another)
    {
        if (another instanceof boolValue)
            return ((boolValue) another).getValue() == value;
        else
            return false;
    }

    public iValue deepCopy()
    {
        return new boolValue(this.value);
    }

}
