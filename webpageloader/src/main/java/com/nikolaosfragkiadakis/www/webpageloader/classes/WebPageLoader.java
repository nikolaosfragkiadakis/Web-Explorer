package com.nikolaosfragkiadakis.www.webpageloader.classes;

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

import android.content.Context;
import android.content.Intent;

import com.nikolaosfragkiadakis.www.webpageloader.utilities.Constants.WebPageLoaderConstants;

/**
 * The {@link WebPageLoader} class is the container of the "Web Page Loader" SDK main functions.
 */
public class WebPageLoader {
    /**
     * The "init" function is used in order to the "Web Page Loader" SDK get initialized.
     *
     * @param context     represents the context of the calling activity of the client app that
     *                    imports the "Web Page Loader" SDK.
     * @param newWebPage  represents the new web page's data set that should get loaded.
     */
    public static void init(Context context, WebPage newWebPage) {
        // Initialize the Singleton instance.
        WebPageLoaderManager.getInstance().setContext(context);
        WebPageLoaderManager.getInstance().setWebPage(newWebPage);
    }

    /**
     * The "open" function is used in order to the "Web Page Loader" SDK overlay layout get opened.
     */
    public static void open() throws IllegalStateException {
        // Get the context reference of the current calling activity of the client app.
        Context context = WebPageLoaderManager.getInstance().getContext();

        // Figure out if the "Web Page Loader" SDK has been initialized or not.
        if (context == null) {
            // In case that the "Web Page Loader" SDK has not been initialized yet,
            // throw an "IllegalStateException" exception.
            throw new IllegalStateException(WebPageLoaderConstants.INITIALIZATION_ERROR_MESSAGE);
        }

        // Fire an intent in order to show the requested web page in an overlay layout.
        Intent intent = new Intent(context, OverlayLayout.class);
        context.startActivity(intent);
    }
}
