package dev.johnnylewis.weighttrackr.presentation.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun LocalDateTime.toFormattedString(): String =
  LocalDateTime.Format {
    day(); char(' '); monthName(MonthNames.ENGLISH_ABBREVIATED)
    char(' '); year(); char(' ')
    amPmHour(); char(':'); minute(); char(' '); amPmMarker("AM", "PM")
  }.let {
    format(it)
  }

@OptIn(ExperimentalTime::class)
fun LocalDateTime.Companion.now(): LocalDateTime =
  Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
