package team.noweekend.catalog.navigation

import team.noweekend.catalog.model.Component
import team.noweekend.catalog.model.Example
import team.noweekend.core.common.android.extension.serializeToStringArrayNavType
import team.noweekend.core.common.android.extension.serializeToStringNavType
import kotlin.reflect.typeOf

internal object CatalogNavTypeMap {
    val ComponentNavTypeMap = mapOf(
        typeOf<Component>() to serializeToStringNavType<Component>(),
    )

    val ExamplesNavTypeMap = mapOf(
        typeOf<List<Example>>() to serializeToStringArrayNavType<Example>(),
    )
}
