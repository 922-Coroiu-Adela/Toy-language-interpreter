package gui.a7.Model.Values;

import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.refType;

public class refValue implements  iValue
{
    int address;
    iType locationType;

    public refValue(int address, iType locationType)
    {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddr()
    {
        return address;
    }

    public iType getType()
    {
        return new refType(locationType);
    }

    public iType getInner()
    {
        return locationType;
    }

    public String toString()
    {
        return "(" + Integer.toString(address) + ", " + locationType.toString() + ")";
    }

    @Override
    public boolean equals(Object another)
    {
        if (another instanceof refValue ref)
        {
            return locationType == ref.getType();
        }
        return false;
    }

    @Override
    public iValue deepCopy()
    {
        return new refValue(address, locationType);
    }
}
