package com.group2.case_study.services;



import com.group2.case_study.dtos.RoleDto;
import com.group2.case_study.models.Role;

import java.util.Optional;

public interface RoleService {

    Iterable<RoleDto> findAll();
    Optional<Role> findById(Long id);
    void save(RoleDto roleDto);
    void remove(Long id);
}
