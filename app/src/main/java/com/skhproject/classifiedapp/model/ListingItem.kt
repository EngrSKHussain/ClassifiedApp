data class ListingItem(

    val created_at: String,
    val price: String,
    val name: String,
    val uid: String,
    val image_ids: List<String>,
    val image_urls: List<String>,
    val image_urls_thumbnails: List<String>

) {
    fun validateListingItem(): Boolean {

        /*INFO:
        * This method will validate if the listItem is valid or not
        * */

        if (image_urls.size == 0) {
            return false
        }

        if (image_urls_thumbnails.size == 0) {
            return false
        }

        if (image_ids.size == 0) {
            return false
        }

        if (name.isEmpty()) {
            return false
        }

        return true
    }
}