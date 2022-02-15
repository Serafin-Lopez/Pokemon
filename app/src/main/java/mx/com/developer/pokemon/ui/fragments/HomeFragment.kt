package mx.com.developer.pokemon.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_pokemo.*
import mx.com.developer.pokemon.R
import mx.com.developer.pokemon.api.models.PokemonListModel
import mx.com.developer.pokemon.ui.adapter.PokemonAdapter
import mx.com.developer.pokemon.ui.adapter.PokemonCallback
import mx.com.developer.pokemon.ui.helpers.invisible
import mx.com.developer.pokemon.ui.helpers.show
import mx.com.developer.pokemon.ui.viewmodel.PokemonViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment(), PokemonCallback {


    lateinit var pokemonAdapter : PokemonAdapter
    lateinit var viewModel: PokemonViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            pokemonAdapter = PokemonAdapter().apply { listCallback = this@HomeFragment }
            viewModel = ViewModelProvider(it).get(PokemonViewModel::class.java)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {

        recyclerViewPokemon?.layoutManager =
        GridLayoutManager(context, 2)


        recyclerViewPokemon?.adapter = pokemonAdapter


        viewModel.liveData.observe(viewLifecycleOwner, Observer { list ->

            list?.let {
                pokemonAdapter.setData(it)
            }
        })

        viewModel.getListPokemon()


        actionSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })

        actionSearchView.setOnCloseListener {
            textViewTitleToolbar.show()
            false
        }

        actionSearchView.setOnSearchClickListener(View.OnClickListener {
            textViewTitleToolbar.invisible()
        })
    }

    override fun onSelected(pokemon: PokemonListModel.Result) {
        viewModel.detailPokemon.value = pokemon
        replaceFragmentWith(DetailPokemonFragment())
    }

    private fun filter(text: String) {

        val list = viewModel.liveData.value

        if(text.isEmpty()) {
            list?.let { pokemonAdapter.updateData(it) }
        } else {
            val filteredList = list?.filter { it.name.lowercase().contains(text.lowercase()) }

            if (filteredList != null) {
                if (filteredList.isNotEmpty()) {
                    pokemonAdapter.updateData(ArrayList(filteredList))
                } else {
                    pokemonAdapter.updateData(ArrayList())
                }
            }

        }


    }

}