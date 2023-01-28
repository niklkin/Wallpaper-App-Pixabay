package com.example.test_task_nwcode.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.test_task_nwcode.R
import com.example.test_task_nwcode.databinding.FragmentCategoriesBinding
import com.example.test_task_nwcode.model.Hit
import com.example.test_task_nwcode.model.PixabayResponse
import com.example.test_task_nwcode.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: List<Hit>? = null
    private var param2: String? = null

    private var mapResponse: List<Hit>? = null

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelableArray(ARG_PARAM1) as List<Hit>?
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)


        getPixabayResponse()
        binding?.apply {
            category1.text = Categories.CATS.category
            createBundleAndNavigate(category1)
            category2.text = Categories.NATURE.category
            createBundleAndNavigate(category2)
            category3.text = Categories.ANIME.category
            createBundleAndNavigate(category3)
            category4.text = Categories.CARS.category
            createBundleAndNavigate(category4)
            category5.text = Categories.ARCHITECTURE.category
            createBundleAndNavigate(category5)
            category6.text = Categories.MINIMALISM.category
            createBundleAndNavigate(category6)
        }

        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createBundleAndNavigate(view: TextView) {
        view.setOnClickListener {
            val bundle = bundleOf("category" to view.text)
            //arguments?.putString("ARG_PARAM1",Categories.CATS.category)
            findNavController().navigate(
                R.id.action_categoriesFragment_to_imageListFragment,
                bundle
            )
        }

    }


    fun getPixabayResponse() {
        //mapResponse = "123"
        RetrofitClient.api.getAllData(
            "33106230-b104905cd7ff74ed17e2229af",
            "anime",
            "photo"
        ).enqueue(object :
            Callback<PixabayResponse> {

            override fun onResponse(
                call: Call<PixabayResponse>,
                response: Response<PixabayResponse>
            ) {
                if (response.body() != null) {
                    Log.d("7456745674567", response.body()!!.hits.toString())
                    mapResponse = response.body()!!.hits
                    Log.d("response", mapResponse!!.toString())

                } else {
                    return
                }
            }

            override fun onFailure(call: Call<PixabayResponse>, t: Throwable) {
                Log.d("qwerqwy", t.message.toString())
            }
        })


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoriesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    enum class Categories(val category: String) {
        CATS("cats"),
        NATURE("nature"),
        ANIME("anime"),
        CARS("cars"),
        ARCHITECTURE("architecture"),
        MINIMALISM("minimalism")
    }
}