package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/8/15.
 */
public class CpVect extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList("x", "y");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public static class ByValue extends CpVect implements Structure.ByValue {
        public ByValue(double x, double y) {
            super(x, y);
        }

        public ByValue() {
            super();
        }
    }

    ;

    public CpVect(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public CpVect() {
    }

    public double x, y;

    public double len(){
        return Math.sqrt(x * x + y * y);
    }
}
