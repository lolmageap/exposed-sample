package com.example.exposedsample.dao

import com.example.exposedsample.dao.table.Authors
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver", user = "sa", password = "")

    transaction {
        SchemaUtils.create(Authors)

        Author.new {
            name = "cherhy"
            age = 25
            email = "ekxk1234@naver.com"
        }

        val author = Author.find { Authors.age eq 25 }.single()
        require(author.name == "cherhy")
        require(author.age == 25)
        require(author.email == "ekxk1234@naver.com")

        val updatedAuthor = Author.findByIdAndUpdate(author.id.value) {
            it.name = "cherhy2"
        }

        require(updatedAuthor?.name == "cherhy2")

        val secondUpdatedAuthor = Author.findByNameAndUpdate("cherhy2") {
            name = "cherhy3"
        }

        require(secondUpdatedAuthor.name == "cherhy3")
    }
}
