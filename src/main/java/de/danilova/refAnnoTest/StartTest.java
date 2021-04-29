package de.danilova.refAnnoTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.SortedMap;
import java.util.TreeMap;

public class StartTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        start();
    }

    static void start() throws InvocationTargetException, IllegalAccessException, InstantiationException {

        Object testObject = TestClass.class.newInstance();
        Method beforeSuitM = null;
        Method afterSuitM = null;
        SortedMap<Integer, Method> priorityMethod = new TreeMap<>();
        Method[] methods = TestClass.class.getDeclaredMethods();

        for(Method m :  methods ) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuitM == null) {
                    beforeSuitM = m;
                }
                else{
                    throw new RuntimeException("There can be only one annotation of this type");
                }

            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuitM == null) {
                    afterSuitM = m;
                }
                else{
                    throw new RuntimeException("There can be only one annotation of this type");
                }

            }
            if (m.isAnnotationPresent(Test.class)) {
                priorityMethod.put(m.getAnnotation(Test.class).priority(), m);
            }
        }
        if (beforeSuitM != null) {
            beforeSuitM.invoke(testObject);
        }

        for (int i = 1; i <= priorityMethod.size(); i++) {
            priorityMethod.get(i).invoke(testObject);
        }

        if (afterSuitM != null) {
            afterSuitM.invoke(testObject);
        }


    }
}
