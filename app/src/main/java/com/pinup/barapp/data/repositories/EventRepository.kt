package com.pinup.barapp.data.repositories

import com.pinup.barapp.R
import com.pinup.barapp.domain.models.Event
import com.pinup.barapp.domain.models.EventDetail

object EventRepository {
    val events = listOf(
        Event(
            id = 1,
            title = "Happy Hour!",
            description = "50% discount on cocktails from 18:00 to 20:00. Hurry up to enjoy the best mixes of the evening!",
            imageRes = R.drawable.event_happy_hour,
            detail = EventDetail(
                title = "Happy Hour!",
                subtitle = "50% discount on cocktails from 18:00 to 20:00.",
                mainInfo = "Great discounts on all cocktails during happy hours.",
                timeTitle = "Time:",
                timeValue = "Every day from 18:00 to 20:00",
                featuresTitle = "What awaits you?",
                features = listOf(
                    "50% discount on the cocktail menu",
                    "DJ sets on weekends"
                ),
                promoTitle = "Top cocktails at a special price:",
                promoList = listOf(
                    "Mojito – €5",
                    "Sex on the Beach – €5"
                ),
                extraTitle = null,
                extraList = null,
                howToTitle = "How to use it?",
                howTo = "Just come to the bar during Happy Hour and order drinks"
            )
        ),
        Event(
            id = 2,
            title = "Burger & Pint",
            description = "Juicy burger + glass of beer for only €9.99! Every Tuesday.",
            imageRes = R.drawable.event_burger_pint,
            detail = EventDetail(
                title = "Burger & Pint",
                subtitle = "Juicy burger + glass of beer for only €9.99!",
                mainInfo = "Special offer for burger lovers.",
                timeTitle = "Time:",
                timeValue = "Every Tuesday 12:00 – 23:00",
                featuresTitle = "What awaits you?",
                features = listOf(
                    "Any beer to choose",
                    "Add fries for just €2"
                ),
                promoTitle = null,
                promoList = null,
                extraTitle = null,
                extraList = null,
                howToTitle = "How to get it?",
                howTo = "Order the Burger & Pint set at the bar"
            )
        ),
        Event(
            id = 3,
            title = "Match of the day: Bet on victory!",
            description = "Come watch the top match and get a free shot for every goal of your favorite team!",
            imageRes = R.drawable.event_match_day,
            detail = EventDetail(
                title = "Match of the day",
                subtitle = "Bet on victory and cheer loudly!",
                mainInfo = "Free shot for every goal of your favorite team.",
                timeTitle = "When:",
                timeValue = "On main match days",
                featuresTitle = null,
                features = null,
                promoTitle = null,
                promoList = null,
                extraTitle = null,
                extraList = null,
                howToTitle = "How it works?",
                howTo = "Support your team and enjoy a shot on every goal"
            )
        ),
        Event(
            id = 4,
            title = "Karaoke Night",
            description = "Every Friday from 22:00 – get discounts on drinks! The best singer of the evening gets a prize!",
            imageRes = R.drawable.event_karaoke_night,
            detail = EventDetail(
                title = "Karaoke Night",
                subtitle = "Sing your heart out every Friday!",
                mainInfo = "Discounts on drinks and prizes for the best singers.",
                timeTitle = "Time:",
                timeValue = "Fridays from 22:00",
                featuresTitle = "What awaits you?",
                features = listOf(
                    "Prize for the best singer",
                    "Discounts on cocktails"
                ),
                promoTitle = null,
                promoList = null,
                extraTitle = null,
                extraList = null,
                howToTitle = "How to join?",
                howTo = "Reserve a table or just come and pick your song"
            )
        ),
        Event(
            id = 5,
            title = "Whiskey and Poker Night",
            description = "Play American poker and get a 20% discount on the entire range of whiskey.",
            imageRes = R.drawable.event_whiskey_poker,
            detail = EventDetail(
                title = "Whiskey and Poker Night",
                subtitle = "Poker tournament with whiskey discounts",
                mainInfo = "Compete in poker and enjoy 20% off whiskey.",
                timeTitle = "Time:",
                timeValue = "Thursdays from 20:00",
                featuresTitle = "Why join?",
                features = listOf(
                    "Poker tournament",
                    "20% off all whiskey"
                ),
                promoTitle = null,
                promoList = null,
                extraTitle = null,
                extraList = null,
                howToTitle = "How to participate?",
                howTo = "Sign up for the tournament at the bar"
            )
        ),
        Event(
            id = 6,
            title = "Hen Party at Pin-up!",
            description = "A compliment to every girl – a free cocktail for a table of 20€ or more.",
            imageRes = R.drawable.event_hen_party,
            detail = EventDetail(
                title = "Hen Party at Pin-up!",
                subtitle = "Free cocktail for orders over 20€",
                mainInfo = "Gather your girlfriends and have fun!",
                timeTitle = "Time:",
                timeValue = "Saturdays after 18:00",
                featuresTitle = null,
                features = null,
                promoTitle = null,
                promoList = null,
                extraTitle = null,
                extraList = null,
                howToTitle = "How to get compliment?",
                howTo = "Book a table and order drinks for 20€ or more"
            )
        ),
        Event(
            id = 7,
            title = "Birthday at Pin-up!",
            description = "Birthday boys and girls get a set of shots as a gift! Just open your passport and celebrate loudly!",
            imageRes = R.drawable.event_birthday,
            detail = EventDetail(
                title = "Birthday at Pin-up!",
                subtitle = "Shots set for the birthday person",
                mainInfo = "Celebrate your birthday in a big way!",
                timeTitle = "Any day:",
                timeValue = "Celebrate whenever you like",
                featuresTitle = null,
                features = null,
                promoTitle = null,
                promoList = null,
                extraTitle = null,
                extraList = null,
                howToTitle = "How to get gift?",
                howTo = "Show your ID to the bartender"
            )
        )
    )

    fun getEventById(id: Int): Event? = events.find { it.id == id }
}
