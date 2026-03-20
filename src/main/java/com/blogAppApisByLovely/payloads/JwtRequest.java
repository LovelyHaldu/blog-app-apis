// file: JwtRequest.java
package com.blogAppApisByLovely.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {

    private String username;
    private String password;
}