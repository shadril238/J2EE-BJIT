package com.shadril.rolebasedauthjwt.repository;

import com.shadril.rolebasedauthjwt.entity.Role;
import com.shadril.rolebasedauthjwt.entity.RoleType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleType name);

}
