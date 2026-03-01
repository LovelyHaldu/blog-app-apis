package com.blogAppApisByLovely.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String resourceNmae;
    long fieldValue;

    public ResourceNotFoundException(String resourceName, String resourceNmae, long fieldValue) {
        super(String.format("%s not found with %s : %1",resourceName,resourceNmae,fieldValue));
        this.resourceName = resourceName;
        this.resourceNmae = resourceNmae;
        this.fieldValue = fieldValue;
    }
}
