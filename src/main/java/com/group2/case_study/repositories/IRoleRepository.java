package com.group2.case_study.repositories;

import com.group2.case_study.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    Set<Role> findAllByNameIn(Set<String> names);
}
