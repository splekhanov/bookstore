package com.example.bookstore.model.user;

import com.example.bookstore.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @ApiModelProperty(position = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ApiModelProperty(position = 2)
    @Column(name = "user_id", updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;

    @ApiModelProperty(position = 3)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ApiModelProperty(position = 4)
    @Column(name = "address_line1", nullable = false)
    private String address_line1;

    @ApiModelProperty(position = 5)
    @Column(name = "address_line2")
    private String address_line2;

    @ApiModelProperty(position = 6)
    @Column(name = "postal", nullable = false)
    private String postal;

    @ApiModelProperty(position = 7)
    @Column(name = "city", nullable = false)
    private String city;

    @ApiModelProperty(position = 8)
    @Column(name = "region", nullable = false)
    private String region;

    @ApiModelProperty(position = 9)
    @Column(name = "country", nullable = false)
    private String country;

}
