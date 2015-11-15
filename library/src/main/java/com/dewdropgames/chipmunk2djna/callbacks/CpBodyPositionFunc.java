package com.dewdropgames.chipmunk2djna.callbacks;

import com.dewdropgames.chipmunk2djna.structs.CpBody;
import com.sun.jna.Callback;

/**
 * Created by etsvigun on 11/9/15.
 */
public interface CpBodyPositionFunc extends Callback {
    void invoke(CpBody body, double dt);
}

// https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/cpBody.h#L44

// typedef void (*cpBodyVelocityFunc)(cpBody *body, cpVect gravity, cpFloat damping, cpFloat dt);