package com.debs.utils.bookflights;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

public final class TestDataLoader {

    private TestDataLoader() {}

    public static <T> Map<String, List<T>> load(Class<?> testClass,
                                                TypeReference<Map<String, List<T>>> typeRef) {

        String fileName = TestDataResolver.resolve(testClass);

        return JsonDataReader.read(fileName, typeRef);
    }

}
