package com.navi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor     // Lombok annotation that will create a constructor with all fields in the class
@NoArgsConstructor      // Lombok annotation that will create a constructor with no fields in the class (Necessary when declaring all args constructor)
@Getter
@Setter
public class Stock {
    private String ticker;
    
    private String name;

    private String price;

    private String open;

    private String volume;
}
