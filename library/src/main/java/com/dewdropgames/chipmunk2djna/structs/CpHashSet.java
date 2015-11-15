package com.dewdropgames.chipmunk2djna.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.PointerByReference;

import java.util.Arrays;
import java.util.List;

/**
 * Created by etsvigun on 11/8/15.
 */
public class CpHashSet extends Structure {
    private static final List<String> FIELD_ORDER = Arrays.asList("entries", "size", "eql", "default_value", "table", "pooledBins", "allocatedBuffers");

    @Override
    protected List getFieldOrder() {
        return FIELD_ORDER;
    }

    public static class ByReference extends CpHashSet implements Structure.ByReference {
    }

    public int entries, size;

    public Pointer eql;
    public Pointer default_value;

    public PointerByReference table;
    public Pointer pooledBins; //TODO

    public CpArray.ByReference allocatedBuffers;
}

// https://github.com/slembcke/Chipmunk2D/blob/master/src/cpHashSet.c#L31

//    struct cpHashSet {
//        unsigned int entries, size;
//
//        cpHashSetEqlFunc eql;
//        void *default_value;
//
//        cpHashSetBin **table;
//        cpHashSetBin *pooledBins;
//
//        cpArray *allocatedBuffers;
//    };