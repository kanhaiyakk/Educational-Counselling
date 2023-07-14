package com.boot.educationalCounselling.backend.entitytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.boot.educationalCounselling.backend.entity.Role;
import com.boot.educationalCounselling.backend.entity.User;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
    }

    @Test
    public void testSetAndGetId() {
        // Arrange
        long id = 1;

        // Act
        user.setId(id);
        long result = user.getId();

        // Assert
        assertEquals(id, result);
    }

    @Test
    public void testSetAndGetFirstName() {
        // Arrange
        String firstName = "John";

        // Act
        user.setFirstName(firstName);
        String result = user.getFirstName();

        // Assert
        assertEquals(firstName, result);
    }

    @Test
    public void testSetAndGetLastName() {
        // Arrange
        String lastName = "Doe";

        // Act
        user.setLastName(lastName);
        String result = user.getLastName();

        // Assert
        assertEquals(lastName, result);
    }

    @Test
    public void testSetAndGetEmail() {
        // Arrange
        String email = "test@example.com";

        // Act
        user.setEmail(email);
        String result = user.getEmail();

        // Assert
        assertEquals(email, result);
    }

    @Test
    public void testSetAndGetPassword() {
        // Arrange
        String password = "password";

        // Act
        user.setPassword(password);
        String result = user.getPassword();

        // Assert
        assertEquals(password, result);
    }

    @Test
    public void testSetAndGetRoles() {
        // Arrange
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);

        // Act
        user.setRoles(roles);
        Set<Role> result = user.getRoles();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(roles, result);
    }
}
