package com.viko.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.viko.student.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query(value = "select max(nilai) from student", nativeQuery = true)
	public Integer getNilaiMax();

	@Query(value = "select avg(nilai) from student", nativeQuery = true)
	public Integer getNilaiAverage();
}