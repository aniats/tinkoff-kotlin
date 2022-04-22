package ru.tinkoff.homework.lesson9.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import ru.tinkoff.homework.lesson9.configuration.client.SomeClient
import ru.tinkoff.homework.lesson9.repository.SongRepository

@Service
class SongService(private val repository: SongRepository,
                  private val client: SomeClient) {
    fun getSongCover(songId: Int): ByteArray {
        val cover = repository.getSongCover(songId)
        return requireNotNull(cover?.cover) { "Cover was not found" }
    }

    fun newSong(name: String) {
        val songId = repository.addSong(name)
        CoroutineScope(Dispatchers.Default).launch {
            getSetCover(name, songId)
        }
    }

    suspend fun getSetCover(name: String, songId: Int) {
        val cover = client.getCoverByName(name)
        withContext(Dispatchers.IO) {
            repository.addCover(songId, cover)
        }
    }
}