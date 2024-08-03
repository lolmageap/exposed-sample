package com.example.exposedsample.dao

import com.example.exposedsample.dao.table.Authors
import org.jetbrains.exposed.sql.ResultRow

class AuthorVo private constructor(
    val id: Long,
    val name: String,
    val age: Int,
    val email: String,
) {
    companion object {
        fun fromResultRow(
            row: ResultRow,
        ) =
            AuthorVo(
                id = row[Authors.id].value,
                name = row[Authors.name],
                age = row[Authors.age],
                email = row[Authors.email],
            )
    }
}
