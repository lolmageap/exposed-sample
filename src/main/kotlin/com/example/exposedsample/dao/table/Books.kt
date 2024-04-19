package com.example.exposedsample.dao.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Books: LongIdTable() {
    val title = varchar("title", 50)
    val author = reference("author", Authors)
}