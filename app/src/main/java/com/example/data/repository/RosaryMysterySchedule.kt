package com.example.data.repository

import com.example.data.models.RosaryMysteryType
import java.util.Calendar

/**
 * Which set of mysteries the Church suggests for a weekday. Pure, so the
 * rule can be tested without a repository or a clock.
 *
 * Monday and Saturday are Joyful, Tuesday and Friday Sorrowful, Wednesday
 * and Sunday Glorious, Thursday Luminous.
 */
object RosaryMysterySchedule {
    /** [dayOfWeek] is a [Calendar] constant (`Calendar.MONDAY` ...). */
    fun typeFor(dayOfWeek: Int): RosaryMysteryType = when (dayOfWeek) {
        Calendar.MONDAY, Calendar.SATURDAY -> RosaryMysteryType.JOYFUL
        Calendar.TUESDAY, Calendar.FRIDAY -> RosaryMysteryType.SORROWFUL
        Calendar.WEDNESDAY, Calendar.SUNDAY -> RosaryMysteryType.GLORIOUS
        Calendar.THURSDAY -> RosaryMysteryType.LUMINOUS
        else -> RosaryMysteryType.JOYFUL
    }

    fun today(): RosaryMysteryType = typeFor(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
}
