package com.kamil.courseerpbackend.model.entity;

import com.kamil.courseerpbackend.model.enums.UserStatus;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//@SuperBuilder
@Builder
public class User extends BaseEntity{
    String name;
    String surname;
    UserStatus status;
    Long roleId;
    String email;
    String phone_number;
    String password;
}
