package com.project.registermedicalstaff.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Medical(
        @Id
        @GeneratedValue
        @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
        val id: Long = 0L,
        val name: String = "",
        val crm: String = "",
        val specialty: String = "",
        val salary: Double = 0.0
)
