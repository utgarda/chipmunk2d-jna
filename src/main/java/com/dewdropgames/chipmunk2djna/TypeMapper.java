package com.dewdropgames.chipmunk2djna;

import com.dewdropgames.chipmunk2djna.structs.CpShapeType;
import com.sun.jna.DefaultTypeMapper;
import com.sun.jna.FromNativeContext;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;

/**
 * Created by etsvigun on 11/11/15.
 */
public class TypeMapper extends DefaultTypeMapper {

    TypeMapper() {
        addTypeConverter(CpShapeType.class, new TypeConverter() {

            @Override
            public Object fromNative(Object nativeValue, FromNativeContext context) {
                return CpShapeType.values()[(Integer) nativeValue];
            }

            @Override
            public Object toNative(Object value, ToNativeContext context) {
                return ((CpShapeType) value).ordinal();
            }

            @Override
            public Class nativeType() {
                return Integer.class;
            }
        });
    }
}
