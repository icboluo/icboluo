package com.icboluo.repository;

import com.icboluo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author icboluo
 * @since 2024-03-06 23:43
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
