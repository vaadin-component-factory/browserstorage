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

(() => {
    if(window.browserStorage !== undefined) return;

    window.browserStorage = {
        setItem: (loc, key, value) => {
            try {
                if (value === null) {
                    window[loc].removeItem(key);
                } else {
                    window[loc].setItem(key, value);
                }
            } catch(e) { 
                console.error("Failed to set value '" + value +
                "' on key '" + key + "' in storage '" + loc + "'", e);
                return false;
            }
            return true;
        },

        getItem: (loc, key) => {
            try {
                return window[loc].getItem(key);
            } catch(e) { 
                console.error("Failed to get value from storage '" +
                loc + "' with key '" + key + "'", e);
            }
            return null;
        },

        containsKey: (loc, key) => {
            try {
                return window[loc].getItem(key) !== null;
            } catch(e) { 
                console.error("Failed to get status of value from storage '" +
                loc + "' with key '" + key + "'", e);
            }
            return false;
        },

        removeItem: (loc, key) => {
            try {
                window[loc].removeItem(key);
            } catch(e) {
                console.error("Failed to remove item with key '" + key + "' " +
                "from storage '" + loc + "'", e);
                return false;
            }
            return true;
        },

        clear: (loc) => {
            try {
                window[loc].clear();
            } catch(e) {
                console.error("Failed to clear storage '" + loc + "'", e);
                return false;
            }
            return true;
        }
    };
})();
