package team.noweekend.core.common.android.extension

import android.os.Bundle
import kotlin.enums.enumEntries

inline fun <reified T : Enum<T>> Bundle.getEnum(key: String, default: T): T {
    return getInt(key).let {
        if (it >= 0) {
            enumEntries<T>()[it]
        } else {
            default
        }
    }
}

fun <T : Enum<T>> Bundle.putEnum(key: String, value: T?) {
    putInt(key, value?.ordinal ?: -1)
}
