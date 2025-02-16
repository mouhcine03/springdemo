package com.mouhcine.hospitalapp.Repository;

import com.mouhcine.hospitalapp.Entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;


public interface PatientRepository extends JpaRepository<Patient, Long> {
   Page<Patient> findByNomContainsIgnoreCase(String keyword, Pageable pageable);



}
