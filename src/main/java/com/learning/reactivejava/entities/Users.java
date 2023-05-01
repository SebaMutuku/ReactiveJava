package com.learning.reactivejava.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table("users")
public class Users {
    @Id
    private Long id;
    private String fullName;
    private String lastName;
    private String userName;
    private int age;
}
