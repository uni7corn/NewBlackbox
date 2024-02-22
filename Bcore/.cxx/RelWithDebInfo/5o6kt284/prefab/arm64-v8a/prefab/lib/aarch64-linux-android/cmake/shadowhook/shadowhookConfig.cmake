if(NOT TARGET shadowhook::shadowhook)
add_library(shadowhook::shadowhook SHARED IMPORTED)
set_target_properties(shadowhook::shadowhook PROPERTIES
    IMPORTED_LOCATION "/home/alex/.gradle/caches/transforms-3/e90b0947d631f38af080d445f1caeaae/transformed/shadowhook-1.0.8/prefab/modules/shadowhook/libs/android.arm64-v8a/libshadowhook.so"
    INTERFACE_INCLUDE_DIRECTORIES "/home/alex/.gradle/caches/transforms-3/e90b0947d631f38af080d445f1caeaae/transformed/shadowhook-1.0.8/prefab/modules/shadowhook/include"
    INTERFACE_LINK_LIBRARIES ""
)
endif()

