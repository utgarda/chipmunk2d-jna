package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/8/15.
 */
public class CpShape extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList(
            "klass",
            "space",
            "body",
            "massInfo",
            "bb",
            "sensor",
            "e",
            "u",
            "surfaceV",
            "userData",
            "type",
            "filter",
            "next",
            "prev",
            "hashid");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public static class ByReference extends CpShape implements Structure.ByReference {
    }

    @Override
    public void clear() {
        super.clear();
    }

    public Pointer klass; //TODO

    public CpSpace.ByReference space;
    public CpBody.ByReference body;
    public CpShapeMassInfo massInfo;
    public CpBB bb;

    public boolean sensor;

    public double e;
    public double u;
    public CpVect surfaceV;

    public Pointer userData;

    public long type;
    public CpShapeFilter filter;

    public CpShape.ByReference next;
    public CpShape.ByReference prev;

    public long hashid;
}

// https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/chipmunk_structs.h#L177

//    struct cpShape {
//        const cpShapeClass *klass;
//
//        cpSpace *space;
//        cpBody *body;
//        struct cpShapeMassInfo massInfo;
//        cpBB bb;
//
//        cpBool sensor;
//
//        cpFloat e;
//        cpFloat u;
//        cpVect surfaceV;
//
//        cpDataPointer userData;
//
//        cpCollisionType type;
//        cpShapeFilter filter;
//
//        cpShape *next;
//        cpShape *prev;
//
//        cpHashValue hashid;
//     };
