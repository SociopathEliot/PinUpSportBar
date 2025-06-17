package com.pinup.barapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentMenuBinding
import com.pinup.barapp.domain.models.MenuItem
import com.pinup.barapp.ui.adapters.MenuAdapter

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var adapter: MenuAdapter

    private val tags = listOf("Drinks", "Starters", "Mains", "Desserts")
    private var selected = tags.first()

    private val drinksList = listOf(
        MenuItem(1, "Pin-up Sunset", "Vodka, peach liqueur, cranberry juice...", 8.99, R.drawable.cocktails),
        MenuItem(2, "Classic Mojito", "White rum, lime juice, soda...", 9.49, R.drawable.draft_beer),
        MenuItem(3, "Draft Lager", "House-brewed golden lager...", 6.99, R.drawable.draft_beer),
        MenuItem(4, "Virgin Berry Fizz", "Berry juice blend with lime and mint", 5.49, R.drawable.cocktails)
    )

    private val startersList = listOf(
        MenuItem(5, "Mozzarella Sticks", "Golden-fried mozzarella with sauce", 7.99, R.drawable.cocktails),
        MenuItem(6, "Loaded Nachos", "Chips topped with cheddar & salsa", 9.49, R.drawable.draft_beer),
        MenuItem(7, "Chicken Bites", "Boneless bites with BBQ or buffalo", 8.99, R.drawable.cocktails),
        MenuItem(8, "Retro Fries Basket", "Fries with cheese dip or spicy ketchup", 5.99, R.drawable.draft_beer)
    )

    private val mainsList = listOf(
        MenuItem(9, "Double Burger", "Beef patties, lettuce, sauce", 13.99, R.drawable.draft_beer),
        MenuItem(10, "BBQ Chicken Wrap", "Grilled chicken, sauce, tortilla", 11.49, R.drawable.cocktails),
        MenuItem(11, "Steak & Fries", "Sirloin with fries and garlic butter", 16.99, R.drawable.cocktails),
        MenuItem(12, "Mac ‘n’ Cheese", "Cheddar, mozzarella, crunchy top", 10.99, R.drawable.draft_beer)
    )

    private val dessertsList = listOf(
        MenuItem(13, "Vanilla Cupcake", "Sponge cake with frosting", 4.99, R.drawable.cocktails),
        MenuItem(14, "Choco Lava Cake", "Chocolate cake with molten center", 6.49, R.drawable.draft_beer),
        MenuItem(15, "Retro Sundae", "Ice cream with caramel drizzle", 5.99, R.drawable.cocktails),
        MenuItem(16, "Classic Apple Pie", "Apple pie with ice cream", 5.49, R.drawable.draft_beer)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = MenuAdapter { item ->
            // item click shit
        }
        binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMenu.adapter = adapter

        setupChips()
        filterAndSubmit()
    }

    private fun setupChips() {
        fun refreshChips() {
            binding.chipSelected.text = selected
            binding.chipGroupUnselected.removeAllViews()

            tags.filter { it != selected }.forEach { tag ->
                val chip = Chip(requireContext(), null, R.style.Widget_App_MenuChipDefault).apply {
                    text = tag
                    setOnClickListener {
                        selected = tag
                        refreshChips()
                        filterAndSubmit()
                    }
                }
                binding.chipGroupUnselected.addView(chip)
            }
        }
        refreshChips()
    }

    private fun filterAndSubmit() {
        val list = when (selected) {
            "Drinks" -> drinksList
            "Starters" -> startersList
            "Mains" -> mainsList
            "Desserts" -> dessertsList
            else -> emptyList()
        }
        adapter.submitList(list)
    }
}
