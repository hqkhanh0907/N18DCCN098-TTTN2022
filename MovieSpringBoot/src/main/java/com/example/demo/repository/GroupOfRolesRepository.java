package com.example.demo.repository;

import com.example.demo.model.Key.GroupOfRolesKey;
import com.example.demo.model.GroupOfRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupOfRolesRepository extends JpaRepository<GroupOfRoles, GroupOfRolesKey> {
}
