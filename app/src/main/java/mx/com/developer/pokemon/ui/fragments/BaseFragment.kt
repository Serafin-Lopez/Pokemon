package mx.com.developer.pokemon.ui.fragments

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Base class where all the fragments are going to inherit from.
 * Primary goal for this class is to put variables and functions
 * that are shared across the fragments in order NO to have repeated code.
 */
abstract class BaseFragment : Fragment() {



    fun replaceFragmentWith(fragment: Fragment) {
        fragmentManager?.beginTransaction()
            ?.replace(my_nav_host_fragment.id, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }


    fun popBackStack() {
        fragmentManager?.popBackStack()
    }

    fun backButton() {
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                fragmentManager?.popBackStack()
                return@OnKeyListener true
            }
            false
        })
    }

    fun showToast(message: String?): Toast {
        return Toast.makeText(context,message, Toast.LENGTH_SHORT)
    }



}
