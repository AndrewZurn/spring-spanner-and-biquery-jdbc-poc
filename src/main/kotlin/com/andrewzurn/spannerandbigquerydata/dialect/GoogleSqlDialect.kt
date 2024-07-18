package com.andrewzurn.spannerandbigquerydata.dialect

import org.springframework.data.relational.core.dialect.AbstractDialect
import org.springframework.data.relational.core.dialect.ArrayColumns
import org.springframework.data.relational.core.dialect.LimitClause
import org.springframework.data.relational.core.dialect.LockClause
import org.springframework.data.relational.core.dialect.ObjectArrayColumns
import org.springframework.data.relational.core.sql.IdentifierProcessing
import org.springframework.data.relational.core.sql.IdentifierProcessing.LetterCasing
import org.springframework.data.relational.core.sql.IdentifierProcessing.Quoting
import org.springframework.data.relational.core.sql.LockOptions

@Suppress("INACCESSIBLE_TYPE")
class GoogleSqlDialect : AbstractDialect() {

    override fun limit(): LimitClause = limitClause

    override fun lock(): LockClause = lockClause

    override fun getIdentifierProcessing(): IdentifierProcessing {
        return IdentifierProcessing.create(Quoting.NONE, LetterCasing.LOWER_CASE)
    }

    private val lockClause: LockClause = object : LockClause {
        override fun getClausePosition(): LockClause.Position {
            return LockClause.Position.AFTER_ORDER_BY
        }

        override fun getLock(lockOptions: LockOptions): String {
            throw UnsupportedOperationException("Lock not supported")
        }
    }

    override fun getArraySupport(): ArrayColumns {
        return ObjectArrayColumns.INSTANCE
    }

    private val limitClause: LimitClause = object : LimitClause {
        override fun getClausePosition(): LimitClause.Position {
            return LimitClause.Position.AFTER_ORDER_BY
        }

        override fun getLimit(limit: Long): String {
            return "LIMIT $limit "
        }

        override fun getLimitOffset(limit: Long, offset: Long): String {
            return String.format("LIMIT %d OFFSET %d;", limit, offset)
        }

        override fun getOffset(offset: Long): String {
            return "OFFSET $offset;"
        }
    }
}
