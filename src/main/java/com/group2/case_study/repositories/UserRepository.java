package com.group2.case_study.repositories;

import com.group2.case_study.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query(nativeQuery = true, value = "select * " +
            "from users u " +
            "where u.fullname like (:fullname);")
    List<User> findByFullName(@Param("fullname") String fullname);

    @Query(nativeQuery = true,
            value = "SELECT r.name FROM roles r " +
                    "JOIN users_roles ur ON ur.role_id = r.id " +
                    "JOIN users u ON ur.user_id = u.id " +
                    "WHERE u.username = :username")
    List<String> findRolesByUsername(@Param("username") String username);
}
