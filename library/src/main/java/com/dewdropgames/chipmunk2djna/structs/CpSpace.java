package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/8/15.
 */
public class CpSpace extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList(
            "iterations",
            "gravity",
            "damping",
            "idleSpeedThreshold",
            "sleepTimeThreshold",
            "collisionSlop",
            "collisionBias",
            "collisionPersistence",
            "userData",
            "stamp",
            "curr_dt",
            "dynamicBodies",
            "staticBodies",
            "rousedBodies",
            "sleepingComponents",
            "shapeIDCounter",
            "staticShapes",
            "dynamicShapes",
            "constraints",
            "arbiters",
            "contactBuffersHead",
            "cachedArbiters",
            "pooledArbiters",
            "allocatedBuffers",
            "locked",
            "usesWildcards",
            "collisionHandlers",
            "defaultHandler",
            "skipPostStep",
            "postStepCallbacks",
            "staticBody",
            "_staticBody"
    );

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public static class ByReference extends CpSpace implements Structure.ByReference {
    }

    public int iterations;

    public CpVect gravity;
    public double damping;

    public double idleSpeedThreshold;
    public double sleepTimeThreshold;

    public double collisionSlop;
    public double collisionBias;
    public int collisionPersistence;

    public Pointer userData;

    public int stamp;
    public double curr_dt;

    public CpArray.ByReference dynamicBodies;
    public CpArray.ByReference staticBodies;
    public CpArray.ByReference rousedBodies;
    public CpArray.ByReference sleepingComponents;

    public long shapeIDCounter;
    public CpSpatialIndex.ByReference staticShapes;
    public CpSpatialIndex.ByReference dynamicShapes;

    public CpArray.ByReference constraints;

    public CpArray.ByReference arbiters;
    public CpContactBufferHeader.ByReference contactBuffersHead;
    public CpHashSet.ByReference cachedArbiters;
    public CpArray.ByReference pooledArbiters;

    public CpArray.ByReference allocatedBuffers;
    public int locked;
    public boolean usesWildcards; //TODO
    public CpHashSet.ByReference collisionHandlers;
    public CpCollisionHandler defaultHandler;

    public boolean skipPostStep;
    public CpArray.ByReference postStepCallbacks;

    public CpBody.ByReference staticBody;
    public CpBody _staticBody;
}
//https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/chipmunk_structs.h#L396

//    struct cpSpace {
//        int iterations;
//
//        cpVect gravity;
//        cpFloat damping;
//
//        cpFloat idleSpeedThreshold;
//        cpFloat sleepTimeThreshold;
//
//        cpFloat collisionSlop;
//        cpFloat collisionBias;
//        cpTimestamp collisionPersistence;
//
//        cpDataPointer userData;
//
//        cpTimestamp stamp;
//        cpFloat curr_dt;
//
//        cpArray *dynamicBodies;
//        cpArray *staticBodies;
//        cpArray *rousedBodies;
//        cpArray *sleepingComponents;
//
//        cpHashValue shapeIDCounter;
//        cpSpatialIndex *staticShapes;
//        cpSpatialIndex *dynamicShapes;
//
//        cpArray *constraints;
//
//        cpArray *arbiters;
//        cpContactBufferHeader *contactBuffersHead;
//        cpHashSet *cachedArbiters;
//        cpArray *pooledArbiters;
//
//        cpArray *allocatedBuffers;
//        unsigned int locked;
//
//        cpBool usesWildcards;
//        cpHashSet *collisionHandlers;
//        cpCollisionHandler defaultHandler;
//
//        cpBool skipPostStep;
//        cpArray *postStepCallbacks;
//
//        cpBody *staticBody;
//        cpBody _staticBody;
//        };