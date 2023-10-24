package com.example.marvelapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.marvelapp.databinding.FragmentCreateBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateFragment : Fragment() {
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

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCreateBinding.inflate(inflater, container, false)
        binding.alredyhaveacc.setOnClickListener{
            findNavController().navigate(R.id.action_createFragment_to_loginFragment2)
        }

        var userList = mutableListOf<User>()
        var type = object : TypeToken<List<User>>(){}.type
        var gson = Gson()

        var getPreferences = requireActivity().getSharedPreferences("login", AppCompatActivity.MODE_PRIVATE)
        var edit = getPreferences.edit()

        binding.signup.setOnClickListener{
            if ( binding.emailrgstrcrt1.text!!.isEmpty() || binding.passwordrgstrcrt1.text!!.isEmpty()){
                Toast.makeText(requireContext(), "Fill all strokes", Toast.LENGTH_LONG).show()
            }else{
                var user_email = binding.emailrgstrcrt1.text.toString()
                var user_password = binding.passwordrgstrcrt1.text.toString()
                var str = getPreferences.getString("users", "")
                if (str == ""){
                    userList = mutableListOf<User>()
                }else{
                    userList = gson.fromJson(str, type)
                }
                userList.add(User(user_email,user_password))
                var s = gson.toJson(userList)
                edit.putString("Users", s).commit()

                findNavController().navigate(R.id.action_createFragment_to_planFragment)
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
         * @return A new instance of fragment CreataccFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}