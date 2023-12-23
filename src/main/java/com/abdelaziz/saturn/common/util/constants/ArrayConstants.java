package com.abdelaziz.saturn.common.util.constants;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * avoids unnecessary #empty calls, avoids unnecessary heap, and compatibility with other mods should be fine.
 */
@SuppressWarnings("rawtypes")
public interface ArrayConstants {
    List EMPTY_LIST = Collections.emptyList();
    Optional EMPTY_OPTIONAL = Optional.empty();
}