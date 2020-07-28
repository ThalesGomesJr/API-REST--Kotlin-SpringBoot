package com.project.registermedicalstaff.service.impl

import com.project.registermedicalstaff.model.Medical
import com.project.registermedicalstaff.repository.MedicalRepository
import com.project.registermedicalstaff.service.MedicalService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class MedicalServiceImpl(val medicalRepository: MedicalRepository) : MedicalService {

    override fun register(medical: Medical){
        this.medicalRepository.save(medical)
    }

    override fun update(id: Long, medical: Medical) {
        this.medicalRepository.save(medical.copy(id))
    }

    override fun delete(id: Long) {
        this.medicalRepository.deleteById(id)
    }

    override fun getAll() : List<Medical>  = this.medicalRepository.findAll().toList()

    override fun getAllPaging(start: Int, size: Int): List<Medical> {
        val pages: PageRequest = PageRequest.of(start, size)
        return this.medicalRepository.findAll(pages).toList()
    }

    override fun getById(id: Long): Medical? = this.medicalRepository.findById(id).orElse(null)

    override fun getByName(name: String): List<Medical> {
        val medical = getAll()
        val list =  medical.filter {
            it.name.contains(name, true) }.toList()
        return list
    }

    override fun getByCrm(crm: String): Medical? {
        val list = getAll()

        val medical = list.filter {
            it.crm.equals(crm, true)
        }
        if(medical.isEmpty())
            return null

        return medical.get(0)
    }

    override fun getBySpecialty(specialty: String): List<Medical> {
        val medical = getAll()
        val list =  medical.filter {
            it.specialty.equals(specialty, true) }.toList()
        return list
    }
}