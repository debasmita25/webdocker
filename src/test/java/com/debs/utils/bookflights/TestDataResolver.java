package com.debs.utils.bookflights;

public final class TestDataResolver {

    private static final String BASE_FOLDER = "test-data/";

    private TestDataResolver() {}

    public static String resolve(Class<?> testClass) {

        String fileName = testClass.getSimpleName() + ".json";

        return BASE_FOLDER + fileName;
    }
}
