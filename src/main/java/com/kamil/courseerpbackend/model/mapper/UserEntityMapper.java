package com.kamil.courseerpbackend.model.mapper;

import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.payload.auth.register.RegisterPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    // Learn:
    //  source is param target is User's field name
    //  similar values are not defined in @Mapping
    //  we must use only properties which is present in the User entity
    //  in User we have 7 fields but in registerPayload we have 5 similar fields , we should define additionally
    @Mapping(source = "encryptedPassword" , target = "password")
    @Mapping(source = "roleId" , target = "roleId")
    @Mapping( target = "status" , constant = "ACTIVE")// Regardless of the actual value of the source property or how the mapping logic is generated for other properties, the "status" property in the target type will always be set to the constant value "ACTIVE".
    User fromRegisterPayloadToUser(RegisterPayload registerPayload,String encryptedPassword,Long roleId);
}
