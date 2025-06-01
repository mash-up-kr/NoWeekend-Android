package team.noweekend.core.common.android.extension

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

inline fun <reified T : Any?> serializeToStringNavType(isNullableAllowed: Boolean = false) =
    object : NavType<T>(isNullableAllowed = isNullableAllowed) {
        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putString(key, Json.encodeToString(serializer<T>(), value))
        }

        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getString(key)?.let { Json.decodeFromString(it) }
        }

        override fun serializeAsValue(value: T): String {
            return Json.encodeToString(serializer<T>(), value)
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString(value)
        }
    }

inline fun <reified T : Any?> serializeToStringArrayNavType(isNullableAllowed: Boolean = false) =
    object : NavType<List<T>>(isNullableAllowed = isNullableAllowed) {
        override fun put(bundle: Bundle, key: String, value: List<T>) {
            val serializedList = value.map {
                Json.encodeToString(serializer<T>(), it)
            }.toTypedArray()
            bundle.putStringArray(key, serializedList)
        }

        override fun get(bundle: Bundle, key: String): List<T>? {
            return bundle.getStringArray(key)?.map { Json.decodeFromString<T>(it) }
        }

        override fun serializeAsValue(value: List<T>): String {
            return Json.encodeToString(ListSerializer(serializer<T>()), value)
        }

        override fun parseValue(value: String): List<T> {
            return Json.decodeFromString(ListSerializer(serializer<T>()), value)
        }
    }
