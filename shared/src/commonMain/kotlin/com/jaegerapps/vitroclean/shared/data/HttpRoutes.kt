package com.jaegerapps.vitroclean.shared.data


//Holds the routes for the app.
//I did it this way because I like having one place to see all of the routes, where I can change them easily if needed
object HttpRoutes {
    private const val BASE_URL = "https://jkeplvvhjynrgvqdltuk.supabase.co/rest/v1"
    const val FAQS = "$BASE_URL/FAQS"
    const val VF_FILTER_CHART = "$BASE_URL/vf_filter_chart"
}