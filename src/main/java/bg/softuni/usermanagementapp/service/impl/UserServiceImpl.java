package bg.softuni.usermanagementapp.service.impl;

import bg.softuni.usermanagementapp.model.UserEntity;
import bg.softuni.usermanagementapp.model.dto.UserInfoDto;
import bg.softuni.usermanagementapp.model.dto.UserNoIdInfoDto;
import bg.softuni.usermanagementapp.repository.UserRepository;
import bg.softuni.usermanagementapp.service.UserService;
import bg.softuni.usermanagementapp.service.exception.EmailAlreadyExistsException;
import bg.softuni.usermanagementapp.service.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserInfoDto add(UserNoIdInfoDto userNoIdInfoDto) {

        UserEntity mappedUser = modelMapper.map(userNoIdInfoDto, UserEntity.class);

        Optional<UserEntity> byEmail = this.userRepository.findByEmail(userNoIdInfoDto.getEmail());

        if (byEmail.isPresent()) {
            throw new EmailAlreadyExistsException("User with this email already exist, try again");
        }

        return modelMapper.map(this.userRepository.save(mappedUser), UserInfoDto.class);
    }

    @Override
    public UserInfoDto findById(UUID id) {

        UserEntity userEntity = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));

        return modelMapper.map(userEntity, UserInfoDto.class);
    }

    @Override
    public UserNoIdInfoDto findByEmail(String email) {

        UserEntity userEntity = this.userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found!"));

        return modelMapper.map(userEntity, UserNoIdInfoDto.class);
    }

    @Override
    public List<UserNoIdInfoDto> findAllSorted() {

        return this.userRepository
                .findAllUsersOrderByFirstNameAndDateOfBirth()
                .stream()
                .map(user -> modelMapper.map(user, UserNoIdInfoDto.class))
                .toList();
    }

    @Override
    public List<UserNoIdInfoDto> findAllByDateOfBirthYearBefore(int year) {

        return this.userRepository.findAllByDateOfBirthYearBefore(year)
                .stream()
                .map(user -> modelMapper.map(user, UserNoIdInfoDto.class))
                .toList();
    }

    @Override
    public List<UserInfoDto> findAll() {

        return this.userRepository.findAll().stream().map(user -> modelMapper.map(user, UserInfoDto.class)).toList();
    }

    @Override
    public UserNoIdInfoDto editUserByEmail(String email, UserNoIdInfoDto userNoIdInfoDto) {

        UserEntity userEntity = this.userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found!"));

        if (!userNoIdInfoDto.getFirstName().isBlank() && userNoIdInfoDto.getFirstName() != null) {
            userEntity.setFirstName(userNoIdInfoDto.getFirstName());
        }
        if (!userNoIdInfoDto.getLastName().isBlank() && userNoIdInfoDto.getLastName() != null) {
            userEntity.setLastName(userNoIdInfoDto.getLastName());
        }
        if (!userNoIdInfoDto.getPhoneNumber().isBlank() && userNoIdInfoDto.getPhoneNumber() != null) {
            userEntity.setPhoneNumber(userNoIdInfoDto.getPhoneNumber());
        }

        return modelMapper.map(this.userRepository.save(userEntity), UserNoIdInfoDto.class);
    }

    @Override
    public void delete(String email) {

        this.userRepository.deleteByEmail(email);
    }
}
