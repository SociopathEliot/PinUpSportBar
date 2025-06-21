    package com.pinup.barapp.ui.fragments

    import android.content.res.ColorStateList
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.LinearLayout
    import androidx.annotation.OptIn
    import androidx.core.content.ContextCompat
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.viewModels
    import androidx.navigation.fragment.findNavController
    import androidx.recyclerview.widget.GridLayoutManager
    import com.google.android.material.badge.BadgeDrawable
    import com.google.android.material.badge.BadgeUtils
    import com.google.android.material.badge.ExperimentalBadgeUtils
    import com.google.android.material.chip.Chip
    import com.pinup.barapp.R
    import com.pinup.barapp.databinding.FragmentMenuBinding
    import com.pinup.barapp.domain.models.MenuItem
    import com.pinup.barapp.domain.models.CartItem
    import com.pinup.barapp.ui.adapters.MenuAdapter
    import com.pinup.barapp.ui.viewmodels.CartViewModel
    import dagger.hilt.android.AndroidEntryPoint

    @AndroidEntryPoint
    class MenuFragment : Fragment(R.layout.fragment_menu) {

        private var _binding: FragmentMenuBinding? = null
        private val binding get() = _binding!!

        private lateinit var adapter: MenuAdapter
        private val cartViewModel: CartViewModel by viewModels()

        private val tags = listOf("Drinks", "Starters", "Mains", "Desserts")
        private var selected = tags.first()
        private lateinit var chipViews: List<Chip>
        private lateinit var cartBadge: BadgeDrawable

        private val drinksList = listOf(
            MenuItem(1, "Pin-up Sunset", "Vodka, peach liqueur, cranberry juice, and a splash of soda served over ice.", 8.99, R.drawable.sunset_im),
            MenuItem(2, "Classic Mojito", "White rum, fresh mint, lime juice, sugar, and soda water. Cool and refreshing.", 9.49, R.drawable.mojito_im),
            MenuItem(3, "Draft Lager", "House-brewed golden lager with a crisp finish and creamy head.", 6.99, R.drawable.lager_im),
            MenuItem(4, "Virgin Berry Fizz", "Sparkling berry juice blend with lime and mint. Perfect mocktail option.", 5.49, R.drawable.virgin_berry_fizz_im)
        )

        private val startersList = listOf(
            MenuItem(5, "Mozzarella Sticks", "Golden-fried mozzarella with sauce", 7.99, R.drawable.mozzarella_sticks_im),
            MenuItem(6, "Loaded Nachos", "Chips topped with cheddar & salsa", 9.49, R.drawable.nachos_im),
            MenuItem(7, "Chicken Bites", "Boneless bites with BBQ or buffalo", 8.99, R.drawable.chicken_bites_im),
            MenuItem(8, "Retro Fries Basket", "Fries with cheese dip or spicy ketchup", 5.99, R.drawable.retro_fries_basket_img)
        )

        private val mainsList = listOf(
            MenuItem(9, "Double Burger", "Beef patties, lettuce, sauce", 13.99, R.drawable.double_burger_im),
            MenuItem(10, "BBQ Chicken Wrap", "Grilled chicken, sauce, tortilla", 11.49, R.drawable.chicken_wrap_im),
            MenuItem(11, "Steak & Fries", "Sirloin with fries and garlic butter", 16.99, R.drawable.steak_fries_img),
            MenuItem(12, "Mac ‘n’ Cheese", "Cheddar, mozzarella, crunchy top", 10.99, R.drawable.mac_cheese_im)
        )

        private val dessertsList = listOf(
            MenuItem(13, "Vanilla Cupcake", "Sponge cake with frosting", 4.99, R.drawable.vanilla_cupcake_im),
            MenuItem(14, "Choco Lava Cake", "Chocolate cake with molten center", 6.49, R.drawable.choko_laka_wake_im),
            MenuItem(15, "Retro Sundae", "Ice cream with caramel drizzle", 5.99, R.drawable.retro_sundae),
            MenuItem(16, "Classic Apple Pie", "Apple pie with ice cream", 5.49, R.drawable.classic_apple_pie_im)
        )

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View {
            _binding = FragmentMenuBinding.inflate(inflater, container, false)
            return binding.root
        }


        @OptIn(ExperimentalBadgeUtils::class)
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            adapter = MenuAdapter { item ->
                val cartItem = CartItem(
                    id = item.id,
                    name = item.name,
                    price = item.price,
                    imageRes = item.imageRes,
                    quantity = 1
                )
                cartViewModel.addToCart(cartItem)
            }

            binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvMenu.adapter = adapter

            binding.ivCart.setOnClickListener {
                findNavController().navigate(R.id.basketFragment)
            }

            cartBadge = BadgeDrawable.create(requireContext()).apply {
                badgeGravity    = BadgeDrawable.TOP_END

                backgroundColor = ContextCompat.getColor(requireContext(), R.color.chip_stroke_default)
                badgeTextColor  = ContextCompat.getColor(requireContext(), android.R.color.white)

                badgeGravity    = BadgeDrawable.TOP_END

                isVisible = false
            }
            BadgeUtils.attachBadgeDrawable(
                cartBadge,
                binding.ivCart,
                binding.cartContainer
            )
            cartViewModel.totalQuantity.observe(viewLifecycleOwner) { count ->
                if (count > 0) {
                    cartBadge.number    = count
                    cartBadge.isVisible = true
                } else {
                    cartBadge.isVisible = false
                }
            }

            setupChips()
            filterAndSubmit()
            observeCartCount()
        }

        private fun setupChips() {
            // Собираем все чипы в список
            chipViews = listOf(
                binding.chipDrinks,
                binding.chipStarters,
                binding.chipMains,
                binding.chipDesserts
            )

            // Слушаем клик на каждый
            chipViews.forEach { chip ->
                chip.setOnClickListener {
                    selected = chip.text.toString()
                    // сначала обновим порядок вью и стили
                    reorderChips(chip)
                    updateChipStyles()
                    filterAndSubmit()
                }
            }

            reorderChips(chipViews.first())
            updateChipStyles()
        }

        private fun reorderChips(selectedChip: Chip) {
            val parent = binding.chipContainer
            val divider = binding.chipDivider

            parent.removeAllViews()
            parent.addView(selectedChip)
            parent.addView(divider)

            chipViews
                .filter { it != selectedChip }
                .forEach { parent.addView(it) }
        }

        private fun updateChipStyles() {
            chipViews.forEach { chip ->
                if (chip.text.toString() == selected) {
                    applySelectedStyle(chip)
                } else {
                    applyUnselectedStyle(chip)
                }
            }
        }


        private fun applySelectedStyle(chip: Chip) {
            chip.chipBackgroundColor = ColorStateList.valueOf(
                ContextCompat.getColor(requireContext(), R.color.chip_bg_selected)
            )
            chip.chipStrokeWidth = 0f
            chip.chipStrokeColor = ColorStateList.valueOf(
                ContextCompat.getColor(requireContext(), R.color.chip_stroke_selected)
            )
            chip.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.chip_text_selected)
            )
        }

        private fun applyUnselectedStyle(chip: Chip) {
            chip.chipBackgroundColor = ColorStateList.valueOf(android.graphics.Color.TRANSPARENT)
            chip.chipStrokeWidth = resources.displayMetrics.density * 2
            chip.chipStrokeColor = ColorStateList.valueOf(
                ContextCompat.getColor(requireContext(), R.color.chip_unselected_stroke)
            )
            chip.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.chip_unselected_text)
            )
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

        private fun observeCartCount() {
            cartViewModel.totalQuantity.observe(viewLifecycleOwner) { count ->
//                binding.tvCartBadge.text = count.toString()
//                binding.tvCartBadge.visibility = if (count > 0) View.VISIBLE else View.GONE
            }

        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
