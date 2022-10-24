package com.example.bfttaskfirst

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.morpher.ws3.ClientBuilder

@Configuration
class Configuration {

    @Bean
    fun getMorpherClient() =
        ClientBuilder().build()
}