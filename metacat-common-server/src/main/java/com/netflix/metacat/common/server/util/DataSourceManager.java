/*
 *
 *  Copyright 2016 Netflix, Inc.
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
package com.netflix.metacat.common.server.util;

import com.google.common.collect.Maps;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.apache.tomcat.jdbc.pool.DataSourceProxy;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Data source manager.
 */
public final class DataSourceManager {
    private static final String JDO_PREFIX = "javax.jdo.option.";
    private static DataSourceManager instance = new DataSourceManager();
    private Map<String, DataSource> dataSources = Maps.newConcurrentMap();

    private DataSourceManager() {
    }

    /**
     * This method has been provided so that it can be used in the connectors. We could have injected into the plugins.
     *
     * @return DataSourceManager
     */
    public static DataSourceManager get() {
        return instance;
    }

    /**
     * Initialize a data source and store it.
     *
     * @param catalogName catalog name
     * @param properties  properties
     * @return DataSourceManager
     */
    public DataSourceManager load(final String catalogName, final Map<String, String> properties) {
        if (dataSources.get(catalogName) == null) {
            createDataSource(catalogName, properties);
        }
        return this;
    }

    /**
     * Initialize a data source and store it.
     *
     * @param catalogName catalog name
     * @param properties  properties
     * @return DataSourceManager
     */
    public DataSourceManager load(final String catalogName, final Properties properties) {
        if (dataSources.get(catalogName) == null) {
            createDataSource(catalogName, properties);
        }
        return this;
    }

    /**
     * Returns the data source loaded for the given catalog.
     *
     * @param catalogName catalog name
     * @return DataSource
     */
    public DataSource get(final String catalogName) {
        return dataSources.get(catalogName);
    }

    private synchronized void createDataSource(final String catalogName, final Map props) {
        if (dataSources.get(catalogName) == null) {
            final Properties dataSourceProperties = new Properties();
            props.forEach((key, value) -> {
                final String prop = String.valueOf(key);
                if (prop.startsWith(JDO_PREFIX)) {
                    dataSourceProperties.put(prop.substring(JDO_PREFIX.length()), value);
                }
            });
            if (!dataSourceProperties.isEmpty()) {
                try {
                    final DataSource dataSource = new DataSourceFactory().createDataSource(dataSourceProperties);
                    dataSources.put(catalogName, dataSource);
                } catch (Exception e) {
                    throw new RuntimeException(String
                        .format("Failed to load the data source for catalog %s with error [%s]", catalogName,
                            e.getMessage()), e);
                }
            }
        }
    }

    /**
     * Closes all the data sources stored in the manager.
     */
    @PreDestroy
    public void close() {
        final Iterator<DataSource> iter = dataSources.values().iterator();
        while (iter.hasNext()) {
            final DataSourceProxy dataSource = (DataSourceProxy) iter.next();
            if (dataSource != null) {
                dataSource.close();
            }
            iter.remove();
        }
    }
}
