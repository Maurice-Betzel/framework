/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.osgi.resources.impl;

import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

import com.vaadin.osgi.resources.VaadinResourceService;

/**
 * Implementation of {@link VaadinResourceService}. Uses bundle version as a
 * prefix for the /VAADIN/ folder.
 *
 * @author Vaadin Ltd.
 *
 * @since 8.1
 */
@Component(immediate = true, scope = ServiceScope.SINGLETON)
public class VaadinResourceServiceImpl implements VaadinResourceService {
    private static final String NAMESPACE_PREFIX = "vaadin-%s";

    private String bundleVersion;

    private String pathPrefix;

    /**
     * Constructor, Sets the version of the bundle managing this service.
     *
     *
     */

    public VaadinResourceServiceImpl() {
        this.bundleVersion = FrameworkUtil.getBundle(VaadinResourceServiceImpl.class).
                getBundleContext().getBundle().getVersion().toString();
        pathPrefix = String.format(NAMESPACE_PREFIX, bundleVersion);
    }


    public void setBundleVersion(String bundleVersion) {

    }

    @Override
    public void publishTheme(String themeName, HttpService httpService)
            throws NamespaceException {
        String themeAlias = PathFormatHelper.getThemeAlias(themeName, pathPrefix);
        String themePath = PathFormatHelper.getThemePath(themeName);
        httpService.registerResources(themeAlias, themePath, null);
    }

    @Override
    public void publishResource(String resource, HttpService httpService)
            throws NamespaceException {
        String alias = PathFormatHelper.getRootResourceAlias(resource, pathPrefix);
        String path = PathFormatHelper.getRootResourcePath(resource);
        httpService.registerResources(alias, path, null);
    }

    @Override
    public void publishWidgetset(String widgetset, HttpService httpService)
            throws NamespaceException {
        String widgetsetAlias = PathFormatHelper.getWidgetsetAlias(widgetset,
                pathPrefix);
        String widgetsetPath = PathFormatHelper.getWidgetsetPath(widgetset);
        httpService.registerResources(widgetsetAlias, widgetsetPath, null);
    }

    @Override
    public String getResourcePathPrefix() {
        return String.format(NAMESPACE_PREFIX, bundleVersion);
    }
}
