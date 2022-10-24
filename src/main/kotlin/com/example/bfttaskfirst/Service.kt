package com.example.bfttaskfirst

import com.github.demidko.aot.MorphologyTag
import com.github.demidko.aot.WordformMeaning
import com.github.demidko.aot.WordformMeaning.lookupForMeanings
import org.springframework.stereotype.Service

@Service
class Service {

    fun spell(word: String): List<String>? {

        val casesOrder = arrayOf(
            MorphologyTag.Nominative,    // Именительный
            MorphologyTag.Genitive,      // Родительный
            MorphologyTag.Dative,        // Дательный
            MorphologyTag.Accusative,    // Винительный
            MorphologyTag.Ablative,      // Творительный
            MorphologyTag.Prepositional, // Предложный
        )

        val sortFunc = { elem: WordformMeaning ->
            casesOrder.indexOf(
                casesOrder.intersect(
                    elem.morphology.toSet()
                ).first()
            )
        }

        val meanings = lookupForMeanings(word)
        if (meanings.isEmpty())
            return null

        return meanings.first().transformations
            .filter { it.morphology.contains(MorphologyTag.Singular) }
            .sortedBy(sortFunc)
            .map { it.toString() }
    }
}