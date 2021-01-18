package ru.alex.movieslist.responses

data class FilmListResponse(
    val pagesCount: Int,
    val films: List<FilmShort>
){
    data class FilmShort(
        val filmId: Int,
        val nameRu: String,
        val nameEn: String,
        val year: String,
        val filmLength: String,
        //val countries
        val genres: List<Genre>,
        val rating: Double,
        val ratingVoteCount: Int,
        val postedUrl: String,
        val posterUrlPreview: String
    )
}
