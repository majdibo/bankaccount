package com.majdibo.lab.kata.bankaccount.core.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TestRunner {

    private static final Logger logger = Logger.getLogger(TestRunner.class.getName());
    public static Set<Class<? extends Tests>> tests = new HashSet<>();

    protected TestRunner() {

    }

    public static void run() throws Throwable {
        var entries = tests.stream().map(TestRunner::createInstanceOfTestsClass)
                .flatMap(t -> Arrays.stream(t.getClass().getMethods())
                        .filter(method -> method.isAnnotationPresent(Test.class))
                        .map(method -> new SimpleEntry<>(method, t)))
                .collect(Collectors.toList());

        for (SimpleEntry<Method, Tests> entry : entries) {
            runTest(entry);
        }

    }

    private static void runTest(SimpleEntry<Method, Tests> methodAndObject) throws Throwable {
        try {
            Method method = methodAndObject.getKey();
            Tests testsInstance = methodAndObject.getValue();
            method.invoke(testsInstance);
            logger.info(testsInstance.getClass().getName() + ": " + method.getName().replace('_', ' ') + " : OK");

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof TestFailError) {
                throw e.getCause();
            }

            throw new RuntimeException(e);
        }
    }

    private static Tests createInstanceOfTestsClass(Class<? extends Tests> aClass) {
        try {
            return aClass.getConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
