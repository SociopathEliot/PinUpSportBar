package com.pinup.barapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentBasketBinding
import com.pinup.barapp.ui.adapters.CartAdapter
import com.pinup.barapp.ui.viewmodels.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class BasketFragment : Fragment(R.layout.fragment_basket) {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CartAdapter(
            onIncrease = { cartViewModel.increaseQuantity(it) },
            onDecrease = { cartViewModel.decreaseQuantity(it) },
            onDelete = { cartViewModel.removeFromCart(it) }
        )
        binding.rvCart.adapter = adapter

        cartViewModel.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

        cartViewModel.totalPrice.observe(viewLifecycleOwner) { price ->
            binding.tvTotal.text = "Total: $%.2f".format(price)
        }

        binding.btnConfirm.setOnClickListener {
            val orderId = "2047"
            val action = BasketFragmentDirections.actionBasketFragmentToQRFragment(orderId)
            findNavController().navigate(action)
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_basketFragment_to_menuFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
