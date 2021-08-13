package com.teambear.goodielist

import com.teambear.goodielist.models.Recipe

object IconBuilder {
    val iconHashMap = hashMapOf<String, Int>(
        "unknown"   to R.drawable.icon_unknown,
        "multi"     to R.drawable.icon_multi,
        "fish"      to R.drawable.icon_fish,
        "meat"      to R.drawable.icon_meat,
        "salad"     to R.drawable.icon_salad,
        "soup"      to R.drawable.icon_soup,
        "soup2"     to R.drawable.icon_soup2,
        "drink"     to R.drawable.icon_drink,
        "dessert"   to R.drawable.icon_dessert,
    )

    fun getIconIdByTags(tagList: List<String>): Int{
        val relatedTags = mutableListOf<String>()
        for (tag in tagList) {
            if(iconHashMap.containsKey(tag)) relatedTags.add(tag);
        }

        when(relatedTags.size){
            0 -> return iconHashMap["unknown"]!!
            1 -> return iconHashMap[relatedTags[0]]!!
            else -> return iconHashMap["multi"]!!
        }
    }
}