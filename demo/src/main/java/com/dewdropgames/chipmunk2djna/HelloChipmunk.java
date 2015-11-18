package com.dewdropgames.chipmunk2djna;


import com.dewdropgames.chipmunk2djna.structs.CpBody;
import com.dewdropgames.chipmunk2djna.structs.CpShape;
import com.dewdropgames.chipmunk2djna.structs.CpSpace;
import com.dewdropgames.chipmunk2djna.structs.CpVect;

import static com.dewdropgames.chipmunk2djna.CLibrary.CHIPMUNK;

/**
 * Replicates
 * <a href="https://chipmunk-physics.net/release/ChipmunkLatest-Docs/#Intro-HelloChipmunk">HelloChipmunk example</a>
 */
public class HelloChipmunk {

    public static void main(String[] args) {
//        System.setProperty("jna.library.path", "~/devenv/Chipmunk2D/src");
        CpSpace.ByReference space = CHIPMUNK.cpSpaceNew();

//        CHIPMUNK.cpSpaceSetGravity(space, new CpVect.ByValue(0, -100));
//        space.gravity.x = 0;
//        space.gravity.y = -100;
        space.writeField("gravity");
        CpShape ground = CHIPMUNK.cpSegmentShapeNew(space.staticBody, new CpVect.ByValue(-20, 5), new CpVect.ByValue(20, -5), 0);

        CHIPMUNK.cpShapeSetFriction(ground, 1);

        CHIPMUNK.cpSpaceAddShape(space, ground);

        // Now let's make a ball that falls onto the line and rolls off.
        // First we need to make a cpBody to hold the physical properties of the object.
        // These include the mass, position, velocity, angle, etc. of the object.
        // Then we attach collision shapes to the cpBody to give it a size and shape.

        double radius = 5;
        double mass = 1;


        // The moment of inertia is like mass for rotation
        // Use the cpMomentFor*() functions to help you approximate it.
        double moment = CHIPMUNK.cpMomentForCircle(mass, 0, radius, new CpVect.ByValue(0, 0));

        // The cpSpaceAdd*() functions return the thing that you are adding.
        // It's convenient to create and add an object in one line.
        CpBody ballBody = CHIPMUNK.cpSpaceAddBody(space, CHIPMUNK.cpBodyNew(mass, moment));
        CHIPMUNK.cpBodySetPosition(ballBody, new CpVect.ByValue(0, 15));

        // Now we create the collision shape for the ball.
        // You can create multiple collision shapes that point to the same body.
        // They will all be attached to the body and move around to follow it.
        CpShape ballShape = CHIPMUNK.cpSpaceAddShape(space, CHIPMUNK.cpCircleShapeNew(ballBody, radius, new CpVect.ByValue(0, 0)));
        CHIPMUNK.cpShapeSetFriction(ballShape, 0.7);


        // Now that it's all set up, we simulate all the objects in the space by
        // stepping forward through time in small increments called steps.
        // It is *highly* recommended to use a fixed size time step.
        double timeStep = 1.0 / 60.0;
        for (double time = 0; time < 2.5; time += timeStep) {
            ballBody.read();
            System.out.printf("Time = %5.2f. ballBody is at (%5.2f, %5.2f). It's velocity is (%5.2f, %5.2f). It's angular velocity = %5.2f\n",
                    time,
                    ballBody.p.x,
                    ballBody.p.y,
                    ballBody.v.x,
                    ballBody.v.y,
                    ballBody.w);
            CHIPMUNK.cpSpaceStep(space, timeStep);
        }
        ballShape.clear();
    }

    //TODO free resources
}
