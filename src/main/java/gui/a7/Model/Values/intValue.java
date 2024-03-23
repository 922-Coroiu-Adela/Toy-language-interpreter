package gui.a7.Model.Values;

import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.intType;

public class intValue implements iValue
{
    int value;

    ///the constructor of the class intValue
    public intValue(int value)
    {
        this.value = value;
    }

    ///the function getValue is used to return the value of the object
    public int getValue()
    {
        return this.value;
    }

    ///the function equals is used to compare two objects
    public boolean equals(Object another)
    {
        if (another instanceof intValue)
        {
            intValue anotherInt = (intValue)another;
            return anotherInt.value == this.value;
        }
        return false;
    }

    ///the function toString is used to return the value of the object as a string
    public String toString()
    {
        return Integer.toString(this.value);
    }

    ///the function getType is used to return the type of the object
    public iType getType()
    {
        return new intType();
    }

    public iValue deepCopy()
    {
        return new intValue(this.value);
    }

}
