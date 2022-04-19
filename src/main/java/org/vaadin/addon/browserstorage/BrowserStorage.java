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

import java.util.concurrent.CompletableFuture;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JavaScript;

/**
 * Allows handling generic browser storage object access from Vaadin.
 * Create an instance of a sublcass of this class in order to use it.
 * 
 * @author Vaadin Ltd.
 */
@JavaScript("./browserstorage.js")
public class BrowserStorage {

    private final UI ui;
    private final String setValueFn;
    private final String getValueFn;
    private final String containsKeyFn;
    private final String removeFn;
    private final String clearFn;

    /**
     * Creates a BrowserStorage object. The storage needs to be bound
     * to a specific UI instance in order to get access to the correct
     * Page object.
     * 
     * @param ui a UI instance
     * @param type either "localStorage" or "sessionStorage"
     */
    protected BrowserStorage(UI ui, String type) {
        this.ui = ui;
        setValueFn = "return window.browserStorage.setItem('" + type +
         "',$0,$1)";
        getValueFn = "return window.browserStorage.getItem('" + type +
         "',$0)";
        containsKeyFn = "return window.browserStorage.containsKey('" +
         type + "',$0)";
        removeFn = "return window.browserStorage.removeItem('" + type +
         "',$0)";
        clearFn = "return window.browserStorage.clear('" + type + "')";
    }

    /**
     * Store a value on the client. Since there is no semantic
     * difference on the browser side between non-existent items
     * and items assigned a null value, assigning a null value
     * will call {@code storage.removeItem()}
     * 
     * @param key key string used to identify the value
     * @param value a string value. If 'value' is null, the key is deleted.
     * @return true when the JavaScript function completes, or false if a client-side error occurred
     */
    public CompletableFuture<Boolean> setItem(String key, String value) {
        return ui.getPage().executeJs(setValueFn, key, value)
            .toCompletableFuture(Boolean.class);
    }

    /**
     * Fetch a value from client side storage.
     * 
     * @param key key string used to identify the value
     * @return a CompletableFuture carrying a string (or null if value does not exist)
     */
    public CompletableFuture<String> getItem(String key) {
        return ui.getPage().executeJs(getValueFn, key)
            .toCompletableFuture(String.class);
    }

    /**
     * Check if a key has been associated with a value on the
     * client side. This is different from checking for a null
     * return value from {@link #getItem(String)}, because the
     * actual stored value is not transmitted over the network
     * back to the server if it exists.
     * 
     * @param key key string used to identify the value
     * @return true if a non-null value is associated with the specified key, otherwise false
     */
    public CompletableFuture<Boolean> containsKey(String key) {
        return ui.getPage().executeJs(containsKeyFn, key)
            .toCompletableFuture(Boolean.class);
    }

    /**
     * Remove a value by key from the client storage. This is the same
     * as running {@link #setItem(String, String)} with a null value.
     * If the key does not exist, this function does nothing.
     * 
     * @param key key string used to identify the value
     * @return true when the JavaScript function completes, or false if a client-side error occurred
     */
    public CompletableFuture<Boolean> removeItem(String key) {
        return ui.getPage().executeJs(removeFn, key)
            .toCompletableFuture(Boolean.class);
    }

    /**
     * Remove all stored values from the client storage.
     * 
     * @return true true when the JavaScript function completes, or false if a client-side error occurred
     */
    public CompletableFuture<Boolean> clear() {
        return ui.getPage().executeJs(clearFn)
            .toCompletableFuture(Boolean.class);
    }

}
