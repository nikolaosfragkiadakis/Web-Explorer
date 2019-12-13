package com.nikolaosfragkiadakis.www.webexplorer.ui;

/*
* MIT License
*
* Copyright 2019 © Nikolaos Fragkiadakis / www.nikolaosfragkiadakis.com
*
* Permission is hereby granted, free of charge, to any person obtaining a copy of this software
* and associated documentation files (the "Software"), to deal in the Software without restriction,
* including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
* and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
* subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
* INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
* PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
* FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
* ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nikolaosfragkiadakis.www.webexplorer.utilities.Constants.MainActivityConstants;
import com.nikolaosfragkiadakis.www.webexplorer.R;

// I. Adding the necessary "import" statements of the "Web Page Loader" SDK.
import com.nikolaosfragkiadakis.www.webpageloader.classes.WebPage;
import com.nikolaosfragkiadakis.www.webpageloader.classes.WebPageLoader;
import com.nikolaosfragkiadakis.www.webpageloader.interfaces.WebPageLoaderClosedListener;
import com.nikolaosfragkiadakis.www.webpageloader.interfaces.WebPageLoaderOpenedListener;

/**
 * {@link MainActivity} is the main activity of the "Web Explorer" app and the place
 * where the implemented "Web Page Loader" SDK should get initialized and invoked.
 *
 * "Web Explorer" is a small app that its purpose is to help in testing
 * the "Web Page Loader" SDK.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        // II. Initializing the "Web Page Loader" SDK.
        initializeWebPageLoader();

        // Set an "OnClickListener" for the "Explore" button, in order to provide a way
        // to the "Web Page Loader" SDK to get initialized by the current user.
        Button webViewTriggerButton = (Button) findViewById(R.id.web_view_trigger_button);
        webViewTriggerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view) {
                // III. Opening the overlay layout of the "Web Page Loader" SDK.
                try {
                    WebPageLoader.open();
                } catch (IllegalStateException exception) {
                    // In case that the "WebPageLoader.open();" method invoked before of the initialization of the
                    // "Web Page Loader" SDK, an "IllegalStateException" exception will be thrown.
                    // Catch the "exception" and show an appropriate message in the UI.
                    Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * The "initializeWebPageLoader" is a helper method which contains the
     * initialization steps for the "Web Page Loader" SDK.
     */
    public void initializeWebPageLoader() {
        // Try to get a new "WebPage" class instance of the new requested web page's data set
        // by using it's builder pattern.
        // If an invalid url value or a null url value get passed in the "setUrl()" method,
        // an "IllegalStateException" exception will be thrown.
        // If a null value get passed in another method of the builder, then the
        // default values will be used.
        try {
            // In this case, use some constant values in order to test the "Web Page Loader" SDK.
            // 1. Pass "NEW_WEB_PAGE_URL" constant value as "Param 1" for "newWebPageUrl".
            // 2. Pass "NEW_WEB_PAGE_DESCRIPTION" constant value as "Param 2" for "newWebPageDescription".
            // 3. Pass "CURRENT_USER_ID" constant value as "Param 3" for "newUserId".
            // 4. Pass "CURRENT_USER_NAME" constant value as "Param 4" for "newUserName".
            // 5. Pass "CURRENT_USER_MESSAGE" constant value as "Param 5" for "newUserMessage".
            WebPage newWebPage = new WebPage.Builder()
                    .setUrl(MainActivityConstants.NEW_WEB_PAGE_URL)
                    .setDescription(MainActivityConstants.NEW_WEB_PAGE_DESCRIPTION)
                    .setUserId(MainActivityConstants.CURRENT_USER_ID)
                    .setUserName(MainActivityConstants.CURRENT_USER_NAME)
                    .setUserMessage(MainActivityConstants.CURRENT_USER_MESSAGE)
                    .setOpenedListener(new WebPageLoaderOpenedListener() {
                        @Override
                        public void onWebPageLoaderOpened() {
                            // Show an appropriate message on the UI in order to notify the user that
                            // the "Web Page Loader" SDK overlay layout has been opened.
                            Toast.makeText(MainActivity.this,
                                    MainActivityConstants.OVERLAY_LAYOUT_OPEN_TAG,
                                    Toast.LENGTH_LONG).show();
                        }
                    })
                    .setClosedListener(new WebPageLoaderClosedListener() {
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
                    })
                    .build();

            // In order to initializeOverlayLayout the "Web Page Loader" SDK pass the context of the
            // calling activity plus the new "WebPage" class instance as arguments
            // in the "init()" function.
            WebPageLoader.init(MainActivity.this, newWebPage);

        } catch (IllegalStateException exception){
            // An invalid url value or a null url value have been passed in the "setUrl()" method,
            // thus an "IllegalStateException" exception had thrown.
            // Catch the "exception" and show an appropriate message in the UI.
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
