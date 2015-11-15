package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/8/15.
 */
public class CpSpatialIndex extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList("klass", "bbfunc", "staticIndex", "dynamicIndex");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public static class ByReference extends CpSpatialIndex implements Structure.ByReference {
    }

    public Pointer klass;
    public Pointer bbfunc;
    public CpSpatialIndex.ByReference staticIndex, dynamicIndex;
}

// https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/cpSpatialIndex.h#L58

//    struct cpSpatialIndex {
//        cpSpatialIndexClass *klass;
//
//        cpSpatialIndexBBFunc bbfunc;
//
//        cpSpatialIndex *staticIndex, *dynamicIndex;
//    };