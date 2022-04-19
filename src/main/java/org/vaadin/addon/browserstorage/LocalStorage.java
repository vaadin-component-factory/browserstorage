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

import com.vaadin.flow.component.UI;

/**
 * Provides access to the local storage object of the client's browser.
 */
public class LocalStorage extends BrowserStorage {

    /**
     * Creates a LocalStorage object for a specific UI. LocalStorage access
     * is UI specific due to a need to run JavaScript on the specific page
     * that is attached to that specific UI.
     * 
     * @param ui a valid UI instance
     */
    public LocalStorage(UI ui) {
        super(ui, "localStorage");
    }

    /**
     * Creates a LocalStorage object for the *CURRENT UI*.
     * This is often the right thing to do, but sometimes it's necessary
     * to be explicit, and use the {@link #LocalStorage(UI)} constructor.
     */
    public LocalStorage() {
        this(UI.getCurrent());
    }
}
