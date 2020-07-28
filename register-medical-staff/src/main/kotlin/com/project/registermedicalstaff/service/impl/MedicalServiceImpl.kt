package com.project.registermedicalstaff.service.impl

import com.project.registermedicalstaff.model.Medical
import com.project.registermedicalstaff.repository.MedicalRepository
import com.project.registermedicalstaff.service.MedicalService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class MedicalServiceImpl(val medicalRepository: MedicalRepository) : MedicalService {

    //Exclui o cache ao registrar um médico
    @CacheEvict("medical-staff", allEntries = true)
    override fun register(medical: Medical){
        this.medicalRepository.save(medical)
    }

    //Exclui o cache ao atualizar o registro de um médico
    @CacheEvict("medical-staff", allEntries = true)
    override fun update(id: Long, medical: Medical) {
        this.medicalRepository.save(medical.copy(id))
    }

    //Exclui o cache ao deletar o registro de um médico
    @CacheEvict("medical-staff", allEntries = true)
    override fun delete(id: Long) {
        this.medicalRepository.deleteById(id)
    }

    //Utilizando o cache
    @Cacheable("medical-staff")
    override fun getAll() : List<Medical>  = this.medicalRepository.findAll().toList()

    //Utilizando o cache
    @Cacheable("medical-staff")
    override fun getAllPaging(start: Int, size: Int): List<Medical> {
        val pages: PageRequest = PageRequest.of(start, size)
        return this.medicalRepository.findAll(pages).toList()
    }

    //Utilizando o cache
    @Cacheable("medical-staff")
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