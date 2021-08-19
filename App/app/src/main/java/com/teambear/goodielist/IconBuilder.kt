package com.teambear.goodielist

object IconBuilder {
    private const val unknownIcon = R.drawable.icon_unknown
    private const val multipleIcon = R.drawable.icon_multi

    val iconHashMap = hashMapOf(
        "breakfast"     to R.drawable.icon_breakfest,
        "dessert"       to R.drawable.icon_dessert,
        "drink"         to R.drawable.icon_drink,
        "fish"          to R.drawable.icon_fish,
        "lunch"         to R.drawable.icon_lunch,
        "dinner"        to R.drawable.icon_lunch,
        "meat"          to R.drawable.icon_meat,
        "salad"         to R.drawable.icon_salad,
        "vegetables"    to R.drawable.icon_salad,
        "soup"          to R.drawable.icon_soup,
        "supper"        to R.drawable.icon_supper
    )

    fun getIconId(tagList: List<String>): Int{
        val tagIcons = mutableListOf<Int>()
        for (tag in tagList) {
            if(iconHashMap.containsKey(tag.lowercase())) {
                tagIcons.add(iconHashMap[tag.lowercase()]!!)
            }
        }

        return when(tagIcons.size){
            0       -> unknownIcon
            1       -> tagIcons.first()
            else    -> multipleIcon
        }
    }

    fun getIconId(tag: String): Int{
        if(iconHashMap.containsKey(tag.lowercase()))
            return iconHashMap[tag.lowercase()]!!
        return unknownIcon
    }
}