package com.example.exposedsample.dao.table

import com.example.exposedsample.BaseLongIdTable

object Books: BaseLongIdTable("book", "id") {
    val title = varchar("title", 50)
    val author = reference("author", Authors)
}