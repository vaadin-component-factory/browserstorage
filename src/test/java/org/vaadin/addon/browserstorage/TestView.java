/*
 * Copyright 2000-2022 Vaadin Ltd.
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
package org.vaadin.addon.browserstorage;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

@Route("")
public class TestView extends Div {

    final LocalStorage localStorage = new LocalStorage();
    final SessionStorage sessionStorage = new SessionStorage();

    public TestView() {

        // Session storage test
        {
            add(new Label("Test session storage"));
            HorizontalLayout hl = new HorizontalLayout();
            TextField key = new TextField("Key");
            key.setValue("Foo");
            TextField value = new TextField("Value");
            value.setValue("Bar");
            hl.add(key, value);
            add(hl);
            hl = new HorizontalLayout();
            Button saveButton = new Button("Set value");
            Button testButton = new Button("Check value");
            Button loadButton = new Button("Get value");
            Button removeButton = new Button("Remove value");
            Button clearButton = new Button("Clear all");
            hl.add(saveButton, testButton, loadButton, removeButton, clearButton);
            add(hl);

            saveButton.addClickListener(e -> {
                sessionStorage.setItem(key.getValue(), value.getValue()).thenApply(b -> 
                    Notification.show("Stored " + key.getValue() + " = " +
                     value.getValue() + " in session storage")
                );
            });

            testButton.addClickListener(e -> {
                final String kv = key.getValue();
                sessionStorage.containsKey(kv).thenApply(b ->
                    Notification.show("A value for key '" + kv + "' " +
                     (b ? "exists" : "does not exist") + " in session storage")
                );
            });

            loadButton.addClickListener(e -> {
                final String kv = key.getValue();
                sessionStorage.getItem(kv).thenApply(v ->
                    Notification.show("Value for key '" + kv + "' " +
                     "in session storage is '" + v + "'")
                );
            });

            removeButton.addClickListener(e -> {
                final String kv = key.getValue();
                sessionStorage.removeItem(kv).thenApply(v ->
                    Notification.show("Value for key '" + kv + "' " +
                     "removed from session storage")
                );
            });

            clearButton.addClickListener(e -> {
                sessionStorage.clear().thenApply(v ->
                    Notification.show("Session storage cleared.")
                );
            });
        }

        // Local storage test
        {
            add(new Label("Test local storage"));
            HorizontalLayout hl = new HorizontalLayout();
            TextField key = new TextField("Key");
            key.setValue("Answer");
            TextField value = new TextField("Value");
            value.setValue("42");
            hl.add(key, value);
            add(hl);
            hl = new HorizontalLayout();
            Button saveButton = new Button("Set value");
            Button testButton = new Button("Check value");
            Button loadButton = new Button("Get value");
            Button removeButton = new Button("Remove value");
            Button clearButton = new Button("Clear all");
            hl.add(saveButton, testButton, loadButton, removeButton, clearButton);

            add(hl);

            saveButton.addClickListener(e -> {
                localStorage.setItem(key.getValue(), value.getValue()).thenApply(b ->
                    Notification.show("Stored " + key.getValue() + " = " +
                     value.getValue() + " in local storage")
                );
            });

            testButton.addClickListener(e -> {
                final String kv = key.getValue();
                localStorage.containsKey(kv).thenApply(b ->
                    Notification.show("A value for key '" + kv + "' " +
                     (b ? "exists" : "does not exist") + " in local storage")
                );
            });

            loadButton.addClickListener(e -> {
                final String kv = key.getValue();
                localStorage.getItem(kv).thenApply(v ->
                    Notification.show("Value for key '" + kv + "' " +
                     "in local storage is '" + v + "'")
                );
            });

            removeButton.addClickListener(e -> {
                final String kv = key.getValue();
                localStorage.removeItem(kv).thenApply(v ->
                    Notification.show("Value for key '" + kv + "' " +
                     "removed from local storage")
                );
            });

            clearButton.addClickListener(e -> {
                localStorage.clear().thenApply(v ->
                    Notification.show("Local storage cleared.")
                );
            });
        }
    }
}
