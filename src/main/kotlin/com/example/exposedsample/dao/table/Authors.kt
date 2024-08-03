package com.example.exposedsample.dao.table

import com.example.exposedsample.BaseLongIdTable

object Authors: BaseLongIdTable("author", "id") {
    val name = varchar("name", 50)
    val age = integer("age")
    val email = varchar("email", 50)
}