package gui.a7.Model.Values;

import gui.a7.Model.Types.iType;
public interface iValue
{
    iType getType();
    iValue deepCopy();
}
