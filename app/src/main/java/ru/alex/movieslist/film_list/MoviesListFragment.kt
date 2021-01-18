package ru.alex.movieslist.film_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.alex.movieslist.FilmViewModel
import ru.alex.movieslist.film_list.pagination.FilmsListAdapter
import ru.alex.movieslist.databinding.FragmentMoviesListBinding
import ru.alex.movieslist.film_list.pagination.FilmsDiffUtilCallback

class MoviesListFragment: Fragment() {

    private lateinit var binding: FragmentMoviesListBinding
    private var filmViewModel = FilmViewModel()
    private val viewModel: FilmViewModel by activityViewModels()
    private lateinit var adapter: FilmsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = FilmsListAdapter(FilmsDiffUtilCallback(), viewModel)

        binding = FragmentMoviesListBinding.inflate(layoutInflater)

        binding.filmsList.run {
            this.adapter = adapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            setNewAdapter()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if(adapter.itemCount<=0) {
                setNewAdapter()
        }
        return binding.root
    }

    private fun setNewAdapter(){
        println("new adapter")
        //binding.progressBar.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing=true
        //filmViewModel = FilmViewModel()
        viewModel.filmsLiveData.observe(viewLifecycleOwner, {
            binding.swipeRefreshLayout.isRefreshing=false
            adapter.submitList(it)
        } )
        adapter.notifyDataSetChanged()
        binding.filmsList.adapter = adapter
    }
}