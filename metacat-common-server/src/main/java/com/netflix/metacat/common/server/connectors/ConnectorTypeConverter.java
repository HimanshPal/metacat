/*
 *
 *  Copyright 2017 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
package com.netflix.metacat.common.server.connectors;

import com.netflix.metacat.common.type.Type;
import lombok.NonNull;

import javax.annotation.Nonnull;

/**
 * Canonical type converter class.
 *
 * @author tgianos
 * @author zhenl
 * @since 1.0.0
 */
public interface ConnectorTypeConverter {

    /**
     * Converts to metacat type.
     *
     * @param type type
     * @return metacat type
     */
    Type toMetacatType(@Nonnull @NonNull final String type);

    /**
     * Converts from metacat type.
     *
     * @param type type
     * @return connector type
     */
    String fromMetacatType(@Nonnull @NonNull final Type type);
}
