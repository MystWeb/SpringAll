package com.proaim.jpa.repository;

import com.proaim.jpa.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoDao extends JpaRepository<Demo, Long> {
}
