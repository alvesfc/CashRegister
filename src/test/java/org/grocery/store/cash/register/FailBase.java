package org.grocery.store.cash.register;

import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FailBase extends BaseTests {

    public FailBase() {
        super(invalidFields());
    }

    public FailBase(Map<String, List<FieldDescriptor>> fieldDescriptors) {
        super(fieldDescriptors);
    }

    private static Map<String, List<FieldDescriptor>> invalidFields() {
        return new HashMap<>();
    }
}
