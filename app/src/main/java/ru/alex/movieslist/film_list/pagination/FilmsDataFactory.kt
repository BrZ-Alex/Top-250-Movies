package ru.alex.movieslist.film_list.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

import ru.alex.movieslist.responses.FilmListResponse

class FilmsDataFactory() : DataSource.Factory<Int, FilmListResponse.FilmShort>() {

    val mutableLiveData = MutableLiveData<FilmsDataSource>()

    override fun create(): DataSource<Int, FilmListResponse.FilmShort> {
        val ordersDataSource = FilmsDataSource()
        mutableLiveData.postValue(ordersDataSource)
        return ordersDataSource
    }
}