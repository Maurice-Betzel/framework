package com.vaadin.tests.tickets;

import com.vaadin.server.LegacyApplication;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.LegacyWindow;
import com.vaadin.v7.ui.LegacyTextField;

public class Ticket2026 extends LegacyApplication {

    @Override
    public void init() {
        LegacyWindow w = new LegacyWindow(getClass().getSimpleName());
        setMainWindow(w);

        GridLayout layout = new GridLayout(2, 2);
        layout.setSpacing(true);

        @SuppressWarnings("unused")
        int nr = 5;
        LegacyTextField tf;
        tf = new LegacyTextField("TextField (tabIndex 1)");
        tf.setTabIndex(1);
        tf.focus();
        layout.addComponent(tf);
        layout.addComponent(new LegacyTextField("TextField without tab index"));
        layout.addComponent(new LegacyTextField("TextField without tab index"));
        layout.addComponent(new LegacyTextField("TextField without tab index"));
        layout.addComponent(new LegacyTextField("TextField without tab index"));
        tf = new LegacyTextField("TextField (tabIndex 2)");
        tf.setTabIndex(2);
        layout.addComponent(tf);

        w.setContent(layout);
    }
}
