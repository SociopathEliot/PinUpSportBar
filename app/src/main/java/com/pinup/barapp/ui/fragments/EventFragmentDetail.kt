package com.pinup.barapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentEventDetailBinding
import com.pinup.barapp.data.repositories.EventRepository
import com.pinup.barapp.domain.models.Event


class EventFragmentDetail : Fragment() {

    private lateinit var binding: FragmentEventDetailBinding
    private val args by navArgs<EventFragmentDetailArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val eventId = args.eventId
        val event: Event? = EventRepository.getEventById(eventId)

        if (event == null) {
            binding.tvTitle.text = getString(R.string.app_name)
            return
        }

        binding.ivEventImage.setImageResource(event.imageRes)
        binding.tvTitle.text = event.detail?.title ?: event.title
        binding.tvSubtitle.text = event.detail?.subtitle ?: event.description

        event.detail?.let { detail ->
            binding.tvTimeTitle.text = detail.timeTitle
            binding.tvTime.text = detail.timeValue

            // Features
            if (detail.features.isNullOrEmpty()) {
                binding.tvMainBlockTitle.visibility = View.GONE
                binding.featureListContainer.visibility = View.GONE
            } else {
                binding.tvMainBlockTitle.text = detail.featuresTitle ?: ""
                binding.featureListContainer.removeAllViews()
                detail.features.forEach { text ->
                    val tv = TextView(requireContext()).apply {
                        setText("• $text")
                        setTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat_Body1)
                    }
                    binding.featureListContainer.addView(tv)
                }
            }

            // Promo list
            if (detail.promoList.isNullOrEmpty()) {
                binding.tvPromoTitle.visibility = View.GONE
                binding.promoListContainer.visibility = View.GONE
            } else {
                binding.tvPromoTitle.visibility = View.VISIBLE
                binding.tvPromoTitle.text = detail.promoTitle
                binding.promoListContainer.visibility = View.VISIBLE
                binding.promoListContainer.removeAllViews()
                detail.promoList.forEach { promo ->
                    val tv = TextView(requireContext()).apply {
                        setText("• $promo")
                        setTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat_Body1)
                    }
                    binding.promoListContainer.addView(tv)
                }
            }

            // How to section
            if (detail.howTo.isBlank()) {
                binding.howToContainer.visibility = View.GONE
            } else {
                binding.howToContainer.visibility = View.VISIBLE
                binding.tvHowToTitle.text = detail.howToTitle
                binding.tvHowTo.text = detail.howTo
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}