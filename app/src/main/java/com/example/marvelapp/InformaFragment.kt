package com.example.marvelapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.marvelapp.databinding.FragmentInformaBinding
import com.example.marvelapp.model.Characters
import com.example.marvelapp.util.ShPHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InformaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InformaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInformaBinding.inflate(inflater, container, false)
        val character = arguments?.getSerializable("character") as Characters
        val list = ShPHelper.getInstance(requireContext()).getAllChars()
        val name = character.name
        val bio = character.bio
        val image = character.imageurl
        binding.information.text = bio
        binding.textView5.text = name
        binding.imageView.load(image) {
            placeholder(R.drawable.ic_launcher_background)
        }
        binding.downloadbut.setOnClickListener {
            ShPHelper.getInstance(requireContext()).setChars(character)
        }
        var s = 1
        binding.floatingActionButton.setOnClickListener {
            for (i in 0 until list.size) {
                if (list[i] == character) {
                    if (i+s == list.size) {
                        s=0
                        binding.information.text = list[0].bio
                        binding.textView5.text = list[0].name
                        binding.imageView.load(list[0].imageurl) {
                            placeholder(R.drawable.ic_launcher_background)
                        }
                    }
                    else{
                        binding.information.text = list[i+s].bio
                        binding.textView5.text = list[i+s].name
                        binding.imageView.load(list[i+s].imageurl) {
                            placeholder(R.drawable.ic_launcher_background)
                        }
                        s = s+1
                        return@setOnClickListener
                    }
                }
            }
        }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InformaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InformaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}