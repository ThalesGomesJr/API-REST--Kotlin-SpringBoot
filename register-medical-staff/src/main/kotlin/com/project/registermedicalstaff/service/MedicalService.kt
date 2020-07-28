package com.project.registermedicalstaff.service

import com.project.registermedicalstaff.model.Medical

interface MedicalService {
    fun register(medical:Medical)
    fun update(id: Long, medical: Medical)
    fun delete(id: Long)
    fun getAll():List<Medical>
    fun getAllPaging(start: Int, size: Int): List<Medical>
    fun getById(id: Long): Medical?
    fun getByName(name: String): List<Medical>
    fun getByCrm(crm: String): Medical?
    fun getBySpecialty(specialty: String): List<Medical>
}