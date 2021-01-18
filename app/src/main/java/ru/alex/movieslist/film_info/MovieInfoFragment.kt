package ru.alex.movieslist.film_info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.alex.movieslist.FilmViewModel
import ru.alex.movieslist.MovieApp
import ru.alex.movieslist.R
import ru.alex.movieslist.databinding.FragmentMovieInfoBinding
import ru.alex.movieslist.responses.FilmResponse


class MovieInfoFragment: Fragment() {

    private lateinit var binding: FragmentMovieInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieInfoBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: FilmViewModel by activityViewModels()

        binding.filmLayout.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        if(viewModel.film?.data?.filmId == viewModel.filmId){
            bindFilm(viewModel.film)
            return
        }

        MovieApp.serverApi.getFilmInfo(viewModel.filmId, "RATING, POSTERS").enqueue(object : Callback<FilmResponse> {
            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                println(response.code())
                println(response.message())
                if (response.isSuccessful) {
                    println(response.body())
                    viewModel.film = response.body()
                    bindFilm(response.body())
                }
            }

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun bindFilm(filmResponse: FilmResponse?) {
        binding.filmLayout.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE

        binding.filmName.text = filmResponse?.data?.nameRu
        if(!filmResponse?.data?.nameEn.isNullOrEmpty()) {
            binding.filmNameEn.text = filmResponse?.data?.nameEn
        }
        binding.filmDesc.text = filmResponse?.data?.description?.trim()
        binding.filmDist.text = filmResponse?.data?.distributors?.trim()
        binding.filmLength.text = filmResponse?.data?.filmLength?.trim()
        binding.filmYear.text = filmResponse?.data?.year?.trim()
        binding.filmSlogan.text = filmResponse?.data?.slogan?.trim()
        binding.filmPremiereRu.text = filmResponse?.data?.premiereRu?.trim()
        binding.filmPremiereWorld.text = filmResponse?.data?.premiereWorld?.trim()
        binding.filmRating.text = filmResponse?.rating?.rating.toString()

        binding.buttonOpenUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(filmResponse?.data?.webUrl))
            startActivity(intent)
        }

        Picasso.get()
            .load(filmResponse?.data?.posterUrl)
            .placeholder(R.drawable.ic_baseline_movie_24)
            .error(R.drawable.ic_launcher_foreground)
            .fit()
            .centerInside() //.transform(new CircleTransform())
            .into(binding.filmPoster)
    }
}