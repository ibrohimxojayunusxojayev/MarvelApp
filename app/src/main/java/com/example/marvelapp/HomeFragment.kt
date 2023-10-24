package com.example.marvelapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.Adapter.CharacterAdapter
import com.example.marvelapp.databinding.FragmentHomeBinding
import com.example.marvelapp.model.Characters
import retrofit2.Call
import retrofit2.Callback
import com.example.marvelapp.util.API.APIClient
import com.example.marvelapp.util.API.APIService
import com.example.marvelapp.util.ShPHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var charList: MutableList<Characters>

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

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val api = APIClient.getInstance().create(APIService::class.java)
        charList = mutableListOf()
        charList.add(
            Characters(
                "Lee had one younger brother named Larry Lieber. He said in 2006 that as a child he was influenced by books and movies, particularly those with Errol Flynn playing heroic roles",
                "Stan Lee",
                "2022",
                "https://images.hdqwalls.com/download/spiderman-2-ps5-2023-tv-1080x1920.jpg",
                "Spider-Man",
                "Stan Lee",
                "Peter Parker",
                "Avengers"
            )
        )
        charList.add(
            Characters(
                "\n" +
                        "Stan Lee created, co-created, and recreated hundreds of superhero characters, most notably Spider-Man, The Incredible Hulk, X-Men, and Captain America. Throughout his career he has devoted himself to the excellence, growth, and availability of this unique American art form. Stan Lee was born in New York City in 1922",
                "Stan Lee",
                "2022",
                "https://images.hdqwalls.com/download/kraven-the-hunter-in-marvels-spider-man-2-l7-1080x1920.jpg",
                "Super-Man",
                "Stan Lee",
                "Peter Parker",
                "Avengers"
            )
        )
        charList.add(
            Characters(
                "Batman[a] is a superhero appearing in American comic books published by DC Comics. The character was created by artist Bob Kane and writer Bill Finger, and debuted in the 27th issue of the comic book Detective Comics on March 30, 1939. ",
                "Stan Lee",
                "2012",
                "https://upload.wikimedia.org/wikipedia/en/c/c7/Batman_Infobox.jpg",
                "Bet-Man",
                "Stan Lee",
                "Oliver Uols",
                "Joker"
            )
        )
        charList.add(
            Characters(
                "The Hulk is a superhero appearing in American comic books published by Marvel Comics. Created by writer Stan Lee and artist Jack Kirby, the character first appeared in the debut issue of The Incredible Hulk (May 1962).",
                "Stan Lee",
                "2020",
                "https://w0.peakpx.com/wallpaper/936/825/HD-wallpaper-hulk-the-incredible-hulk-avengers-marvel-heros.jpg",
                "Hulk",
                "Stan Lee",
                "Martin Josh",
                "Hulk"
            )
        )

        ShPHelper.getInstance(requireContext()).setAllChars(charList)
        val adapter = CharacterAdapter(charList, object : CharacterAdapter.MyPost {
            override fun onItemClick(characters: Characters) {
                val bundle = bundleOf()
                bundle.putSerializable("character", characters)
                findNavController().navigate(
                    R.id.action_menuHomeFragment_to_informaFragment,
                    bundle
                )
            }

        })
        binding.charRV.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.charRV.adapter = adapter

        api.getAllCharacters().enqueue(object : Callback<List<Characters>> {
            override fun onResponse(
                call: Call<List<Characters>>,
                response: retrofit2.Response<List<Characters>>
            ) {
                if (response.isSuccessful && response.body() != null) {

                    charList = response.body() as MutableList<Characters>

                    adapter.notifyDataSetChanged()
                    binding.charRV.layoutManager =
                        GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
                    binding.charRV.adapter = adapter
                    Log.d("TAG", "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<Characters>>, t: Throwable) {
                Log.d("TAG", "onFailure: $call")
            }
        })

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}