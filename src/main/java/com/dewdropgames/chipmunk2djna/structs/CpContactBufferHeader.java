package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/8/15.
 */
public class CpContactBufferHeader extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList("stamp", "next", "numContacts");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public static class ByReference extends CpContactBufferHeader implements Structure.ByReference {
    }

    public int stamp;
    public CpContactBufferHeader.ByReference next;
    public int numContacts;
}

// https://github.com/slembcke/Chipmunk2D/blob/master/src/cpSpaceStep.c#L109

//    struct cpContactBufferHeader {
//        cpTimestamp stamp;
//        cpContactBufferHeader *next;
//        unsigned int numContacts;
//    };