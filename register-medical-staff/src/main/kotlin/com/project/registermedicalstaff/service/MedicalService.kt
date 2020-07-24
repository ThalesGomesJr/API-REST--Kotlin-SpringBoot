package com.project.registermedicalstaff.service

import com.project.registermedicalstaff.model.Medical

interface MedicalService {
    fun register(medical:Medical)
    fun getAll():List<Medical>
}