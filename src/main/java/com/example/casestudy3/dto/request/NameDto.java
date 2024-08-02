package com.example.casestudy3.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
public class NameDto {
    private String nameCategory;
    private String name;

    public static void main(String[] args) {
        NameDto n1 = new NameDto();
        n1.setName("t1");
        n1.setNameCategory("t1");
        NameDto n2 = new NameDto();
        n2.setName("t1");
        n2.setNameCategory("t1");
        Set<NameDto> set = new HashSet<>();
        set.add(n1);
        set.add(n2);
        System.out.println(set.toString());
    }

}

