package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/9/15.
 */
public class CpTransform extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList("a", "b", "c", "d", "tx", "ty");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public double a, b, c, d, tx, ty;
}

// https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/chipmunk_types.h#L257

//    typedef struct cpTransform {
//        cpFloat a, b, c, d, tx, ty;
//    } cpTransform;