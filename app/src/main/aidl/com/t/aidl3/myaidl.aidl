// myaidl.aidl
package com.t.aidl3;

// Declare any non-default types here with import statements

interface myaidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

            int addition(int a, int b);
}
