package com.blogAppApisByLovely.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldValue;

    //constructor hai ye
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        //String.format --> just a way of formating in Strings see carefully
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
