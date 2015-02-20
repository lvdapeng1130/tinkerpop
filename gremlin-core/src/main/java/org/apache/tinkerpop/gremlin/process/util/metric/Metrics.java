/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.gremlin.process.util.metric;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Holds metrics data; typically for .profile()-step analysis. Metrics may be nested. Nesting enables the ability to
 * capture explicit metrics for multiple distinct operations. Annotations are used to store miscellaneous notes that
 * might be useful to a developer when examining results, such as index coverage for Steps in a Traversal.
 *
 * @author Bob Briody (http://bobbriody.com)
 */
public interface Metrics {


    /**
     * The MetricsId used to obtain the element count via Metrics.getCount(String countKey)
     */
    public static final String ELEMENT_COUNT_ID = "elementCount";

    /**
     * The MetricsId used to obtain the traverser count via Metrics.getCount(String countKey)
     */
    public static final String TRAVERSER_COUNT_ID = "traverserCount";

    /**
     * The annotation key used to obtain the percent duration via Metrics.getAnnotation(String key)
     */
    public static final String PERCENT_DURATION_KEY = "percentDur";

    /**
     * Get the duration of execution time taken.
     *
     * @param units
     * @return
     */
    public long getDuration(TimeUnit units);

    /**
     * Get the count for the corresponding countKey.
     *
     * @param countKey key for counter to get.
     * @return
     */
    public long getCount(String countKey);

    /**
     * Get the map of all counters. This method copies the internal map.
     *
     * @return a Map where the key is the counter ID and the value is the counter value.
     */
    public Map<String, Long> getCounts();

    /**
     * Name of this Metrics.
     *
     * @return name of this Metrics.
     */
    public String getName();

    /**
     * Id of this Metrics.
     *
     * @return id of this Metrics.
     */
    public String getId();


    /**
     * Get the nested Metrics objects. Metrics will be ordered in the order they were inserted.
     *
     * @return the nested Metrics objects.
     */
    public Collection<? extends Metrics> getNested();

    /**
     * Get a nested Metrics object by Id.
     *
     * @param metricsId
     * @return a nested Metrics object.
     */
    Metrics getNested(String metricsId);

    /**
     * Obtain the annotations for this Metrics.
     *
     * @return the annotations for this Metrics. Modifications to the returned object are persisted in the original.
     */
    public Map<String, String> getAnnotations();

    /**
     * Obtain the annotation with the specified key.
     *
     * @param key key of the annotation to obtain.
     * @return
     */
    public String getAnnotation(String key);
}
