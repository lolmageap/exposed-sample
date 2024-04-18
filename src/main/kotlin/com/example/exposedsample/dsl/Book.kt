package com.example.exposedsample.dsl

import org.jetbrains.exposed.sql.Table

object Book: Table() {
    val id = long("id").autoIncrement()
    val title = varchar("title", 50)
    val authorId = (long("author_id") references Author.id).nullable()

    override val primaryKey = PrimaryKey(id, name = "PK_Book_ID")
}