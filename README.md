# Robolectric - Espresso - Compose unified test suite

## Step 1 - Create a single activity app
Create an app with one activity with:

* A Text initially set to "0"
* A Button which, when clicked, increments the number in the Text
```
┌───────────────┐ 
│ MainActivity  │ 
│               │ 
│ Text          │ 
│ "0"           │ 
│               │ 
│               │ 
│               │ 
│ Button        │ 
│ "Add one"     │
│               │ 
│               │ 
└───────────────┘ 
```
### Step 1.1 
Create the app.

### Step 1.2
Create a UI instrumentation test in `src/androidTest` using `AndroidComposeTestRule` which launches the activity and tests the behavior of the Button and Text.

### Step 1.3
Create a Robolectric test in `src/test` which tests the same thing, using Robolectric apis (not best practice).

### Step 1.4
Adapt the Robolectric test to use androidx test apis instead of Robolectric apis.

### Step 1.5 
Merge the two tests into one single test folder.

## Step 2 - Create an app with two activities
Create an app with two activities:
```
┌───────────────┐                       ┌───────────────┐                  ┌──────────────┐
│ MainActivity  │                       │ SecondActivity│                  │MainActivity  │
│               │                       │               │                  │              │
│ Label         │                       │ Text input    │                  │Label         │
│ ""            │                       │ "Hello"       │                  │"Hello"       │
│               │                       │               │                  │              │
│               │                       │               │                  │              │
│               │                       │               │                  │              │
│ Button        │      startActivity    │ Button        │     finish       │Button        │
│ "Click me"    ├──────────────────────►│ "Close"       ├─────────────────►│"Click me"    │
│               │       ForResult       │               │                  │              │
│               │                       │               │                  │              │
└───────────────┘                       └───────────────┘                  └──────────────┘
```

* The app launches in `MainActivity`.
* Click on "Click me" to start `SecondActivity` for a result.
* In `SecondActivity`, type some text in the Text input.
* In `SecondActivity`, click "Close".
* `SecondActivity` sets the result, and finishes.
* `MainActivity` shows the text that was entered in `SecondActivity`.

### Step 2.1
Create the app.

### Step 2.2
Create a UI instrumentation test in `src/androidTest` using `AndroidComposeTestRule` which launches the activity and tests the behavior of the activities.

### Step 2.3
Create a Robolectric test in `src/test` which tests the same thing, using Robolectric apis for the wiring between activities

### Step 2.4
Create a ShadowActivity to replace the wiring done in step 2.3

### Step 2.5
Merge the two tests into one single test folder.

# References
* Robolectric: https://robolectric.org/
* Testing in Jetpack Compose: https://developer.android.com/codelabs/jetpack-compose-testing#2
