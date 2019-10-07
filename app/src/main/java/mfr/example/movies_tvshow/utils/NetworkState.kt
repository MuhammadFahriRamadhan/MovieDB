package mfr.example.movies_tvshow.utils

data class NetworkState(val status: Status) {

    companion object {
        val LOADED = NetworkState(Status.LOADED)
        val LOADING = NetworkState(Status.LOADING)
        val ERROR = NetworkState(Status.ERROR)
        val CONNECTED = NetworkState(Status.CONNECTED)
        val DISCONNECTED = NetworkState(Status.DISCONNECTED)
    }
}

enum class Status {
    LOADING,
    LOADED,
    ERROR,
    CONNECTED,
    DISCONNECTED
}