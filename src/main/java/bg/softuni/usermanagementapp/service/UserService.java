package bg.softuni.usermanagementapp.service;


import bg.softuni.usermanagementapp.model.dto.UserInfoDto;
import bg.softuni.usermanagementapp.model.dto.UserNoIdInfoDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserInfoDto add(UserNoIdInfoDto userNoIdInfoDto);

    UserInfoDto findById(UUID id);

    List<UserInfoDto> findAll();

    UserNoIdInfoDto editUserByEmail(String email, UserNoIdInfoDto userNoIdInfoDto);

    void delete(String email);

    UserNoIdInfoDto findByEmail(String email);

    List<UserNoIdInfoDto> findAllSorted();

    List<UserNoIdInfoDto> findAllByDateOfBirthYearBefore(int year);
}
