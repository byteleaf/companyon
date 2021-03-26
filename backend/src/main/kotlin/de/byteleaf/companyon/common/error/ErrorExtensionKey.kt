package de.byteleaf.companyon.common.error

enum class ErrorExtensionKey(val value: String) {
    CODE("code"),
    MESSAGE("message"),
    CURRENT_USER_ID("currentUserId"),
    TARGET_USER_ID("targetUserId"),
    PERMISSION_TYPE("permissionType"),
    ENTITY_ID("entityId"),
    ENTITY_TYPE("entityType")
}
