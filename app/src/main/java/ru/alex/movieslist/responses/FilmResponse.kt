package ru.alex.movieslist.responses

data class FilmResponse(
    val data: CommonFilmData,
    val rating: Rating,
    //val budget: Budget,
    //val reviews: Review
    //val externalId: ,
    val images: Images
){
    data class CommonFilmData(
        val filmId: Int,
        val nameRu: String,
        val nameEn: String,
        val webUrl: String,
        val posterUrl: String,
        val posterUrlPreview: String,
        val year: String,
        val filmLength: String,
        val slogan: String,
        val description: String,
        val premiereRu: String,
        val distributors: String,
        val premiereWorld: String,

        val genres: List<Genre>,
        val facts: List<String>,
    )

    data class Rating(
        val rating: Double,
        val ratingVoteCount: Int,
        val ratingImdb: Double
    )

    data class Images(
        val posters: List<ImageData>,
        val backdrops: List<ImageData>
    ){
        data class ImageData(
            val language: String,
            val url: String,
            val height: Int,
            val width: Int
        )
    }
}
