package com.mouhcine.hospitalapp;

import com.mouhcine.hospitalapp.Entity.Patient;
import com.mouhcine.hospitalapp.Repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class HospitalappApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalappApplication.class, args);
    }
    @Bean
    public CommandLineRunner start(PatientRepository patientRepository){
        return args -> {

            Patient patient1 = new Patient();
            patient1.setNom("mouhcine");
            patient1.setDateNaissance(new Date());
            patient1.setScore(100);
            patient1.setMalade(false);
            patientRepository.save(patient1);

            Patient patient2 = new Patient(null, "iliass", new Date(), true, 200);
            patientRepository.save(patient2);

            Patient patient3 = new Patient(null, "houcine", new Date(), true, 1000);
            patientRepository.save(patient3);



        };
    }

}
