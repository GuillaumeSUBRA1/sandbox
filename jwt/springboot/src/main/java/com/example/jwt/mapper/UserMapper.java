package com.example.jwt.mapper;

import com.example.jwt.dto.ConnectedUserDTO;
import com.example.jwt.dto.UserDTO;
import com.example.jwt.dto.UserRecord;
import com.example.jwt.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO entityToDTO(UserEntity userEntity);
    ConnectedUserDTO entityToConnectedDTO(UserEntity userEntity);
    UserEntity recordToEntity(UserRecord userRecord);
}
