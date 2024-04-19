package com.example.exposedsample.dao.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Authors: LongIdTable() {
    val name = varchar("name", 50)
    val age = integer("age")
    val email = varchar("email", 50)
}