package com.company.store.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "v_user_view")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserView {
    @Id
    private Long id;

    private String name;

    private String roles;
}
