package ru.alex.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ru.alex.movieslist.film_list.pagination.FilmsDataFactory
import ru.alex.movieslist.responses.FilmListResponse
import ru.alex.movieslist.responses.FilmResponse
import java.util.concurrent.Executors

class FilmViewModel: ViewModel() {
    var filmId: Int = 0
    var film: FilmResponse? = null

    private var executor = Executors.newFixedThreadPool(2);
    var filmsLiveData: LiveData<PagedList<FilmListResponse.FilmShort>>
    val filmsDataFactory = FilmsDataFactory()
    init{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(50)
            .build()
        filmsLiveData = LivePagedListBuilder(filmsDataFactory, config)
            .setFetchExecutor(executor)
            .setBoundaryCallback(object: PagedList.BoundaryCallback<FilmListResponse.FilmShort>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                    //view.isRefreshing=false//visibility = View.GONE
                }

                override fun onItemAtFrontLoaded(itemAtFront: FilmListResponse.FilmShort) {
                    super.onItemAtFrontLoaded(itemAtFront)
                    //  view.isRefreshing=false//visibility = View.GONE
                }
            })
            .build()
    }

}