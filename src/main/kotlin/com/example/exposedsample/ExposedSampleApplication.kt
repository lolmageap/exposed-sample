package com.example.exposedsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExposedSampleApplication

fun main(args: Array<String>) {
    runApplication<ExposedSampleApplication>(*args)
}
