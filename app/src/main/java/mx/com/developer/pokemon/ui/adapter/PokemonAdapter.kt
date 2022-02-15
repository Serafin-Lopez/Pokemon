package mx.com.developer.pokemon.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_detail_pokemon.*
import kotlinx.android.synthetic.main.item_pokemon_list.view.*
import mx.com.developer.pokemon.R
import mx.com.developer.pokemon.api.models.PokemonListModel
import mx.com.developer.pokemon.ui.helpers.loadImage
import mx.com.developer.pokemon.ui.helpers.loadText


class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.AdapterViewHolder>() {

    private var data: List<PokemonListModel.Result> = ArrayList()
    var listCallback: PokemonCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokemon_list, parent, false),
            listCallback
        )
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: AdapterViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(data[position])
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<PokemonListModel.Result>) {
        this.data = data
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<PokemonListModel.Result>) {
        this.data = data
        notifyDataSetChanged()
    }


    class AdapterViewHolder(itemView: View, var pokemonCallback: PokemonCallback?) : RecyclerView.ViewHolder(itemView) {

        private var data: PokemonListModel.Result?= null

        fun bind(item: PokemonListModel.Result) = with(itemView) {

            textViewTitle.loadText(item.name)
            data = item

            val number = item.url.takeLast(2).replace("/","")
            val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
            imageViewPokemon.loadImage(url)

            setOnClickListener {
                selectItem()
            }



        }

        /**
         * this function was created to select the item in recyclerView
         */

        fun selectItem() {
            data?.let {
                pokemonCallback?.onSelected(it)
            }
        }

    }

}

interface PokemonCallback {
    fun onSelected(pokemon: PokemonListModel.Result)
}