package com.example.usersservice.application.mapper;

import com.example.usersservice.application.dto.UserRequest;
import com.example.usersservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {

    @Mapping(source = "rolId", target = "role.id")
    User toUserModel (UserRequest userRequest);


}
