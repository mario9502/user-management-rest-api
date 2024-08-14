package bg.softuni.usermanagementapp.web;

import bg.softuni.usermanagementapp.model.dto.UserInfoDto;
import bg.softuni.usermanagementapp.model.dto.UserNoIdInfoDto;
import bg.softuni.usermanagementapp.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserInfoDto> add(@RequestBody @Valid UserNoIdInfoDto userNoIdInfoDto){

        UserInfoDto addedUser = this.userService.add(userNoIdInfoDto);

        URI locationUri = ServletUriComponentsBuilder
                .fromUriString("http://localhost:8080/users/{id}")
                .buildAndExpand(addedUser.getId())
                .toUri();

        return ResponseEntity.created(locationUri).body(addedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDto> getById(@PathVariable UUID id){

        UserInfoDto userInfo = this.userService.findById(id);

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/email={email}")
    public ResponseEntity<UserNoIdInfoDto> findByEmail(@PathVariable String email){

        UserNoIdInfoDto userInfo = this.userService.findByEmail(email);

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/all/year={year}")
    public ResponseEntity<List<UserNoIdInfoDto>> findByBirthdayYearBefore(@PathVariable int year){

        List<UserNoIdInfoDto> allByDateOfBirthYearBefore = this.userService.findAllByDateOfBirthYearBefore(year);

        return ResponseEntity.ok(allByDateOfBirthYearBefore);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserInfoDto>> getAll(){

        List<UserInfoDto> infoDtoList = this.userService.findAll();

        return ResponseEntity.ok(infoDtoList);
    }

    @GetMapping("/allSorted")
    public ResponseEntity<List<UserNoIdInfoDto>> getAllSorted(){

        List<UserNoIdInfoDto> infoDtoList = this.userService.findAllSorted();

        return ResponseEntity.ok(infoDtoList);
    }

    @PutMapping("/email={email}")
    public ResponseEntity<UserNoIdInfoDto> editUser(@PathVariable String email, @RequestBody UserNoIdInfoDto userNoIdInfoDto){

        UserNoIdInfoDto editedUser = this.userService.editUserByEmail(email, userNoIdInfoDto);

        return ResponseEntity.ok(editedUser);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email){

        this.userService.delete(email);

        return ResponseEntity.noContent().build();
    }
}
