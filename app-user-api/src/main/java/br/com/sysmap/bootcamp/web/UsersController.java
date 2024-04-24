
package br.com.sysmap.bootcamp.web;

import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.sysmap.bootcamp.dto.AuthDto;

import java.util.List;


@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name="Users", description = "Users API")
public class UsersController {

    private final UsersService usersService;

    @Operation(summary = "Save user")
    @PostMapping("/create")
    public ResponseEntity<Users> save(@RequestBody Users user) {
        return ResponseEntity.ok(this.usersService.save(user));
    }
    @Operation(summary = "Auth user")
    @PostMapping("/auth")
    public ResponseEntity<AuthDto> auth(@RequestBody AuthDto user) {
        return ResponseEntity.ok(this.usersService.auth(user));
    }

    @Operation(summary = "Update user")
    @PutMapping("/update")
    public ResponseEntity<Users> update(@RequestBody Users user) {
        return ResponseEntity.ok(this.usersService.update(user));
    }

    @Operation(summary = "List users")
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(this.usersService.listAll());
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(this.usersService.findById(id));
    }
}


