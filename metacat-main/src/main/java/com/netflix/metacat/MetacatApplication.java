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
package com.netflix.metacat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Metacat application entry point.
 *
 * @author tgianos
 * @since 1.1.0
 */
@SpringBootApplication
public class MetacatApplication {

    /**
     * Constructor.
     */
    protected MetacatApplication() {
    }

    /**
     * Main.
     *
     * @param args Program arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(MetacatApplication.class, args);
    }
}
