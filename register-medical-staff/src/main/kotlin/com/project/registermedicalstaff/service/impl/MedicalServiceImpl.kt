package com.project.registermedicalstaff.service.impl

import com.project.registermedicalstaff.model.Medical
import com.project.registermedicalstaff.repository.MedicalRepository
import com.project.registermedicalstaff.service.MedicalService
import org.springframework.stereotype.Component

@Component
class MedicalServiceImpl(val medicalRepository: MedicalRepository) : MedicalService {

    override fun register(medical: Medical){
        this.medicalRepository.save(medical)
    }

    override fun getAll() : List<Medical>  = this.medicalRepository.findAll().toList()

}