package gui.a7.Model.Types;

import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.refValue;

public class refType implements iType
{
    iType inner;

        public refType(iType inner)
        {
            this.inner = inner;
        }

        public iType getInner()
        {
            return inner;
        }

        public iType getType()
        {
            return new refType(inner);
        }

        @Override
        public boolean equals(Object another)
        {
            if (another instanceof refType ref)
                return inner.equals(ref.getInner());
            else
                return false;
        }

        @Override
        public String toString()
        {
            return "Ref(" + inner.toString() + ")";
        }

        public iValue getDefaultValue()
        {
            return new refValue(0, inner);
        }
}
