package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @NotNull  private String id;
    @NotNull private String name;
    private String telephone;
    private String userName;
    private String title;
}
