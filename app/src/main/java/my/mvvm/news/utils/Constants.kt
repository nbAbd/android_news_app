package my.mvvm.news.utils

class Constants {
    companion object {
        const val BASE_URL: String =
            "https://newsapi.org/v2/"

        /***
         * v2/top-headlines - returns breaking news headlines for a country and category,
         * or currently running on a single or multiple sources.
         * This is perfect for use with news tickers or anywhere you
         * want to display live up-to-date news headlines and images.
         */

        // Request params
        const val country: String = "ru"
        const val category: String = "technology"
        const val apiKey: String = "4a1d972d5666411896eabf3f378def5e"


        //cash size 5 MB
        const val cashSize = (5 * 1024 * 1024).toLong()

        // starting page index
        const val STARTING_PAGE_INDEX = 1

        //page size to download
        const val NETWORK_PAGE_SIZE = 10


    }

}