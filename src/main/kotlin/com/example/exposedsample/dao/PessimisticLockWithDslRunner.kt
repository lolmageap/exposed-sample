package com.example.exposedsample.dao

import com.example.exposedsample.dao.table.Authors
import com.example.exposedsample.dao.table.Authors.pessimisticLock
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.vendors.ForUpdateOption.PostgreSQL.MODE.NO_WAIT
import org.jetbrains.exposed.sql.vendors.ForUpdateOption.PostgreSQL.MODE.SKIP_LOCKED

fun main() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres"
    )

    transaction {
        SchemaUtils.create(Authors)

        addLogger(StdOutSqlLogger)

        val generatedKey = Authors.insert {
            it[name] = "cherhy"
            it[age] = 25
            it[email] = "ekxk1234@naver.com"
        }.generatedKey

        val pessimisticLockedAuthor = Authors
            .selectAll()
            .where { Authors.id eq generatedKey }
            .pessimisticLock()
            .map(AuthorVo::fromResultRow)
            .singleOrNull()
            ?: throw IllegalArgumentException("Author not found")

        println("Author: ${pessimisticLockedAuthor.id} ${pessimisticLockedAuthor.name} ${pessimisticLockedAuthor.age} ${pessimisticLockedAuthor.email}")

        val noWaitAuthor = Authors.selectAll()
            .where { Authors.id eq generatedKey }
            .pessimisticLock(NO_WAIT)
            .map(AuthorVo::fromResultRow)
            .singleOrNull()
            ?: throw IllegalArgumentException("Author not found")

        println("Author: ${noWaitAuthor.id} ${noWaitAuthor.name} ${noWaitAuthor.age} ${noWaitAuthor.email}")

        val skipLockedAuthor = Authors.selectAll()
            .where { Authors.id eq generatedKey }
            .pessimisticLock(SKIP_LOCKED)
            .map(AuthorVo::fromResultRow)
            .singleOrNull()
            ?: throw IllegalArgumentException("Author not found")

        println("Author: ${skipLockedAuthor.id} ${skipLockedAuthor.name} ${skipLockedAuthor.age} ${skipLockedAuthor.email}")

        Authors.deleteAll()
    }
}

private val InsertStatement<Number>.generatedKey: Long
    get() {
        val size = this.resultedValues?.size ?: 1
        return this.resultedValues?.getOrNull(size - 1)?.get(Authors.id)?.value
            ?: throw IllegalArgumentException("No generated key returned")
    }