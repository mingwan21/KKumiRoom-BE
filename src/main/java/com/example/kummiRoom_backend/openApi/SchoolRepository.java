package com.example.kummiRoom_backend.openApi;

import com.example.kummiRoom_backend.api.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, String> {
    Optional<School> findBySchoolName(String schoolName);
    School findBySchoolId(Long schoolId);
}