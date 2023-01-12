/* Decompiler 6ms, total 305ms, lines 27 */
package io.zhile.research.intellij.ier.helper;

import java.lang.reflect.Method;

public class ReflectionHelper {
    public static Method getMethod(Class<?> klass, String methodName, Class<?>... methodParameterTypes) {
        try {
            return klass.getMethod(methodName, methodParameterTypes);
        } catch (NoSuchMethodException var4) {
            return null;
        }
    }

    public static Method getMethod(String className, String methodName, Class<?>... methodParameterTypes) {
        Class<?> klass = getClass(className);
        return null == klass ? null : getMethod(klass, methodName, methodParameterTypes);
    }

    public static Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException var2) {
            return null;
        }
    }
}
