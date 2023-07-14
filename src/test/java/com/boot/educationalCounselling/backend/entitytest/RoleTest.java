package com.boot.educationalCounselling.backend.entitytest;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.boot.educationalCounselling.backend.entity.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {

    private Role role;

    @BeforeEach
    public void setup() {
        role = new Role();
    }

    @Test
    public void testSetAndGetId() {
        // Arrange
        long id = 1;

        // Act
        role.setId(id);
        long result = role.getId();

        // Assert
        assertEquals(id, result);
    }

    @Test
    public void testSetAndGetRoleType() {
        // Arrange
        String roleType = "ROLE_ADMIN";

        // Act
        role.setRoleType(roleType);
        String result = role.getRoleType();

        // Assert
        assertEquals(roleType, result);
    }
}
