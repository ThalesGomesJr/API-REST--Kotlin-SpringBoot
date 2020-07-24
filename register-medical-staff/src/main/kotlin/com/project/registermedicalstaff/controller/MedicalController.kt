package com.project.registermedicalstaff.controller

import com.project.registermedicalstaff.model.Medical
import com.project.registermedicalstaff.service.MedicalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/medical-staff"])
class MedicalController {
    @Autowired
    lateinit var medicalService: MedicalService

    @PostMapping("/register")
    fun register(@RequestBody medical: Medical) : ResponseEntity<AcceptMessage>{
        this.medicalService.register(medical)
        val message = AcceptMessage("OK", Date())
        return ResponseEntity(message, HttpStatus.OK)
    }

    @GetMapping()
    fun getAll(): ResponseEntity<List<Medical>> {
        var list = this.medicalService.getAll()
        val status = if(list.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(list, status)
    }

}