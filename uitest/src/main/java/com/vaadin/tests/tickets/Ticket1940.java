package com.vaadin.tests.tickets;

import com.vaadin.server.LegacyApplication;
import com.vaadin.ui.LegacyWindow;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.ui.LegacyTextField;

public class Ticket1940 extends LegacyApplication {

    @Override
    public void init() {
        final LegacyWindow w = new LegacyWindow(getClass().getName());
        setMainWindow(w);

        final VerticalLayout l = new VerticalLayout();
        l.setWidth("200px");
        l.setHeight(null);
        LegacyTextField t = new LegacyTextField();
        l.addComponent(t);
        t.setRequired(true);
        w.addComponent(l);

    }

}
