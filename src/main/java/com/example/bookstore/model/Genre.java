package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genre")
public class Genre implements IdentifiedEntity {

    @Id
    @ApiModelProperty(position = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ApiModelProperty(position = 2)
    @Column(name = "type", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String type;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    transient private List<Book> books;
}
