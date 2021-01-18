package ru.alex.movieslist

import retrofit2.Call
import retrofit2.http.*
import ru.alex.movieslist.responses.FilmListResponse
import ru.alex.movieslist.responses.FilmResponse

interface RequestInterface {

    @Headers("X-API-KEY: 1bd3ec43-a14e-404a-9462-921887259fb8")
    @GET("api/v2.1/films/{id}")
    fun getFilmInfo(@Path("id") filmId: Int, @Query("append_to_response") append: String): Call<FilmResponse>

    @Headers("X-API-KEY: 1bd3ec43-a14e-404a-9462-921887259fb8")
    @GET("api/v2.2/films/top")
    fun getFilmsTop(@Query("page") page: Int): Call<FilmListResponse>
}
