package com.traulko.project.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code RequestAttributeHandler} class represents request attribute handler.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class RequestAttributeHandler {
    private Map<String, Object> attributes = new HashMap<>();

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public void setAttributes(HttpServletRequest request) {
        attributes = new HashMap<>();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            attributes.put(attributeName, request.getAttribute(attributeName));
        }
    }
}
