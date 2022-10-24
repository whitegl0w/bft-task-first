package com.example.bfttaskfirst

import com.example.bfttaskfirst.service.ServiceSpell
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
class SpellRestController(
    private val mService: ServiceSpell
) {
    @GetMapping("spell/{word}")
    fun getSpell(@PathVariable word: String) =
        mService.spell(word) ?: throw UnknownWordException(word)
}

internal class UnknownWordException(word: String) : RuntimeException("Неизвестное слово: '$word'")

@ControllerAdvice
internal class RestAdvice {
    @ResponseBody
    @ExceptionHandler(UnknownWordException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun unknownWordHandler(ex: UnknownWordException) =
        ex.message
}
