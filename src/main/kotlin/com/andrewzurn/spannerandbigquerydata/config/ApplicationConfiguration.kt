package com.andrewzurn.spannerandbigquerydata.config

import com.andrewzurn.spannerandbigquerydata.dialect.GoogleSqlDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import org.springframework.data.relational.core.dialect.Dialect
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class ApplicationConfiguration : AbstractJdbcConfiguration() {

    override fun jdbcDialect(operations: NamedParameterJdbcOperations): Dialect = GoogleSqlDialect()

    @Bean
    fun transactionManager(platformTransactionManager: PlatformTransactionManager): PlatformTransactionManager {
        return platformTransactionManager
    }
}
