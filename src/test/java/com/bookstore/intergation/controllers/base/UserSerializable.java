package com.bookstore.intergation.controllers.base;

import com.bookstore.model.IdentifiedEntity;
import com.bookstore.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserSerializable implements IdentifiedEntity {

    private Long id;
    private String name;
    private String password;
    private boolean enabled;
    private List<Role> roles;
}
