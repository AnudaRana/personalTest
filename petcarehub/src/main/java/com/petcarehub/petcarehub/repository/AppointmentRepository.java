package com.petcarehub.petcarehub.repository;

import com.petcarehub.petcarehub.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDateAndDoctorAndTimeSlot(String date, String doctor, String timeSlot);
    List<Appointment> findByDate(String date);
}
