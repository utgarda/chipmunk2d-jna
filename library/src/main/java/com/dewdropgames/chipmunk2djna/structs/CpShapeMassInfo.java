package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/11/15.
 */
public class CpShapeMassInfo extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList("m", "i", "cog", "area");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public double m, i, area;

    public CpVect cog;
}

// https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/chipmunk_structs.h#L147

//    struct cpShapeMassInfo {
//        cpFloat m;
//        cpFloat i;
//        cpVect cog;
//        cpFloat area;
//    };
