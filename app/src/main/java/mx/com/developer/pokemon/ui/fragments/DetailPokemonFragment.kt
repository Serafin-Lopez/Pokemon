package mx.com.developer.pokemon.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_detail_pokemon.*
import kotlinx.android.synthetic.main.toolbar_detail.*
import mx.com.developer.pokemon.R
import mx.com.developer.pokemon.ui.helpers.loadImage
import mx.com.developer.pokemon.ui.helpers.loadText
import mx.com.developer.pokemon.ui.viewmodel.PokemonViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailPokemonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailPokemonFragment : BaseFragment() {

    lateinit var viewModel: PokemonViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            viewModel = ViewModelProvider(it).get(PokemonViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        actionBack.setOnClickListener {
            popBackStack()
        }

        viewModel.detailPokemon.observe(viewLifecycleOwner, Observer { pokemon->

            pokemon?.let {
                textViewTitlePokemon.loadText(it.name)
                val number = it.url.takeLast(2).replace("/","")
                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
                imageViewPokemon.loadImage(url)
            }
        })
    }
}