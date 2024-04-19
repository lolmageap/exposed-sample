package com.example.exposedsample.dao

import com.example.exposedsample.dao.table.Authors
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Author(id: EntityID<Long>): LongEntity(id) {
    var name by Authors.name
    var age by Authors.age
    var email by Authors.email
    companion object: LongEntityClass<Author>(Authors) {
        fun findByNameAndUpdate(
            name: String,
            operator: Author.() -> Unit = {},
        ) =
            Author.find { Authors.name eq name }.singleOrNull()
                ?.apply { operator() }
                ?: throw IllegalArgumentException("Author not found")
    }
}
