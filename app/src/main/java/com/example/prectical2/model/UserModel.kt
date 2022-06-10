package com.example.prectical2.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.prectical2.db.ItemConverter
import com.google.gson.annotations.SerializedName

data class UserModel(

	@field:SerializedName("quota_max")
	val quotaMax: Int? = null,

	@field:SerializedName("backoff")
	val backoff: Int? = null,

	@field:SerializedName("quota_remaining")
	val quotaRemaining: Int? = null,

	@field:SerializedName("has_more")
	val hasMore: Boolean? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem>
)

@Entity
data class ItemsItem(

	@field:SerializedName("reputation_change_quarter")
	val reputationChangeQuarter: Int? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("last_access_date")
	val lastAccessDate: Int? = null,

	@field:SerializedName("reputation")
	val reputation: Int? = null,

	@TypeConverters(ItemConverter::class)
	@field:SerializedName("badge_counts")
	val badgeCounts: BadgeCounts? = null,

	@field:SerializedName("creation_date")
	val creationDate: Int? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("reputation_change_year")
	val reputationChangeYear: Int? = null,

	@field:SerializedName("is_employee")
	val isEmployee: Boolean? = null,

	@field:SerializedName("profile_image")
	val profileImage: String? = null,

	@PrimaryKey
	@field:SerializedName("account_id")
	val accountId: Int,

	@field:SerializedName("user_type")
	val userType: String? = null,

	@field:SerializedName("reputation_change_week")
	val reputationChangeWeek: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("reputation_change_day")
	val reputationChangeDay: Int? = null,

	@field:SerializedName("reputation_change_month")
	val reputationChangeMonth: Int? = null,

	@field:SerializedName("last_modified_date")
	val lastModifiedDate: Int? = null,

	@field:SerializedName("website_url")
	val websiteUrl: String? = null,

	@field:SerializedName("location")
	val location: String? = null
){
	private fun setPrefixToInt(number : Int?): String? {
		return if(number != null){
			val floatValue = number.toFloat()
			when {
				number >= 1000000000 -> {
					String.format("%.02fB", (floatValue/1000000000))
				}
				number >= 1000000 -> {
					String.format("%.02fM", (floatValue/1000000))
				}
				number >= 1000 -> {
					String.format("%.02fK", (floatValue/1000))
				}
				else -> {
					number.toString()
				}
			}
		}else{
			null
		}
	}

	fun getPrefixReputation():String?{
		return setPrefixToInt(reputation)
	}

	fun getPrefixBadgeGold():String?{
		return setPrefixToInt(badgeCounts?.gold)
	}

	fun getPrefixBadgeSilver():String?{
		return setPrefixToInt(badgeCounts?.silver)
	}

	fun getPrefixBadgeBronze():String?{
		return setPrefixToInt(badgeCounts?.bronze)
	}

}

data class BadgeCounts(

	@field:SerializedName("gold")
	val gold: Int? = null,

	@field:SerializedName("silver")
	val silver: Int? = null,

	@field:SerializedName("bronze")
	val bronze: Int? = null
)
