package com.davidk1412.mypetdirectory.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Pet {
    private String id;
    private String identifierName;
    private PetType petType;
    private City city;
}
