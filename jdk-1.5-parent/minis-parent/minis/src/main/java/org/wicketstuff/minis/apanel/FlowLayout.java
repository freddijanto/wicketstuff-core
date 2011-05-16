/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wicketstuff.minis.apanel;

import java.util.List;

import org.apache.wicket.Component;

/**
 * Layout that only concatenates markup for components.
 */
public class FlowLayout implements ILayout
{
	private static final long serialVersionUID = 1L;
	private final RenderersList renderersList;

	/**
	 * Constructor.
	 */
	public FlowLayout()
	{
		this(RenderersList.getDefaultRenderers());
	}

	/**
	 * Constructor.
	 * 
	 * @param renderers
	 *            list of renderers to customize component rendering in this layout
	 */
	public FlowLayout(final List<IComponentRenderer<?>> renderers)
	{
		renderersList = new RenderersList(renderers);
	}

	protected void onAfterTag(final Component component, final StringBuilder stringBuilder)
	{
	}

	protected void onBeforeTag(final Component component, final StringBuilder stringBuilder)
	{
	}

	/**
	 * @see org.wicketstuff.minis.apanel.ILayout#renderComponents(java.util.List)
	 */
	public CharSequence renderComponents(final List<? extends Component> components)
	{
		final StringBuilder stringBuilder = new StringBuilder();

		for (final Component component : components)
		{
			final IComponentRenderer<Component> componentRenderer = renderersList.findRendererForClass(component.getClass());
			final CharSequence markup = componentRenderer.getMarkup(component);

			onBeforeTag(component, stringBuilder);
			stringBuilder.append(markup);
			onAfterTag(component, stringBuilder);
		}
		return stringBuilder;
	}
}