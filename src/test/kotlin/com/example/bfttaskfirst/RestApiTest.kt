package com.example.bfttaskfirst

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RestApiTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun testSpell() {
        val testWords = arrayOf(
            "человек",
            "человека",
            "человеку",
            "человека",
            "человеком",
            "человеке",
        )

        val response = restTemplate.getForEntity(
            "http://localhost:8080/spell/${testWords.first()}",
            Array<String>::class.java
        )

        assert(response.statusCode == HttpStatus.OK)
        assert(response.hasBody())
        response.body?.let {
            assert(it.contentEquals(testWords))
        }
    }
}