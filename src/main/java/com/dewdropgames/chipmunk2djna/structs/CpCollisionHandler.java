package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/8/15.
 */
public class CpCollisionHandler extends Structure{
    private static final List<String> FIELD_ORDER = Arrays.asList(
            "typeA",
            "typeB",
            "beginFunc",
            "preSolveFunc",
            "postSolveFunc",
            "separateFunc",
            "userData");
    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public long typeA;
    public long typeB;

    public Pointer beginFunc; //TODO
    public Pointer preSolveFunc;
    public Pointer postSolveFunc;
    public Pointer separateFunc;

    public Pointer userData;
}

// https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/cpSpace.h#L41

/// Struct that holds function callback pointers to configure custom collision handling.
/// Collision handlers have a pair of types; when a collision occurs between two shapes that have these types, the collision handler functions are triggered.
//    struct cpCollisionHandler {
//        /// Collision type identifier of the first shape that this handler recognizes.
//        /// In the collision handler callback, the shape with this type will be the first argument. Read only.
//        const cpCollisionType typeA;
//        /// Collision type identifier of the second shape that this handler recognizes.
//        /// In the collision handler callback, the shape with this type will be the second argument. Read only.
//        const cpCollisionType typeB;
//        /// This function is called when two shapes with types that match this collision handler begin colliding.
//        cpCollisionBeginFunc beginFunc;
//        /// This function is called each step when two shapes with types that match this collision handler are colliding.
//        /// It's called before the collision solver runs so that you can affect a collision's outcome.
//        cpCollisionPreSolveFunc preSolveFunc;
//        /// This function is called each step when two shapes with types that match this collision handler are colliding.
//        /// It's called after the collision solver runs so that you can read back information about the collision to trigger events in your game.
//        cpCollisionPostSolveFunc postSolveFunc;
//        /// This function is called when two shapes with types that match this collision handler stop colliding.
//        cpCollisionSeparateFunc separateFunc;
//        /// This is a user definable context pointer that is passed to all of the collision handler functions.
//        cpDataPointer userData;
//    };