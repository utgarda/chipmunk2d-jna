package com.dewdropgames.chipmunk2djna.structs;

import com.dewdropgames.chipmunk2djna.callbacks.CpBodyPositionFunc;
import com.dewdropgames.chipmunk2djna.callbacks.CpBodyVelocityFunc;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/9/15.
 */
public class CpBody extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList(
            "velocity_func",
            "position_func",
            "m",
            "m_inv",
            "i",
            "i_inv",
            "cog",
            "p",
            "v",
            "f",
            "a",
            "w",
            "t",
            "transform",
            "userData",
            "v_bias",
            "w_bias",
            "space",
            "shapeList",
            "arbiterList",
            "constraintList",
            "sleeping"
    );

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public static class ByReference extends CpBody implements Structure.ByReference{};

    public CpBodyVelocityFunc velocity_func;
    public CpBodyPositionFunc position_func;

    public double m, m_inv;

    public double i, i_inv;

    public CpVect cog;

    public CpVect p, v, f;

    public double a, w, t;

    public CpTransform transform;

    public Pointer userData;

    public CpVect v_bias;

    public double w_bias;

    public CpSpace.ByReference space;

    public CpShape.ByReference shapeList;
    public Pointer arbiterList; //TODO
    public Pointer constraintList; //TODO

    public CpSleeping sleeping;
}

// https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/chipmunk_structs.h#L35
//struct cpBody {
//        // Integration functions
//        cpBodyVelocityFunc velocity_func;
//        cpBodyPositionFunc position_func;
//
//        // mass and it's inverse
//        cpFloat m;
//        cpFloat m_inv;
//
//        // moment of inertia and it's inverse
//        cpFloat i;
//        cpFloat i_inv;
//
//        // center of gravity
//        cpVect cog;
//
//        // position, velocity, force
//        cpVect p;
//        cpVect v;
//        cpVect f;
//
//        // Angle, angular velocity, torque (radians)
//        cpFloat a;
//        cpFloat w;
//        cpFloat t;
//
//        cpTransform transform;
//
//        cpDataPointer userData;
//
//        // "pseudo-velocities" used for eliminating overlap.
//        // Erin Catto has some papers that talk about what these are.
//        cpVect v_bias;
//        cpFloat w_bias;
//
//        cpSpace *space;
//
//        cpShape *shapeList;
//        cpArbiter *arbiterList;
//        cpConstraint *constraintList;
//
//        struct {
//        cpBody *root;
//        cpBody *next;
//        cpFloat idleTime;
//        } sleeping;
//        };