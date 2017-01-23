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

import com.netflix.metacat.canonical.common.spi.util.Pageable;
import com.netflix.metacat.canonical.common.spi.util.Sort;
import com.netflix.metacat.common.MetacatRequestContext;
import com.netflix.metacat.common.QualifiedName;
import com.netflix.metacat.common.dto.BaseDto;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Generic interface for methods pertaining to resources from connectors such as Databases, Tables and Partitions.
 *
 * @param <T> The Type of resource this interface works for
 * @author tgianos
 * @since 0.1.51
 */
public interface ConnectorBaseService<T extends BaseDto> {
    /**
     * Standard error message for all default implementations.
     */
    String UNSUPPORTED_MESSAGE = "Not implemented for this connector";

    /**
     * Create a resource.
     *
     * @param requestContext The request context
     * @param resource       The resource metadata
     */
    default void create(@Nonnull final MetacatRequestContext requestContext, @Nonnull final T resource) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    /**
     * Update a resource with the given metadata.
     *
     * @param requestContext The request context
     * @param resource       resource metadata
     */
    default void update(@Nonnull final MetacatRequestContext requestContext, @Nonnull final T resource) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    /**
     * Delete a database with the given qualified name.
     *
     * @param requestContext The request context
     * @param name           The qualified name of the resource to delete
     */
    default void delete(@Nonnull final MetacatRequestContext requestContext, @Nonnull final QualifiedName name) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    /**
     * Return a resource with the given name.
     *
     * @param requestContext The request context
     * @param name           The qualified name of the resource to get
     * @return The resource metadata.
     */
    default T get(@Nonnull final MetacatRequestContext requestContext, @Nonnull final QualifiedName name) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    /**
     * Get a list of all the resources under the given resource identified by <code>name</code>. Optionally sort by
     * <code>sort</code> and add pagination via <code>pageable</code>.
     *
     * @param requestContext The request context
     * @param name           The name of the resource under which to list resources of type <code>T</code>
     * @param prefix         The optional prefix to apply to filter resources for listing
     * @param sort           Optional sorting parameters
     * @param pageable       Optional paging parameters
     * @return A list of type <code>T</code> resources in the desired order if required
     */
    default List<T> list(
        @Nonnull final MetacatRequestContext requestContext,
        @Nonnull final QualifiedName name,
        @Nullable final QualifiedName prefix,
        @Nullable final Sort sort,
        @Nullable final Pageable pageable
    ) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    /**
     * Returns a list of qualified names of resources under the resource identified by <code>name</code>.
     *
     * @param requestContext The request context
     * @param name           The name of the resource under which to list resources of type <code>T</code>
     * @param prefix         The optional prefix to apply to filter resources for listing
     * @param sort           Optional sorting parameters
     * @param pageable       Optional paging parameters
     * @return A list of Qualified Names of resources in the desired order if required
     */
    default List<QualifiedName> listNames(
        @Nonnull final MetacatRequestContext requestContext,
        @Nonnull final QualifiedName name,
        @Nullable final QualifiedName prefix,
        @Nullable final Sort sort,
        @Nullable final Pageable pageable
    ) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    /**
     * Rename the specified resource.
     *
     * @param requestContext The metacat request context
     * @param oldName        The current resource name
     * @param newName        The new resource name
     */
    default void rename(
        @Nonnull final MetacatRequestContext requestContext,
        @Nonnull final QualifiedName oldName,
        @Nonnull final QualifiedName newName
    ) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }
}
