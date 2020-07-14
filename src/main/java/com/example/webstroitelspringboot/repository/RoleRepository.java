package com.example.webstroitelspringboot.repository;
import com.example.webstroitelspringboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
