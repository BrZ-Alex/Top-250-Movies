package ru.alex.movieslist.film_list.pagination

import androidx.recyclerview.widget.DiffUtil
import ru.alex.movieslist.responses.FilmListResponse

class FilmsDiffUtilCallback: DiffUtil.ItemCallback<FilmListResponse.FilmShort>() {
    override fun areItemsTheSame(
        oldItem: FilmListResponse.FilmShort,
        newItem: FilmListResponse.FilmShort
    ): Boolean {
        println("ARE ITEMS SAME")
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(
        oldItem: FilmListResponse.FilmShort,
        newItem: FilmListResponse.FilmShort
    ): Boolean {
        return  oldItem.filmId == newItem.filmId
    }
}
