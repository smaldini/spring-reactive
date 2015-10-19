/*
 * Copyright (c) 2011-2015 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.reactive.convert;

import reactor.core.publisher.convert.CompositionDependencyUtils;

import org.springframework.core.convert.converter.ConverterRegistry;

/**
 * @author Sebastien Deleuze
 */
public class DefaultConversionService extends ReactiveConversionService {

	public DefaultConversionService() {
		addDefaultConverters(this);
	}

	public static void addDefaultConverters(ConverterRegistry converterRegistry) {
		converterRegistry.addConverter(new CompletableFutureConverter());
		converterRegistry.addConverter(new PublisherConverter());
		if (CompositionDependencyUtils.hasReactorStream()) {
			converterRegistry.addConverter(new ReactorPromiseConverter());
			converterRegistry.addConverter(new ReactorStreamConverter());
		}
		if (CompositionDependencyUtils.hasRxJava1Single()) {
			converterRegistry.addConverter(new RxJava1SingleConverter());
		}
		if (CompositionDependencyUtils.hasRxJava1()) {
			converterRegistry.addConverter(new RxJava1ObservableConverter());
		}
	}

}
