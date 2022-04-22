package ru.tinkoff.homework.lesson9.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ru.tinkoff.homework.lesson9.service.SongService

@RestController
@RequestMapping()
class SongController(private val songService: SongService) {
    @PostMapping("/song")
    fun createSong(@RequestParam song: String): Unit = songService.newSong(song)

    @GetMapping("/song/{songId}", produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun getCover(@PathVariable songId: Int): ByteArray = songService.getSongCover(songId)
}