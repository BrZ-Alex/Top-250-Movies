package ru.alex.movieslist.film_list.pagination

import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.alex.movieslist.MovieApp
import ru.alex.movieslist.responses.FilmListResponse

class FilmsDataSource: PageKeyedDataSource<Int, FilmListResponse.FilmShort>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, FilmListResponse.FilmShort>
    ) {
        MovieApp.serverApi.getFilmsTop(1).enqueue(object : Callback<FilmListResponse> {
            override fun onResponse(
                call: Call<FilmListResponse>,
                response: Response<FilmListResponse>
            ) {
                //println(response.code())
                //println(response.message())

                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.films, null, 2)
                }
            }

            override fun onFailure(call: Call<FilmListResponse>, t: Throwable) {
                println(t.message)
            }

        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, FilmListResponse.FilmShort>
    ) {
        val prevKey: Int? = if(params.key>1) params.key-1 else null
        MovieApp.serverApi.getFilmsTop(params.key).execute().also {
            callback.onResult(it.body()!!.films, prevKey)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, FilmListResponse.FilmShort>
    ) {
        MovieApp.serverApi.getFilmsTop(params.key).execute().also {
            //println(it.code())
            //println(it.message())
            callback.onResult(it.body()!!.films, params.key + 1)
        }
    }

}
