# Web Explorer Android Application
### Description
Web Explorer is a small android application that its purpose is to implement and test the Web Page Loader Android SDK which is my very first android library.

### Code Version
The current code version of the Web Explorer application is **"3.0"**.

### Requirements
Web Explorer application operates with **Android 21 and above** and it is written in Java and XML.

# Web Page Loader Android SDK Documentation
### Description
The Web Page Loader Android SDK will load and show on the UI a desired web page URL in a WebView on an overlay layout.

### Code Version
The current code version of the Web Page Loader Android SDK is **"beta 3.0.0"**.

### Quick Reference Guide
1. **Download** the Web Page Loader SDK **.AAR** file.
2. **Import** the **.AAR** file of the Web Page Loader SDK as a separate module to your project's libraries.
3. **Integrate** the appropriate dependency on your **grandle.build** app file.
4. Set up your application's **manifest** file in order to use the necessary **permission**.
5. **Import the necessary classes** in the activity that you wish to use the Web Page Loader SDK.
6. **Create** a new web page data set with the attributes that you wish using the library's **"WebPage"** class builder constructor.
7. **Initialize** the Web Page Loader SDK using its **init()** function.
8. **Open** the library's overlay layout panel using its **open()** function.    

### Requirements
Web Page Loader Android SDK operates with **Android 16 and above** and it is written in Java and XML.

### Integration Steps
1. **Download** the Web Page Loader SDK **.AAR** file from the link below:

    - https://www.dropbox.com/sh/mj06als5o4sh8dz/AADr4WDxaB55MUsyetCQ23k_a?dl=0

2. **Import** the **.AAR** file of the Web Page Loader SDK as a separate module to your project's libraries. If you are using Android Studio, right click on your project and select New Module. Then select Import .JAR or .AAR Package option and from the file browser locate Web Page Loader .AAR file. Right click again on your project and in the Module Dependencies tab choose to add Web Page Loader module that you recently added, as a dependency.

3. **Integrate** the appropriate dependency on your **grandle.build** app file.

      ```
        dependencies {
          implementation project(path: ':webpageloader')
        }
      ```

4. Set up your application's **manifest** file in order to use the necessary **permission**.

      ```
        <uses-permission android:name="android.permission.INTERNET" />
      ```

5. **Import the necessary classes** in the activity that you wish to use the Web Page Loader SDK.

      ```
        import com.nikolaosfragkiadakis.www.webpageloader.classes.WebPage;
      ```

      ```
        import com.nikolaosfragkiadakis.www.webpageloader.classes.WebPageLoader;
      ```

6. **Create** a new web page data set with the attributes that you wish using the library's **"WebPage"** class builder constructor.

      ```
        WebPage newWebPage = new WebPage.Builder()
                  .setUrl(MainActivityConstants.NEW_WEB_PAGE_URL)
                  .setDescription(MainActivityConstants.NEW_WEB_PAGE_DESCRIPTION)
                  .setUserId(MainActivityConstants.CURRENT_USER_ID)
                  .setUserName(MainActivityConstants.CURRENT_USER_NAME)
                  .setUserMessage(MainActivityConstants.CURRENT_USER_MESSAGE).build();
      ```

7. **Initialize** the Web Page Loader SDK using its **init()** function, pass in as arguments the calling activity's context and the previously created new WebPage instance.

      ```
        WebPageLoader.init(MainActivity.this, newWebPage);
      ```

8. **Open** the library's overlay layout panel using its **open()** function.

      ```
        WebPageLoader.open();
      ```

### Optional Feature
The Web Page Loader Android SDK will fire a **notification** by triggering the **"onWebPageLoaderOpened()"** callback method of its **"WebPageLoaderOpenedListener"** listener each time its overlay layout activity get **opened** and the **"onWebPageLoaderClosed()"** callback method of its **"WebPageLoaderClosedListener"** listener each time its overlay layout activity get **closed**.

In the case that the overlay layout get **closed**, the current's user id, the current's user name and the current's user message will be passed in as arguments in the **"onWebPageLoaderClosed()"** callback method as string values.

In order to set up a **listener** for those notifications you have to implement the **"WebPageLoaderOpenedListener"** lister and/or **"WebPageLoaderClosedListener"** listener of the Web Page Loader Android SDK.

**A. Implement the "WebPageLoaderOpenedListener" listener by overriding its "onWebPageLoaderOpened()" callback method.**

Add the necessary import statement.

      ```
        import com.nikolaosfragkiadakis.www.webpageloader.interfaces.WebPageLoaderOpenedListener;
      ```

Override the "onWebPageLoaderOpened()" callback method. Below you will find a code snippet from the "MainActivity.java" class of the Web Explorer application.

      ```
        WebPage newWebPage = new WebPage.Builder().setUrl(MainActivityConstants.NEW_WEB_PAGE_URL).setOpenedListener(new WebPageLoaderOpenedListener() {
                        @Override
                        public void onWebPageLoaderOpened() {
                            // Show an appropriate message on the UI in order to notify the user that
                            // the "Web Page Loader" SDK overlay layout has been opened.
                            Toast.makeText(MainActivity.this,
                                    MainActivityConstants.OVERLAY_LAYOUT_OPEN_TAG,
                                    Toast.LENGTH_LONG).show();
                        }
                    }).build();
        ```

**B. Implement the "WebPageLoaderClosedListener" listener by overriding its "onWebPageLoaderClosed()" callback method.**

Add the necessary import statement.

      ```
        import com.nikolaosfragkiadakis.www.webpageloader.interfaces.WebPageLoaderClosedListener;
      ```

Override the "onWebPageLoaderClosed()" callback method. Below you will find a code snippet from the "MainActivity.java" class of the Web Explorer application.

      ```
        WebPage newWebPage = new WebPage.Builder().setUrl(MainActivityConstants.NEW_WEB_PAGE_URL).setClosedListener(new WebPageLoaderClosedListener() {
                        @Override
                        public void onWebPageLoaderClosed(String userId, String userName, String userMessage) {
                            // Show an appropriate message on the UI in order to notify the user that
                            // the "Web Page Loader" SDK overlay layout has been closed.
                            String message = MainActivityConstants.OVERLAY_LAYOUT__CLOSE_TAG.toUpperCase() + "\n\n" +
                                    MainActivityConstants.USER_ID_TAG + userId + "\n" +
                                    MainActivityConstants.USER_NAME_TAG + userName + "\n" +
                                    MainActivityConstants.USER_MESSAGE_TAG + userMessage;
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                        }
                    }).build();
      ```

### Useful Tips
**A.** This repository includes an android studio project with two separate modules, one for the Web Page Loader SDK library and one for the Web Explorer app, which implements the Web Page Loader SDK library.

You can either clone this repository on your own computer and test the Web Page Loader SDK library, or integrate directly the Web Page Loader SDK library as a separate module into your own project.

**B.** Keep in mind that the builder constructor of the "WebPage" class will throw an "IllegalStateException" exception in case of an invalid url value (The url value cannot be null or a malformed url string). Use a "try-catch" block in order to catch the thrown exception.

An "IllegalStateException" exception will be thrown also from the "WebPageLoader.open()" method if the library has not been initialized yet during the invoking time. Use a "try-catch" block again in order to catch the thrown exception.

### Resources
The links below are considered as useful and strongly recommended resources.

1. https://developer.android.com/reference/android/Manifest.permission.html#INTERNET

### Contact
For any issues that may occur or for more information requests or even for your comments and feedback please contact the author directly using the following email address: info@nikolaosfragkiadakis.com

### License
The Web Page Loader Android SDK is an open source project and it operates under the MIT License.

### Copyright
2019 © Nikolaos Fragkiadakis.

# About the Author
My name is Nikolaos Fragkiadakis and I am Software Developer and IT Instructor. Read more about me, view sample of my work and download my CV while browsing on my personal website www.nikolaosfragkiadakis.com.

# Credits
I owe a huge "THANK YOU" to my friend Dimitris-Sotiris Tsolis for his mental support, his thoughts and his feedback.
