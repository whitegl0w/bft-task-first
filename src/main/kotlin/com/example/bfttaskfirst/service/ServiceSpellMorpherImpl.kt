package com.example.bfttaskfirst.service

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import ru.morpher.ws3.Client
import ru.morpher.ws3.russian.ArgumentNotRussianException

@Service
class ServiceSpellMorpherImpl(
    private val morpherClient: Client
): ServiceSpell {
    override fun spell(word: String): List<String>? {

        val declensions = try {
            morpherClient.russian().declension(word)
        } catch (_: ArgumentNotRussianException) {
            return null
        }

        return with(declensions) {
            listOf(
                nominative,
                genitive,
                dative,
                accusative,
                instrumental,
                prepositional,
            )
        }
    }
}