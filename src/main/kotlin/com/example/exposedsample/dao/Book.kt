package com.example.exposedsample.dao

import org.jetbrains.exposed.dao.id.LongIdTable

object Book: LongIdTable() {
    val title = varchar("title", 50)
    val author = reference("author", Author)
}