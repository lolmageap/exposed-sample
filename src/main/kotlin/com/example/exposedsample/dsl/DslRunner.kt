package com.example.exposedsample.dsl

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun main() {
    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver", user = "sa", password = "")

    transaction {
        SchemaUtils.create(Author)
        Author.insert {
            it[name] = "cherhy"
            it[age] = 25
            it[email] = "ekxk1234@naver.com"
        }

        Author.select(
            Author.id,
            Author.name,
            Author.age,
            Author.email
        ).forEach {
            println("Author: ${it[Author.id]} ${it[Author.name]} ${it[Author.age]} ${it[Author.email]}")
        }

        Author.update({ Author.id eq 1 }) {
            it[name] = "updated cherhy"
        }

        val author = Author.select(
            Author.id,
            Author.name,
            Author.age,
            Author.email
        ).where { Author.id eq 1 }

        val single = author.single()

        require(single[Author.id] == 1L)
        require(single[Author.name] == "updated cherhy")
        require(single[Author.age] == 25)
        require(single[Author.email] == "ekxk1234@naver.com")
    }
}