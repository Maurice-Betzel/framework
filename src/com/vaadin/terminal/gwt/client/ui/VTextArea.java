/*
@VaadinApache2LicenseForJavaFiles@
 */

package com.vaadin.terminal.gwt.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;

/**
 * This class represents a multiline textfield (textarea).
 * 
 * TODO consider replacing this with a RichTextArea based implementation. IE
 * does not support CSS height for textareas in Strict mode :-(
 * 
 * @author Vaadin Ltd.
 * 
 */
public class VTextArea extends VTextField {
    public static final String CLASSNAME = "v-textarea";

    public VTextArea() {
        super(DOM.createTextArea());
        setStyleName(CLASSNAME);
    }

    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        // Call parent renderer explicitly
        super.updateFromUIDL(uidl, client);

        if (uidl.hasAttribute("rows")) {
            setRows(uidl.getIntAttribute("rows"));
        }

        if (getMaxLength() >= 0) {
            sinkEvents(Event.ONKEYUP);
        }
    }

    public void setRows(int rows) {
        setRows(getElement(), rows);
    }

    private native void setRows(Element e, int r)
    /*-{
    try {
        if(e.tagName.toLowerCase() == "textarea")
                e.rows = r;
    } catch (e) {}
    }-*/;

    @Override
    public void onBrowserEvent(Event event) {
        if (getMaxLength() >= 0 && event.getTypeInt() == Event.ONKEYUP) {
            Scheduler.get().scheduleDeferred(new Command() {
                public void execute() {
                    if (getText().length() > getMaxLength()) {
                        setText(getText().substring(0, getMaxLength()));
                    }
                }
            });
        }
        super.onBrowserEvent(event);
    }

    @Override
    public int getCursorPos() {
        // This is needed so that TextBoxImplIE6 is used to return the correct
        // position for old Internet Explorer versions where it has to be
        // detected in a different way.
        return getImpl().getTextAreaCursorPos(getElement());
    }

    @Override
    public void onKeyDown(KeyDownEvent event) {
        // Overridden to avoid submitting TextArea value on enter in IE. This is
        // another reason why widgets should inherit a common abstract
        // class instead of directly each other.
    }
}
