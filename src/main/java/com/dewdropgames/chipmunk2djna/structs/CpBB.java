package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/11/15.
 */
public class CpBB extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList("l", "b", "r", "t");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public double l, b, r, t;
}

//https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/cpBB.h#L33

/// Chipmunk's axis-aligned 2D bounding box type. (left, bottom, right, top)
//    typedef struct cpBB{
//        cpFloat l, b, r ,t;
//    } cpBB;