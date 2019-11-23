package org.grocery.store.cash.register;

import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SuccessBase extends BaseTests {

    public SuccessBase() {
        super(SuccessBase.shouldCreatePlayerFields());
    }

    public SuccessBase(Map<String, List<FieldDescriptor>> fieldDescriptors) {
        super(fieldDescriptors);
    }

    private static Map<String, List<FieldDescriptor>> shouldCreatePlayerFields() {

        Map<String, List<FieldDescriptor>> values = new HashMap<>();


        return values;
    }

}
