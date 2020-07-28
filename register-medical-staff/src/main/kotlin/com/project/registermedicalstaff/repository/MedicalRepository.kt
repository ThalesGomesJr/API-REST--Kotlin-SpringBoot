package com.project.registermedicalstaff.repository

import com.project.registermedicalstaff.model.Medical
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface MedicalRepository : PagingAndSortingRepository <Medical, Long> {
}