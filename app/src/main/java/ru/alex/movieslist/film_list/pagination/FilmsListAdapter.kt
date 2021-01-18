package ru.alex.movieslist.film_list.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.alex.movieslist.FilmViewModel
import ru.alex.movieslist.R
import ru.alex.movieslist.databinding.ItemFilmBinding
import ru.alex.movieslist.responses.FilmListResponse

class FilmsListAdapter(filmsDiffUtilCallback: FilmsDiffUtilCallback, val viewModel: FilmViewModel):
    PagedListAdapter<FilmListResponse.FilmShort, FilmsListAdapter.FilmViewHolder>(
        filmsDiffUtilCallback
    ) {

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFilmBinding.bind(itemView)
        fun bind(film: FilmListResponse.FilmShort) {
            binding.itemFilmName.text = film.nameRu
            if(film.nameEn.isNullOrEmpty()){
                binding.itemFilmEn.text = film.year
            }else{
                binding.itemFilmEn.text = film.nameEn+", "+film.year
            }

            /*var genres = ""
            film.genres.forEach {
                genres.plus(it.name)
                genres.plus(", ")
            }
            //genres = genres.subSequence(0, genres.length - 3) as String*/
            binding.itemFilmGenre.text = ""

            binding.itemFilmRating.text = film.rating.toString()

            Picasso.get()
                .load(film.posterUrlPreview)
                .placeholder(R.drawable.ic_baseline_movie_24)
                .error(R.drawable.ic_baseline_movie_24)
                .fit()
                .centerInside() //.transform(new CircleTransform())
                .into(binding.itemFilmPoster)

            binding.root.setOnClickListener {
                viewModel.filmId = film.filmId
                Navigation.findNavController(it).navigate(R.id.movieInfoFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = getItem(position) ?: return
        holder.bind(film)
    }


}
