package com.andrewzurn.spannerandbigquerydata.model

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import java.time.Instant
import java.util.UUID
import com.google.cloud.spring.data.spanner.core.mapping.Column as SpannerColumn
import com.google.cloud.spring.data.spanner.core.mapping.Table as SpannerTable
import org.springframework.data.relational.core.mapping.Column as JdbcColum
import org.springframework.data.relational.core.mapping.Table as JdbcTable

const val TABLE_NAME = "bike_racer"

@SpannerTable(name = TABLE_NAME)
@JdbcTable(name = TABLE_NAME)
data class BikeRacer(
    @SpannerColumn(name = "name")
    @JdbcColum("name")
    val name: String,

    @SpannerColumn(name = "team")
    @JdbcColum("team")
    val team: String,

    @SpannerColumn(name = "created_at")
    @JdbcColum("created_at")
    val createdAt: Instant = Instant.now(),

    @SpannerColumn(name = "updated_at")
    @JdbcColum("updated_at")
    val updatedAt: Instant = Instant.now(),

    @Id
    @SpannerColumn(name = "id")
    @JdbcColum("id")
    val recordId: String = UUID.randomUUID().toString(),
) : Persistable<String> {
    override fun getId(): String = recordId

    override fun isNew(): Boolean = this.createdAt.epochSecond == this.updatedAt.epochSecond
}
