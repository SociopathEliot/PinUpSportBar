package com.pinup.barapp.data.repositories

import com.pinup.barapp.R
import com.pinup.barapp.domain.models.Event
import com.pinup.barapp.domain.models.EventDetail

object EventRepository {
    val events = listOf(
        Event(
            id = 1,
            title = "Happy Hour!",
            description = "50% off cocktails from 6:00 PM to 8:00 PM. Enjoy the best mixes of the evening!",
            imageRes = R.drawable.event_happy_hour,
            detail = EventDetail(
                title = "Happy Hour – 50% Off Cocktails!",
                subtitle = "Every day from 6:00 PM to 8:00 PM",
                mainInfo = "50% off the entire cocktail menu. The perfect reason to unwind after work. Pin-up atmosphere: upbeat music, stylish interior, and cool bartenders.",
                timeTitle = "Event Time:",
                timeValue = "Every day from 6:00 PM to 8:00 PM",
                featuresTitle = "What to Expect?",
                features = listOf(
                    "50% off the entire cocktail menu.",
                    "The perfect way to relax after work.",
                    "Pin-up vibe: energetic music, stylish interior, and awesome bartenders."
                ),
                promoTitle = "Top Cocktails at Special Prices:",
                promoList = listOf(
                    "Pina Colada – €4.99 instead of €9.99",
                    "Mojito – €3.99 instead of €7.99",
                    "Margarita – €4.49 instead of €8.99",
                    "Cosmopolitan – €4.99 instead of €9.99"
                ),
                extraTitle = null,
                extraList = null,
                howToTitle = "How to Join?",
                howTo = "Just come to the bar at the specified time and order promotional cocktails. Bring your friends and start your night off right!"
            )
        ),
        Event(
            id = 2,
            title = "Burger & Pint",
            description = "Juicy burger + a pint of beer for only €9.99! Valid every Tuesday.",
            imageRes = R.drawable.event_burger_pint,
            detail = EventDetail(
                title = "Burger & Pint – The Perfect Combo!",
                subtitle = "Every Tuesday, all day",
                mainInfo = "Juicy beef burger with fresh veggies and signature sauce. A cold pint of your choice – all for just €9.99 instead of €14.99.",
                timeTitle = "Event Time:",
                timeValue = "Every Tuesday, all day",
                featuresTitle = "What's Included?",
                features = listOf(
                    "Juicy beef burger with fresh veggies and signature sauce.",
                    "A cold pint (500 ml) of your choice: light, dark, or craft beer.",
                    "All for just €9.99 instead of €14.99!"
                ),
                promoTitle = null,
                promoList = null,
                extraTitle = "Add-ons:",
                extraList = listOf(
                    "Add fries for just +€1.99.",
                    "Choose a cheese topping for +€0.99."
                ),
                howToTitle = "How to Join?",
                howTo = "Just come in on Tuesday and ask the waiter for the 'Burger & Pint' deal! Tasty Tuesday is waiting – don't miss it!"
            )
        ),
        Event(
            id = 3,
            title = "Match of the Day: Bet on Victory!",
            description = "Watch the top match and get a free shot for every goal your favorite team scores!",
            imageRes = R.drawable.event_match_day,
            detail = EventDetail(
                title = "Match of the Day: Bet on Victory!",
                subtitle = "Cheer and earn shots",
                mainInfo = "Get a free shot for every goal your favorite team scores.",
                timeTitle = "Event Time:",
                timeValue = "During top football matches (check the app for schedule)",
                featuresTitle = "What’s the Deal?",
                features = listOf(
                    "Come to Pin-up Sports Bar to watch the best match of the day.",
                    "Pick your team and cheer your heart out!",
                    "Get a free shot for every goal your favorite team scores."
                ),
                promoTitle = "Bonuses:",
                promoList = listOf(
                    "Team specials – order a snack set and get discounts on beer!",
                    "Football vibe – big screens, live emotions, awesome company.",
                    "Fan prizes – guess the exact score and win a signature cocktail!"
                ),
                extraTitle = null,
                extraList = null,
                howToTitle = "How to Join?",
                howTo = "Come to the bar before the match starts. Tell the waiter which team you're rooting for. Enjoy the game and collect free shots for each goal your team scores! Football is passion – and with Pin-up shots, it's also fun!"
            )
        ),
        Event(
            id = 4,
            title = "Karaoke Night",
            description = "Every Friday at 10:00 PM – sing and get drink discounts! Best singer wins a prize!",
            imageRes = R.drawable.event_karaoke_night,
            detail = EventDetail(
                title = "Karaoke Night at Pin-up!",
                subtitle = "Every Friday from 10:00 PM",
                mainInfo = "Open mic and 20% off cocktails for all participants.",
                timeTitle = "Event Time:",
                timeValue = "Every Friday from 10:00 PM",
                featuresTitle = "What to Expect?",
                features = listOf(
                    "Open mic for anyone who wants to join!",
                    "20% off cocktails for all participants.",
                    "Prize for the best singer – a signature cocktail from the bartender!"
                ),
                promoTitle = null,
                promoList = null,
                extraTitle = "How to Be the Star of the Night?",
                extraList = listOf(
                    "Choose your favorite song.",
                    "Win over the crowd with your voice and charisma.",
                    "Get free shots for the hottest performances!"
                ),
                howToTitle = "How to Join?",
                howTo = "Just come by, register with the host, and sing your heart out! Friday night is your time to shine! We’re waiting for you at Pin-up!"
            )
        ),
        Event(
            id = 5,
            title = "Whiskey & Poker Night",
            description = "Play a friendly poker game and get 20% off all whiskey.",
            imageRes = R.drawable.event_whiskey_poker,
            detail = EventDetail(
                title = "Whiskey & Poker Night at Pin-up!",
                subtitle = "Every Wednesday from 8:00 PM",
                mainInfo = "Poker night with 20% off whiskey and special sets.",
                timeTitle = "Event Time:",
                timeValue = "Every Wednesday from 8:00 PM",
                featuresTitle = "What to Expect?",
                features = listOf(
                    "Atmospheric poker night among like-minded people.",
                    "20% off all whiskey for participants.",
                    "Special poker sets with snacks and drinks.",
                    "Nice bonuses for lucky combinations!"
                ),
                promoTitle = null,
                promoList = null,
                extraTitle = "Bonus Game:",
                extraList = listOf(
                    "Flush – Free shot of whiskey",
                    "Full House – 30% off a cocktail",
                    "Straight Flush – Signature cocktail from the bartender!"
                ),
                howToTitle = "How to Join?",
                howTo = "Come to the bar on Wednesday and register with the host. Enjoy friendly poker, whiskey, and great vibes. Catch your luck and earn exclusive bonuses! Spend your evening Pin-up style – class, thrill, and top whiskey!"
            )
        ),
        Event(
            id = 6,
            title = "Ladies' Night at Pin-up!",
            description = "A complimentary cocktail for every girl with orders over €20.",
            imageRes = R.drawable.event_hen_party,
            detail = EventDetail(
                title = "Ladies' Night at Pin-up!",
                subtitle = "Every Saturday from 8:00 PM",
                mainInfo = "Free cocktail for every girl on orders over €20. Lively atmosphere and special sets for girls' nights.",
                timeTitle = "Event Time:",
                timeValue = "Every Saturday from 8:00 PM",
                featuresTitle = "What to Expect?",
                features = listOf(
                    "Free cocktail for every girl with orders over €20.",
                    "Lively Pin-up atmosphere – stylish decor, music, and themed photo zones.",
                    "Special snack and cocktail sets for fun hen parties.",
                    "DJ sets and themed parties."
                ),
                promoTitle = null,
                promoList = null,
                extraTitle = "Compliment Options:",
                extraList = listOf(
                    "\"Pink Lady\" – gentle flavor with vanilla and pomegranate notes.",
                    "\"Margarita Passion\" – classic with a fruity twist.",
                    "\"Sex on the Beach\" – iconic cocktail for a bright night."
                ),
                howToTitle = "How to Join?",
                howTo = "Gather your girlfriends and come Saturday night. Order your favorite dishes and drinks. Get a free cocktail and enjoy the party! Vibe – flirt, style – Pin-up, drinks – perfect!"
            )
        ),
        Event(
            id = 7,
            title = "Birthday at Pin-up!",
            description = "Birthday guests get a free shot set! Just show your ID and party hard!",
            imageRes = R.drawable.event_birthday,
            detail = EventDetail(
                title = "Birthday at Pin-up!",
                subtitle = "Shot set & discounts for your group",
                mainInfo = "Celebrate big – get a free shot set and a personalized cocktail!",
                timeTitle = "Event Time:",
                timeValue = "On your birthday + 3 days after",
                featuresTitle = "Our Birthday Treats:",
                features = listOf(
                    "Free shot set for the birthday person!",
                    "10% discount for the entire party group.",
                    "Option to order a custom cocktail with the birthday person's name.",
                    "Cheerful congratulations from the bar team and festive vibe!"
                ),
                promoTitle = null,
                promoList = null,
                extraTitle = "Bonus:",
                extraList = listOf(
                    "Bring 6+ friends and get an extra free cocktail!"
                ),
                howToTitle = "How to Join?",
                howTo = "Come on your birthday or within 3 days after. Show your ID to the waiter. Get your free shot set and celebrate in style! Your party – our treats! See you at Pin-up!"
            )
        )
    )

    fun getEventById(id: Int): Event? = events.find { it.id == id }
}
