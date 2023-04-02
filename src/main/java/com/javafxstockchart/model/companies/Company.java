package com.javafxstockchart.model.companies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Company Object from MySQL
 */
@Getter
@Setter
@AllArgsConstructor
public class Company {
    String symbol;
    String name;

}
