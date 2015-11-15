package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Structure;
import com.sun.jna.ptr.PointerByReference;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/8/15.
 */
public class CpArray extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList("num", "max", "arr");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public static class ByReference extends CpArray implements Structure.ByReference {
    }

    public int num, max;
    public PointerByReference arr;
}

// https://github.com/slembcke/Chipmunk2D/blob/master/include/chipmunk/chipmunk_structs.h#L30
//    struct cpArray {
//        int num, max;
//        void **arr;
//    };
