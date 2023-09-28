package com.example.jpatest.entity;

import com.example.jpatest.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;


@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "UserDto",
                classes = @ConstructorResult(
                        targetClass = UserDto.class,
                        columns = {
                                @ColumnResult(name = "id", type=Long.class),
                                @ColumnResult(name = "name", type=String.class),
                                @ColumnResult(name = "email", type=String.class)
                        }
                )
        )
})
@NamedNativeQuery(
        name = "getUserDto",
        resultSetMapping = "UserDto",
        query = """
            SELECT u.id, u.name, u.email 
            FROM user u
            WHERE u.name = :name
""")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
}

