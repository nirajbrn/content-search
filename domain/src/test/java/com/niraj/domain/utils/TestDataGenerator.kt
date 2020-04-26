package com.niraj.domain.utils

import com.niraj.domain.entities.MovieEntity

class TestDataGenerator {

    companion object {
        fun generateSearchResult(): List<MovieEntity> {
            val s1 = MovieEntity(
                "Jumanji: Welcome to the Jungle",
                "2017",
                "tt2283362",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BODQ0NDhjYWItYTMxZi00NTk2LWIzNDEtOWZiYWYxZjc2MTgxXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
            )
            val s2 = MovieEntity(
                "The Jungle Book",
                "2016",
                "tt2283362",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMTc3NTUzNTI4MV5BMl5BanBnXkFtZTgwNjU0NjU5NzE@._V1_SX300.jpg"
            )
            val s3 = MovieEntity(
                "George of the Jungle",
                "1997",
                "tt2283562",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BNTdiM2VjYjYtZjEwNS00ZWU5LWFkZGYtZGYxMDcwMzY1OTEzL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTczNjQwOTY@._V1_SX300.jpg"
            )
            val s4 = MovieEntity(
                "Mowgli: Legend of the Jungle",
                "2018",
                "tt2229362",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMjMzODc2NzU5MV5BMl5BanBnXkFtZTgwNTMwMTE3NjM@._V1_SX300.jpg"
            )
            val s5 = MovieEntity(
                "Mozart in the Jungle",
                "2014-2018",
                "tt3502172",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMjIzNTYyOTkyOV5BMl5BanBnXkFtZTgwNzk5NzYzNDM@._V1_SX300.jpg"
            )
            return listOf(s1, s2, s3, s4, s5)
        }
    }
}