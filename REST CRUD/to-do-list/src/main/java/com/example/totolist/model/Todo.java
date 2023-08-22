package com.example.totolist.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo {
    private Integer id;
    private String title;
    private boolean status;
}
