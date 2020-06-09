package com.example.bookstore.model.security;

import com.example.bookstore.model.IdentifiedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role implements IdentifiedEntity {

    @Id
    @ApiModelProperty(position = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "User role ID",
            example = "2")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ApiModelProperty(position = 2)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "name", nullable = false)
    private String name;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
//    transient private List<User> users;

    public Role(String name) {
        this.name = name;
    }
}
