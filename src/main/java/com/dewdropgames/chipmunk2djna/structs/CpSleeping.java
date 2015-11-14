package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/9/15.
 */
public class CpSleeping extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList("root", "next", "idleTime");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public class ByValue extends CpSleeping implements Structure.ByValue {
    }

        public CpBody.ByReference root, next;
//    public Pointer root, next;
    public double idleTime;
}

//        struct {
//        cpBody *root;
//        cpBody *next;
//        cpFloat idleTime;
//        } sleeping;