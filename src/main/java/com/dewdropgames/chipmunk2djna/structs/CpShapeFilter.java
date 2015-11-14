package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/11/15.
 */
public class CpShapeFilter extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList("group", "categories", "mask");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public long group;
    public int categories;
    public int mask;
}

//   https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/cpShape.h#L52

/// Fast collision filtering type that is used to determine if two objects collide before calling collision or query callbacks.
//    typedef struct cpShapeFilter {
//        /// Two objects with the same non-zero group value do not collide.
//        /// This is generally used to group objects in a composite object together to disable self collisions.
//        cpGroup group;
//        /// A bitmask of user definable categories that this object belongs to.
//        /// The category/mask combinations of both objects in a collision must agree for a collision to occur.
//        cpBitmask categories;
//        /// A bitmask of user definable category types that this object object collides with.
//        /// The category/mask combinations of both objects in a collision must agree for a collision to occur.
//        cpBitmask mask;
//     } cpShapeFilter;
