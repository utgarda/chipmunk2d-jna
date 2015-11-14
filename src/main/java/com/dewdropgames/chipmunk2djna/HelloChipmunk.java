package com.dewdropgames.chipmunk2djna;


import com.dewdropgames.chipmunk2djna.structs.CpBody;
import com.dewdropgames.chipmunk2djna.structs.CpShape;
import com.dewdropgames.chipmunk2djna.structs.CpSpace;
import com.dewdropgames.chipmunk2djna.structs.CpVect;
import com.sun.jna.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by etsvigun on 11/7/15.
 */
//https://chipmunk-physics.net/release/ChipmunkLatest-Docs/#Intro-HelloChipmunk
public class HelloChipmunk {
    public interface CLibrary extends Library {
        Map<String, Object> OPTIONS = new HashMap<String, Object>(){{
            put(Library.OPTION_TYPE_MAPPER, new TypeMapper());
        }};

        CLibrary INSTANCE = (CLibrary) Native.loadLibrary("chipmunk", CLibrary.class, OPTIONS);
//        void printf(String format, Object... args);
        CpSpace.ByReference cpSpaceNew();
//        CpVect cpv();
        void cpSpaceSetGravity(CpSpace space, CpVect.ByValue vect);
        CpShape cpSegmentShapeNew(CpBody body, CpVect.ByValue a, CpVect.ByValue b, double r);

//        https://github.com/slembcke/Chipmunk2D/blob/master/src/cpShape.c#L350

//        cpShape *
//        cpCircleShapeNew(cpBody *body, cpFloat radius, cpVect offset)
        CpShape cpCircleShapeNew(CpBody body, double radius, CpVect.ByValue offset);

        //        void cpSpaceStep(cpSpace *space, cpFloat dt)
        void cpSpaceStep(CpSpace space, double dt);
//        CpShape cpSegmentShapeNew(CpBody body, CpVect.ByValue a, CpVect.ByValue b, double r);

//        https://github.com/slembcke/Chipmunk2D/blob/master/src/cpShape.c#L152
        double cpShapeSetFriction(CpShape shape, double friction);


//        https://github.com/slembcke/Chipmunk2D/blob/master/src/cpSpace.c#L418
//        cpShape *
//        cpSpaceAddShape(cpSpace *space, cpShape *shape)
        CpShape cpSpaceAddShape(CpSpace space, CpShape shape);

//        https://github.com/slembcke/Chipmunk2D/blob/master/src/chipmunk.c#L64
//      cpFloat
//      cpMomentForCircle(cpFloat m, cpFloat r1, cpFloat r2, cpVect offset)
        double cpMomentForCircle(double m, double r1, double r2, CpVect.ByValue offset);


//        https://github.com/slembcke/Chipmunk2D/blob/master/src/cpBody.c#L68

//        cpBody*
//        cpBodyNew(cpFloat mass, cpFloat moment)
        CpBody cpBodyNew(double mass, double moment);

//        https://github.com/slembcke/Chipmunk2D/blob/master/src/cpSpace.c#L439

//        cpBody *
//        cpSpaceAddBody(cpSpace *space, cpBody *body)
        CpBody cpSpaceAddBody(CpSpace space, CpBody body);

//        https://github.com/slembcke/Chipmunk2D/blob/master/src/cpBody.c#L374

//        void
//        cpBodySetPosition(cpBody *body, cpVect position)

        void  cpBodySetPosition(CpBody body, CpVect.ByValue position);
    }


    public static void main(String[] args) {
//        System.setProperty("jna.library.path", "~/devenv/Chipmunk2D/src");
        CpSpace.ByReference space = CLibrary.INSTANCE.cpSpaceNew();

        CLibrary.INSTANCE.cpSpaceSetGravity(space, new CpVect.ByValue(0, -100));
        CpShape ground = CLibrary.INSTANCE.cpSegmentShapeNew(space.staticBody, new CpVect.ByValue(-20, 5), new CpVect.ByValue(20, -5), 0);

        CLibrary.INSTANCE.cpShapeSetFriction(ground, 1);

        CLibrary.INSTANCE.cpSpaceAddShape(space, ground);

        // Now let's make a ball that falls onto the line and rolls off.
        // First we need to make a cpBody to hold the physical properties of the object.
        // These include the mass, position, velocity, angle, etc. of the object.
        // Then we attach collision shapes to the cpBody to give it a size and shape.

        double radius = 5;
        double mass = 1;


        // The moment of inertia is like mass for rotation
        // Use the cpMomentFor*() functions to help you approximate it.
        double moment = CLibrary.INSTANCE.cpMomentForCircle(mass, 0, radius, new CpVect.ByValue(0, 0));

        // The cpSpaceAdd*() functions return the thing that you are adding.
        // It's convenient to create and add an object in one line.
        CpBody ballBody = CLibrary.INSTANCE.cpSpaceAddBody(space, CLibrary.INSTANCE.cpBodyNew(mass, moment));
        CLibrary.INSTANCE.cpBodySetPosition(ballBody, new CpVect.ByValue(0, 15));

        // Now we create the collision shape for the ball.
        // You can create multiple collision shapes that point to the same body.
        // They will all be attached to the body and move around to follow it.
        CpShape ballShape = CLibrary.INSTANCE.cpSpaceAddShape(space, CLibrary.INSTANCE.cpCircleShapeNew(ballBody, radius, new CpVect.ByValue(0,0)));
        CLibrary.INSTANCE.cpShapeSetFriction(ballShape, 0.7);


        // Now that it's all set up, we simulate all the objects in the space by
        // stepping forward through time in small increments called steps.
        // It is *highly* recommended to use a fixed size time step.
        double timeStep = 1.0/60.0;
        for(double time = 0; time < 2.5; time += timeStep){
            ballBody.read();
            System.out.printf("Time = %5.2f. ballBody is at (%5.2f, %5.2f). It's velocity is (%5.2f, %5.2f). It's angular velocity = %5.2f\n",
                    time,
                    ballBody.p.x,
                    ballBody.p.y,
                    ballBody.v.x,
                    ballBody.v.y,
                    ballBody.w);
            CLibrary.INSTANCE.cpSpaceStep(space, timeStep);
        }


        ballShape.clear();

    }
}
