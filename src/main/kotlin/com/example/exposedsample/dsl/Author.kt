package com.example.exposedsample.dsl

import org.jetbrains.exposed.sql.Table

object Author: Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 50)
    val age = integer("age")
    val email = varchar("email", 50)

    override val primaryKey = PrimaryKey(id, name = "PK_Author_ID")
}