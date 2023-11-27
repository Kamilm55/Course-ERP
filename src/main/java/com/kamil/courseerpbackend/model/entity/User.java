package com.kamil.courseerpbackend.model.entity;

import com.kamil.courseerpbackend.model.enums.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
//@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id; // it must be come from BaseEntity
    String name;
    String surname;
    @Enumerated(EnumType.STRING)
    UserStatus status;
    Long roleId;
    String email;
    String phoneNumber;
    String password;
}
