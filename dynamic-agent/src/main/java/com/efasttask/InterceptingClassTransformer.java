package com.efasttask;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.runtime.Desc;
import javassist.scopedpool.ScopedClassPoolFactoryImpl;
import javassist.scopedpool.ScopedClassPoolRepositoryImpl;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class InterceptingClassTransformer implements ClassFileTransformer {
    private final ScopedClassPoolFactoryImpl scopedClassPoolFactory = new ScopedClassPoolFactoryImpl();
    private ClassPool rootClassPool;

    public void init() {
        Desc.useContextClassLoader = true;
        rootClassPool = ClassPool.getDefault();
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] byteCode = classfileBuffer;
        System.out.println("Transforming class " + className);
        if (className.equals("com/efasttask/FuncClass")) {
            System.out.println("Found the class " + className);
            try {
                ClassPool classPool = scopedClassPoolFactory.create(loader, rootClassPool,
                        ScopedClassPoolRepositoryImpl.getInstance());
                CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
                CtMethod[] methods = ctClass.getDeclaredMethods();

                for (CtMethod method : methods) {
                    if (method.getName().equals("printHello")){
                        method.insertAfter("System.out.println(\"Hello from Java Agent\");");
                    }
                }
                byteCode = ctClass.toBytecode();
                ctClass.detach();
            } catch (Throwable ex) {
                System.err.println("Error in transforming the class: " + className);
            }
        }
        return byteCode;
    }
}
