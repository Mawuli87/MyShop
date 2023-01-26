//package com.messieyawo.myshopapp.fragments.categories
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.widget.NestedScrollView
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.messieyawo.myshopapp.R
//import com.messieyawo.myshopapp.adapters.BestDealsAdapter
//import com.messieyawo.myshopapp.adapters.BestProductsAdapter
//import com.messieyawo.myshopapp.databinding.FragmentBaseCategoryBinding
//
//
//open class BaseCategoryFragment : Fragment() {
//
//    private lateinit var binding: FragmentBaseCategoryBinding
//    protected val offerAdapter: BestProductsAdapter by lazy { BestProductsAdapter() }
//    protected val bestProductsAdapter: BestProductsAdapter by lazy { BestProductsAdapter() }
//    //private lateinit var offerAdapter: BestProductsAdapter
//    // lateinit var bestProductsAdapter: BestProductsAdapter
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentBaseCategoryBinding.inflate(inflater,container,false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setupOfferRv()
//        setupBestProductsRv()
//
//        binding.rvOfferProducts.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                if (!recyclerView.canScrollVertically(1) && dx != 0){
//                    onOfferPagingRequest()
//                }
//            }
//        })
//
//        binding.nestedScrollBaseCategory.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener{ v, _, scrollY, _, _ ->
//            if (v.getChildAt(0).bottom <= v.height + scrollY){
//                onBestProductsPagingRequest()
//            }
//        })
//    }
//
//    open fun onOfferPagingRequest(){
//
//    }
//
//    open fun onBestProductsPagingRequest(){
//
//    }
//
//    fun showOfferLoading(){
//        binding.offerProductsProgressBar.visibility = View.VISIBLE
//    }
//
//    fun hideOfferLoading(){
//        binding.offerProductsProgressBar.visibility = View.GONE
//    }
//
//    fun showBestProductsLoading(){
//        binding.bestProductsProgressBar.visibility = View.VISIBLE
//    }
//
//    fun hideBestProductsLoading(){
//        binding.bestProductsProgressBar.visibility = View.GONE
//    }
//
//
//   private fun setupBestProductsRv() {
//      // bestProductsAdapter = BestProductsAdapter()
//        binding.rvBestProducts.apply {
//            layoutManager =
//                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
//            adapter = bestProductsAdapter
//        }
//    }
//
//    private fun setupOfferRv() {
//        //offerAdapter = BestProductsAdapter()
//        binding.rvOfferProducts.apply {
//            layoutManager =
//                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
//            adapter = offerAdapter
//        }
//    }
//}