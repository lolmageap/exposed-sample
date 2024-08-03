package com.example.exposedsample.dao

import com.example.exposedsample.BaseEntity
import com.example.exposedsample.dao.table.Books
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Book(id: EntityID<Long>): BaseEntity(id, Books) {
    val title by Books.title
    val author by Author referencedOn Books.author
    companion object: LongEntityClass<Book>(Books)
}
