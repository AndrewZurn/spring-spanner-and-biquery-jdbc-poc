package com.andrewzurn.spannerandbigquerydata

import com.andrewzurn.spannerandbigquerydata.model.BikeRacer
import com.andrewzurn.spannerandbigquerydata.repository.jdbc.BikeRacerJdbcRepository
import com.andrewzurn.spannerandbigquerydata.repository.spanner.BikeRacerSpannerRepository
import com.google.cloud.spring.data.spanner.repository.config.EnableSpannerRepositories
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import java.util.UUID

@SpringBootApplication
@EnableSpannerRepositories(basePackages = ["com.andrewzurn.spannerandbigquerydata.repository.spanner"])
@EnableJdbcRepositories(basePackages = ["com.andrewzurn.spannerandbigquerydata.repository.jdbc"])
class SpannerAndBigQueryDataApplication(
    val bikeRacerSpannerRepository: BikeRacerSpannerRepository,
    val bikeRacerJdbcRepository: BikeRacerJdbcRepository
) {
    @Bean
    fun run(): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            val tadej = BikeRacer(
                recordId = UUID.randomUUID().toString(),
                name = "Tadej Pogačar",
                team = "UAE",
            )
            val jonas = BikeRacer(
                recordId = UUID.randomUUID().toString(),
                name = "Jonas Vingegaard",
                team = "Visma-LAB",
            )
            val remco = BikeRacer(
                recordId = UUID.randomUUID().toString(),
                name = "Remco Evenepoel",
                team = "Soudal–Quick-Step",
            )

			bikeRacerSpannerRepository.saveAll(listOf(tadej, jonas, remco))
			bikeRacerJdbcRepository.saveAll(listOf(tadej, jonas, remco))

			println(bikeRacerSpannerRepository.findByName("Tadej Pogačar"))
			println(bikeRacerJdbcRepository.findByName("Tadej Pogačar"))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpannerAndBigQueryDataApplication>(*args)
}

