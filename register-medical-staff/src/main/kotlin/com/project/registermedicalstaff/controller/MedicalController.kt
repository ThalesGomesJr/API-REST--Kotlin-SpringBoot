package com.project.registermedicalstaff.controller

import com.project.registermedicalstaff.responses.ResponseRequest
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
    fun register(@RequestBody medical: Medical) : ResponseEntity<ResponseRequest>{
        this.medicalService.register(medical)
        val message = ResponseRequest("OK", Date())
        return ResponseEntity(message, HttpStatus.OK)
    }

    @PutMapping("/update/{id}")
    fun update(@PathVariable id: Long, @RequestBody medical: Medical) : ResponseEntity<ResponseRequest>{
        var status = HttpStatus.NOT_FOUND
        var message = ResponseRequest("NOT FOUND", Date())
        if ( this.medicalService.getById(id) != null ){
            status = HttpStatus.OK
            message = ResponseRequest("OK", Date())
            this.medicalService.update(id, medical)
        }
        return ResponseEntity(message, status)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<ResponseRequest>{
        var status = HttpStatus.NOT_FOUND
        var message = ResponseRequest("NOT FOUND", Date())
        if (this.medicalService.getById(id) != null){
            status = HttpStatus.OK
            message = ResponseRequest("OK", Date())
            this.medicalService.delete(id)
        }
        return ResponseEntity(message, status)
    }

    @GetMapping("/all")
    fun getAll(): ResponseEntity<Any> {
        val list = this.medicalService.getAll()
        var status = HttpStatus.NOT_FOUND
        var message = ResponseRequest("LIST OF MEDICAL NOT FOUND", Date())
        if(list.size != 0){
            status = HttpStatus.OK
            return ResponseEntity(list, status)
        }
        return ResponseEntity(message, status)
    }

    @GetMapping("/all-paging")
    fun getAllPaging(@RequestParam(required = true, defaultValue = "")start: Int,
                    @RequestParam(required = true, defaultValue = "")size: Int): ResponseEntity<Any> {
        val list = this.medicalService.getAllPaging(start,size)
        var status = HttpStatus.NOT_FOUND
        var message = ResponseRequest("LIST OF MEDICAL NOT FOUND", Date())
        if(list.size != 0){
            status = HttpStatus.OK
            return ResponseEntity(list, status)
        }
        return ResponseEntity(message, status)
    }

    @GetMapping("/search-id/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any>{
        val medical = this.medicalService.getById(id)
        var status = HttpStatus.NOT_FOUND
        val message = ResponseRequest("MEDICAL NOT FOUND", Date())
        if (medical != null) {
            status = HttpStatus.OK
            return ResponseEntity(medical, status)
        }
        return ResponseEntity(message, status)
    }

    @GetMapping("/search-name")
    fun getByName(@RequestParam(required = true, defaultValue = "") name: String): ResponseEntity<Any>{
        val list = this.medicalService.getByName(name)
        var status = HttpStatus.NOT_FOUND
        var message = ResponseRequest("NAME NOT FOUND", Date())
        if(list.size != 0){
            status = HttpStatus.OK
            return ResponseEntity(list, status)
        }
        return ResponseEntity(message, status)
    }

    @GetMapping("/search-crm")
    fun getByCrm(@RequestParam(required = true, defaultValue = "") crm: String): ResponseEntity<Any>{
        val medical = this.medicalService.getByCrm(crm)
        var status = HttpStatus.NOT_FOUND
        val message = ResponseRequest("CRM NOT FOUND", Date())
        if (medical != null) {
            status = HttpStatus.OK
            return ResponseEntity(medical, status)
        }
        return ResponseEntity(message, status)
    }

    @GetMapping("/search-specialty")
    fun getBySpecialty(@RequestParam(required = true, defaultValue = "") specialty: String): ResponseEntity<Any>{
        val list = this.medicalService.getBySpecialty(specialty)
        var status = HttpStatus.NOT_FOUND
        var message = ResponseRequest("SPECIALTY NOT FOUND", Date())
        if(list.size != 0){
            status = HttpStatus.OK
            return ResponseEntity(list, status)
        }
        return ResponseEntity(message, status)
    }


}

