package com.nuri.socialservice.repository;

import com.nuri.socialservice.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByGroupName(String groupName);
    Group findByGroupNameAndUserId(String groupName, Long id);
}
