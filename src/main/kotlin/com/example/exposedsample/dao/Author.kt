package com.example.exposedsample.dao

import org.jetbrains.exposed.dao.id.LongIdTable

object Author: LongIdTable() {
    val name = varchar("name", 50)
    val age = integer("age")
    val email = varchar("email", 50)
}