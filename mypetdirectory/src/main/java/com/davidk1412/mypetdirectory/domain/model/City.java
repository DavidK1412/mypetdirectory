package com.davidk1412.mypetdirectory.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class City {
    private String id;
    private String name;
}
