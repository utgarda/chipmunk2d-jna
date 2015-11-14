package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/8/15.
 */
public class CpShapeClass extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList(
            "type", "cacheData", "destroy", "pointQuery", "segmentQuery");
    @Override
    protected List getFieldOrder() {
        return null;
    }

    public static class ByReference extends CpShapeClass implements Structure.ByReference{};

    public CpShapeType type;
}

// https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/chipmunk_structs.h#L168

//    struct cpShapeClass {
//        cpShapeType type;
//
//        cpShapeCacheDataImpl cacheData;
//        cpShapeDestroyImpl destroy;
//        cpShapePointQueryImpl pointQuery;
//        cpShapeSegmentQueryImpl segmentQuery;
//    };


