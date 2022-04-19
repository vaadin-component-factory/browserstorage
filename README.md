# Vaadin BrowserStorage Add-on

Adds server-side logic and a JavaScript file to allow usage of local and session storage on the client's browser from server-side Vaadin code.

This add-on should be forward-compatible up to Vaadin 23.

## Usage

The API for SessionStorage and LocalStorage are identical, and have been mapped to the Storage object API as returning completable futures, with the addition of a "contains key" function to potentially save on bandwidth, as follows:

```java
CompletableFuture<Boolean> containsKey(String key);
CompletableFuture<String> getItem(String key);
CompletableFuture<Boolean> setItem(String key, String value);
CompletableFuture<Boolean> removeItem(String key);
CompletableFuture<Boolean> clear();
```

The returned boolean for `setItem`, `removeItem` and `clear` can safely be ignored for now; it is reserved for future use.

To access `SessionStorage` or `LocalStorage`, create an instance of them:

```java
LocalStorage localStorage = new LocalStorage();
localStorage.setItem("foo", "bar");
```

Because the objects need to bind to a specific UI. In complex codebases, you may want to use the explicit constructor:

```java
UI myUI = UI.getCurrent();
LocalStorage localStorage = new LocalStorage(myUI);
localStorage.setItem("foo", "bar");
```

The above examples are equivalent - the default constructor uses UI.getCurrent() internally to store a UI reference.

### Demo/test UI deployment

Starting the demo/test server:
```
mvn jetty:run
```

This deploys demo at http://localhost:8080
