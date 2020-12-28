package de.byteleaf.companyon.common.error.exception

import de.byteleaf.companyon.common.error.ErrorCode
import java.lang.RuntimeException

abstract class AbstractException(val errorCode: ErrorCode, message: String) : RuntimeException(
        "Error code: $errorCode. $message"
)