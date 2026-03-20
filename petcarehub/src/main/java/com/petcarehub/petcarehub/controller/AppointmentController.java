package com.petcarehub.petcarehub.controller;

import com.petcarehub.petcarehub.dto.AppointmentRequest;
import com.petcarehub.petcarehub.entity.Appointment;
import com.petcarehub.petcarehub.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequest request) {
        try {
            Appointment saved = appointmentService.createAppointment(request);
            return ResponseEntity.ok(saved);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/booked-slots")
    public ResponseEntity<List<Map<String, String>>> getBookedSlots(@RequestParam String date) {
        List<Map<String, String>> bookedSlots = appointmentService.getBookedSlots(date);
        return ResponseEntity.ok(bookedSlots);
    }
}
