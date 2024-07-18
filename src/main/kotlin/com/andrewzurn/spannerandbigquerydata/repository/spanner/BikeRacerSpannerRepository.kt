package com.andrewzurn.spannerandbigquerydata.repository.spanner

import com.andrewzurn.spannerandbigquerydata.model.BikeRacer
import com.google.cloud.spring.data.spanner.repository.SpannerRepository

interface BikeRacerSpannerRepository : SpannerRepository<BikeRacer, String> {
    fun findByName(name: String): BikeRacer?
}
