package dev.johnnylewis.weighttrackr.data.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import java.time.Instant
import kotlin.time.ExperimentalTime
import kotlin.time.toKotlinInstant

@OptIn(ExperimentalTime::class)
fun LocalDateTime.toInstant(): Instant =
  Instant.ofEpochSecond(
    toInstant(TimeZone.currentSystemDefault()).epochSeconds
  )

@OptIn(ExperimentalTime::class)
fun Instant.toKotlinLocalDateTime(): LocalDateTime =
  toKotlinInstant().toLocalDateTime(TimeZone.currentSystemDefault())
