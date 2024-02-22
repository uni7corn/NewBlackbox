if(NOT TARGET shadowhook::shadowhook)
add_library(shadowhook::shadowhook SHARED IMPORTED)
set_target_properties(shadowhook::shadowhook PROPERTIES
    IMPORTED_LOCATION "/home/alex/.gradle/caches/transforms-3/b5cb4587a1f8b9dd87b5ac4af07cbb1a/transformed/jetified-shadowhook-1.0.8/prefab/modules/shadowhook/libs/android.armeabi-v7a/libshadowhook.so"
    INTERFACE_INCLUDE_DIRECTORIES "/home/alex/.gradle/caches/transforms-3/b5cb4587a1f8b9dd87b5ac4af07cbb1a/transformed/jetified-shadowhook-1.0.8/prefab/modules/shadowhook/include"
    INTERFACE_LINK_LIBRARIES ""
)
endif()

