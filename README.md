# Experiment 12: Android Web Services HTTP Request Demo

A modern, responsive Android application built using **Kotlin**, **XML Layouts**, and **Material Components** that fetches real user data asynchronously from the public REST API [JSONPlaceholder](https://jsonplaceholder.typicode.com/users) using `HttpURLConnection` and Kotlin Coroutines (`Dispatchers.IO`).

---

## 📱 Application Screens

### 1. Home Screen
- **Purple Material Toolbar**: Title `HTTP Request Demo`.
- **Card UI**: Centered rounded card displaying a blue globe icon, title, subtitle, and a large purple **Fetch Data** button to initiate HTTP web service requests.

### 2. Fetched Users Screen
- **Header**: `Fetched Users (Tap for details):`
- **User Cards (RecyclerView)**:
  - User ID number inside a purple circular badge.
  - Full name in bold.
  - `@username` highlighted in brand purple.
  - Email address.
  - Right arrow indicator.
- **Asynchronous Fetching & Error Handling**: Displays a loading progress bar during the HTTP GET request and a clean error/retry UI if connection issues occur.

### 3. User Details Screen
- **Profile Header Card**: Large rounded profile card displaying user ID badge, full name, and username.
- **Contact Information Card**: Email, Phone, and Website with vector icons and dividers.
- **Address Card**: Street, Suite, City, and ZIP code.
- **Company Details Card**: Company Name, Catchphrase, and Business Description (`bs`).

---

## 🛠 Tech Stack & Architecture

- **Language**: Kotlin
- **UI Toolkit**: Android XML Layouts & Material Components (`MaterialCardView`, `RecyclerView`, `MaterialToolbar`)
- **Networking**: `java.net.HttpURLConnection` (Async via Kotlin Coroutines `Dispatchers.IO`)
- **JSON Parsing**: Native `org.json.JSONArray` & `org.json.JSONObject`
- **Data Models**: Immutable Data Classes (`User`, `Address`, `Company`) implementing `Serializable`

---

## 🌐 API Specification

- **Endpoint**: `https://jsonplaceholder.typicode.com/users`
- **HTTP Method**: `GET`
- **Permissions Required**: `android.permission.INTERNET` declared in `AndroidManifest.xml`

---

## 📂 Folder & File Structure

```
app/src/main/
├── AndroidManifest.xml
├── java/com/example/exp12/
│   ├── MainActivity.kt          # Home Screen Activity
│   ├── UsersActivity.kt         # User List Screen & Async Fetching
│   ├── UserDetailActivity.kt    # User Details Screen
│   ├── adapter/
│   │   └── UserAdapter.kt       # RecyclerView Adapter
│   ├── model/
│   │   └── User.kt             # Data Classes (User, Address, Company)
│   └── network/
│       └── UserApiClient.kt     # HTTP Network Engine
└── res/
    ├── drawable/                # Custom drawables & Vector icons
    ├── layout/
    │   ├── activity_main.xml
    │   ├── activity_users.xml
    │   ├── activity_user_detail.xml
    │   └── item_user.xml
    └── values/
        ├── colors.xml
        └── themes.xml
```

---

## ⚙️ How to Build & Run

1. Open **Android Studio** and select **Open** → Choose the `exp12` project folder.
2. Allow Gradle to sync project dependencies.
3. Connect an Android device (with USB Debugging enabled) or start an Emulator.
4. Click **Run 'app'** (or press `Shift + F10`).
