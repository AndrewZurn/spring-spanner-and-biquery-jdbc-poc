package com.andrewzurn.spannerandbigquerydata.repository.jdbc

import com.andrewzurn.spannerandbigquerydata.model.BikeRacer
import org.springframework.data.repository.CrudRepository

interface BikeRacerJdbcRepository : CrudRepository<BikeRacer, String> {
    fun findByName(name: String): BikeRacer?
}
