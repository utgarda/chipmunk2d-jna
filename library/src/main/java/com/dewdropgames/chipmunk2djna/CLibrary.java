package com.dewdropgames.chipmunk2djna;

import com.dewdropgames.chipmunk2djna.structs.CpBody;
import com.dewdropgames.chipmunk2djna.structs.CpShape;
import com.dewdropgames.chipmunk2djna.structs.CpSpace;
import com.dewdropgames.chipmunk2djna.structs.CpVect;
import com.sun.jna.Library;
import com.sun.jna.Native;

import java.util.HashMap;
import java.util.Map;

public interface CLibrary extends Library {
    Map<String, Object> OPTIONS = new HashMap<String, Object>() {{
        put(Library.OPTION_TYPE_MAPPER, new TypeMapper());
    }};

    CLibrary CHIPMUNK = (CLibrary) Native.loadLibrary("chipmunk", CLibrary.class, OPTIONS);

    // zero vector
    CpVect CPVZERO = new CpVect(0, 0);

    CpVect.ByValue CPVZERO_BY_VALUE = new CpVect.ByValue(0, 0);

    /**
     * @return new CpSpace structure by reference
     */
    CpSpace.ByReference cpSpaceNew();

    /**
     * Sets gravity vector of a space. Just for consistency, in case you follow Chipmunk's tutorial pages in detail.<br>
     * Same as using <pre> {@code
     * space.gravity.x = GRAVITY_X;
     * space.gravity.y = GRAVITY_Y;
     * //or space.gravity = GRAVITY_VECTOR;
     * space.writeField("gravity");
     * } </pre>
     *
     * @param space   Your instance of CpSpace
     * @param gravity Gravity vector
     */
    void cpSpaceSetGravity(CpSpace space, CpVect.ByValue gravity);

    CpShape cpSegmentShapeNew(CpBody body, CpVect.ByValue a, CpVect.ByValue b, double r);

    /**
     * Adds a collision shape to a body.<br>
     * C source: <a href="https://github.com/slembcke/Chipmunk2D/blob/master/src/cpShape.c#L350">cpShape.c</a>
     * <pre> {@code
     * cpShape *
     * cpCircleShapeNew(cpBody *body, cpFloat radius, cpVect offset)
     * } </pre>
     *
     * @param body   Existing body
     * @param radius Circle radius
     * @param offset Circle center location relative to body center
     * @return Created shape
     */
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

    void cpBodySetPosition(CpBody body, CpVect.ByValue position);


//    https://github.com/slembcke/Chipmunk2D/blob/master/src/cpSpace.c#L686
//    void
//    cpSpaceUseSpatialHash(cpSpace *space, cpFloat dim, int count)

    /**
     * Switch the space to use a spatial hash instead of the bounding box tree.
     * See the manual: <a href="https://chipmunk-physics.net/release/ChipmunkLatest-Docs/#cpSpace-SpatialHash">
     *     Enabling and Tuning the Spatial Hash</a>
     *
     * @param space space alter
     * @param dim the size of the hash cells
     * @param count the suggested minimum number of cells in the hash table
     */
    void cpSpaceUseSpatialHash(CpSpace space, double dim, int count);
}
