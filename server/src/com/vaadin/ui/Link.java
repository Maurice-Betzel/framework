/* 
 * Copyright 2011 Vaadin Ltd.
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

package com.vaadin.ui;

import java.util.Map;

import com.vaadin.shared.ui.BorderStyle;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.Vaadin6Component;

/**
 * Link is used to create external or internal URL links.
 * 
 * @author Vaadin Ltd.
 * @since 3.0
 */
@SuppressWarnings("serial")
public class Link extends AbstractComponent implements Vaadin6Component {

    /* Target window border type constant: No window border */
    @Deprecated
    public static final BorderStyle TARGET_BORDER_NONE = BorderStyle.NONE;

    /* Target window border type constant: Minimal window border */
    @Deprecated
    public static final BorderStyle TARGET_BORDER_MINIMAL = BorderStyle.MINIMAL;

    /* Target window border type constant: Default window border */
    @Deprecated
    public static final BorderStyle TARGET_BORDER_DEFAULT = BorderStyle.DEFAULT;

    private Resource resource = null;

    private String targetName;

    private BorderStyle targetBorder = BorderStyle.DEFAULT;

    private int targetWidth = -1;

    private int targetHeight = -1;

    /**
     * Creates a new link.
     */
    public Link() {

    }

    /**
     * Creates a new instance of Link.
     * 
     * @param caption
     * @param resource
     */
    public Link(String caption, Resource resource) {
        setCaption(caption);
        this.resource = resource;
    }

    /**
     * Creates a new instance of Link that opens a new window.
     * 
     * 
     * @param caption
     *            the Link text.
     * @param targetName
     *            the name of the target window where the link opens to. Empty
     *            name of null implies that the target is opened to the window
     *            containing the link.
     * @param width
     *            the Width of the target window.
     * @param height
     *            the Height of the target window.
     * @param border
     *            the Border style of the target window.
     * 
     */
    public Link(String caption, Resource resource, String targetName,
            int width, int height, BorderStyle border) {
        setCaption(caption);
        this.resource = resource;
        setTargetName(targetName);
        setTargetWidth(width);
        setTargetHeight(height);
        setTargetBorder(border);
    }

    /**
     * Paints the content of this component.
     * 
     * @param target
     *            the Paint Event.
     * @throws PaintException
     *             if the paint operation failed.
     */
    @Override
    public void paintContent(PaintTarget target) throws PaintException {

        if (resource != null) {
            target.addAttribute("src", resource);
        } else {
            return;
        }

        // Target window name
        final String name = getTargetName();
        if (name != null && name.length() > 0) {
            target.addAttribute("name", name);
        }

        // Target window size
        if (getTargetWidth() >= 0) {
            target.addAttribute("targetWidth", getTargetWidth());
        }
        if (getTargetHeight() >= 0) {
            target.addAttribute("targetHeight", getTargetHeight());
        }

        // Target window border
        switch (getTargetBorder()) {
        case MINIMAL:
            target.addAttribute("border", "minimal");
            break;
        case NONE:
            target.addAttribute("border", "none");
            break;
        }
    }

    /**
     * Returns the target window border.
     * 
     * @return the target window border.
     */
    public BorderStyle getTargetBorder() {
        return targetBorder;
    }

    /**
     * Returns the target window height or -1 if not set.
     * 
     * @return the target window height.
     */
    public int getTargetHeight() {
        return targetHeight < 0 ? -1 : targetHeight;
    }

    /**
     * Returns the target window name. Empty name of null implies that the
     * target is opened to the window containing the link.
     * 
     * @return the target window name.
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * Returns the target window width or -1 if not set.
     * 
     * @return the target window width.
     */
    public int getTargetWidth() {
        return targetWidth < 0 ? -1 : targetWidth;
    }

    /**
     * Sets the border of the target window.
     * 
     * @param targetBorder
     *            the targetBorder to set.
     */
    public void setTargetBorder(BorderStyle targetBorder) {
        this.targetBorder = targetBorder;
        requestRepaint();
    }

    /**
     * Sets the target window height.
     * 
     * @param targetHeight
     *            the targetHeight to set.
     */
    public void setTargetHeight(int targetHeight) {
        this.targetHeight = targetHeight;
        requestRepaint();
    }

    /**
     * Sets the target window name.
     * 
     * @param targetName
     *            the targetName to set.
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
        requestRepaint();
    }

    /**
     * Sets the target window width.
     * 
     * @param targetWidth
     *            the targetWidth to set.
     */
    public void setTargetWidth(int targetWidth) {
        this.targetWidth = targetWidth;
        requestRepaint();
    }

    /**
     * Returns the resource this link opens.
     * 
     * @return the Resource.
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Sets the resource this link opens.
     * 
     * @param resource
     *            the resource to set.
     */
    public void setResource(Resource resource) {
        this.resource = resource;
        requestRepaint();
    }

    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        // TODO Remove once Vaadin6Component is no longer implemented
    }
}
