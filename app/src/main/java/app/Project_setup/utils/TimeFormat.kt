package app.Project_setup.utils

import android.util.Log


class TimeFormat {
    companion object {
        fun getCommentTimeGap(created: Long): String {
            val timeGap = System.currentTimeMillis() - created
            //Minutes
            var timeGapInMin = timeGap / (1000 * 60)
            var accurate = timeGapInMin - 570
            if (accurate < 60) {
                return "${accurate}m"
            }
            //Hour
            var timeGapInHr = accurate / 60
            if (timeGapInHr < 24) {
                return "${timeGapInHr}hr"
            }
            //Days
            var timeGapInDays = timeGapInHr / 24
            if (timeGapInDays < 7) {
                return "${timeGapInDays}d"
            }
            //Weeks
            var timeGapInWeeks = timeGapInDays / 7
            if (timeGapInWeeks < 7) {
                return "${timeGapInWeeks}w"
            }
            //Months
            var timeGapInMonths = timeGapInWeeks / 4
            if (timeGapInMonths < 12) {
                return "${timeGapInMonths}M"
            }
            var timeGapInYears = timeGapInMonths / 12
            return "${timeGapInYears}y"
            //Years
            return " "
        }

        fun getTimeDay(created:Long):String{
            var timeGap = System.currentTimeMillis() - created
            //Minutes
            var timeGapInMin = timeGap / (1000 * 60)
            //Hour
            var timeGapInHr = timeGapInMin / 60
            //Days
            var timeGapInDays = timeGapInHr / 24

            return timeGapInDays.toString()
        }

        fun getTimeGap(created: Long): String {
            Log.d("CURRENTTIME",System.currentTimeMillis().toString())
            Log.d("CREATEDTIME",created.toString())
            var timeGap = System.currentTimeMillis() - created
            //Minutes
            var timeGapInMin = timeGap / (1000 * 60)
            // var accurate = timeGapInMin - 570
            if (timeGapInMin < 60) {
                return if (timeGapInMin.toInt() == 1) {
                    "a minute ago"
                }
                else {
                    "$timeGapInMin minutes ago"
                }
            }
            //Hour
            var timeGapInHr = timeGapInMin / 60
            if (timeGapInHr < 24) {
                return if (timeGapInHr.toInt() == 1) {
                    "an hour ago"
                } else {
                    "$timeGapInHr hours ago"
                }
            }
            //Days
            var timeGapInDays = timeGapInHr / 24
            if (timeGapInDays < 7) {
                return if (timeGapInDays.toInt() == 1) {
                    "a day ago"
                } else {
                    "$timeGapInDays days ago"
                }
            }
            //Weeks
            var timeGapInWeeks = timeGapInDays / 7
            if (timeGapInWeeks < 7) {
                return if (timeGapInWeeks.toInt() == 1) {
                    "a week ago"
                } else {
                    "$timeGapInWeeks weeks ago"
                }
            }
            //Months
            var timeGapInMonths = timeGapInWeeks / 4
            if (timeGapInMonths < 12) {
                return if (timeGapInMonths.toInt() == 1) {
                    "a month ago"
                } else {
                    "$timeGapInMonths months ago"
                }
            }
            var timeGapInYears = timeGapInMonths / 12
            return if (timeGapInYears.toInt() == 1) {
                "a year ago"
            } else {
                "$timeGapInYears years ago"
            }
            //Years
            return " "
        }
    }
}