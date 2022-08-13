package com.example.demo.repository;

import com.example.demo.model.Key.GroupOfRolesKey;
import com.example.demo.model.GroupOfRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GroupOfRolesRepository extends JpaRepository<GroupOfRoles, GroupOfRolesKey> {
    @Transactional
    @Modifying
    @Query(value = "delete from group_of_roles as role where role.account_id=? and role.role_id=? ", nativeQuery = true)
    void deleteRoleById(Integer accountId, Integer roleId);
    @Transactional
    @Modifying
    @Query(value = "insert into group_of_roles(`account_id`,`role_id`) values (?,?)",
            nativeQuery = true)
    void saveRoleOfAccount(Integer accountId, Integer roleId);
}
