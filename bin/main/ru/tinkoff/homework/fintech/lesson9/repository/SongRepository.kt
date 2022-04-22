package ru.tinkoff.homework.fintech.lesson9.repository

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Service
import ru.tinkoff.homework.fintech.lesson9.model.Song
import java.io.ByteArrayInputStream
import java.sql.Statement

@Service
class SongRepository(private val jdbcTemplate: JdbcTemplate) {

    private val rm = RowMapper { rs, _ ->
        Song(
            id = rs.getInt("song_id"),
            name = rs.getString("name"),
            cover = rs.getBytes("data")
        )
    }

    fun getSongCover(songId: Int): Song? = try {
        jdbcTemplate.queryForObject("select * from song where song_id = (?)", rm, songId)
    } catch (e: EmptyResultDataAccessException) {
        null
    }

    fun addSong(name: String): Int {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            { connection ->
                connection.prepareStatement("insert into song(name) values (?)", Statement.RETURN_GENERATED_KEYS).apply {
                    setString(1, name)
                }
            },
            keyHolder
        )
        return keyHolder.key?.toInt() ?: throw IllegalArgumentException("Song was not inserted")
    }

    fun addCover(songId: Int, cover: ByteArray) {
        jdbcTemplate.update { connection ->
            connection.prepareStatement(
                "insert into song(cover) values (?) where song_id = (?)",
                Statement.RETURN_GENERATED_KEYS
            ).apply {
                setBlob(1, ByteArrayInputStream(cover), cover.size.toLong())
                setInt(2, songId)
            }
        }
    }
}