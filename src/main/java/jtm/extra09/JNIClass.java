package jtm.extra09;

public class JNIClass {

    private String value;

    // Native method declarations 
    public native String createArray(int size);
    public native void printArray(String string);
    public static native void printHello();

    // Java wrapper method for createArray
    public void setValue(int size) {
        this.value = createArray(size);
    }

    // Java getter for value 
    public String getValue() {
        return this.value;
    }

    // Load native library 
    static {
        try {
            System.load(System.getProperty("user.dir") + "/lib/jnifunctions.so");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    // Main method for manual testing 
    public static void main(String[] args) {
        JNIClass jni = new JNIClass();

        printHello();
        jni.setValue(5);
        System.out.println("Created value: " + jni.getValue());
        jni.printArray(jni.getValue()); 
    }
}
