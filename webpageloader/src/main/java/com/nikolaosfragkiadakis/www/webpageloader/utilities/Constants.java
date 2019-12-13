package com.nikolaosfragkiadakis.www.webpageloader.utilities;

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

import com.nikolaosfragkiadakis.www.webpageloader.classes.OverlayLayout;
import com.nikolaosfragkiadakis.www.webpageloader.classes.WebPage;
import com.nikolaosfragkiadakis.www.webpageloader.classes.WebPageLoader;

/**
 * The {@link Constants} class is a container of all the constant values that are used in the
 * "Web Page Loader" SDK.
 */
public class Constants {
    ////////////////////////////////////////////////////
    ///////////////////// Classes //////////////////////
    ////////////////////////////////////////////////////

    /**
     * The constant values of the {@link OverlayLayout} class.
     */
    public class OverlayLayoutConstants {
        // The text for the "No internet connection" toast message.
        public static final String NO_INTERNET_CONNECTION_MESSAGE = "No internet connection!\nPlease try again later.";

        // The text for the "Error" toast message.
        public static final String ERROR_MESSAGE =
                "Unable to load the requested web page url.\nAn unexpected error has been occurred.";
    }

    /**
     * The constant values of the {@link WebPage} class.
     */
    public class WebPageConstants {
        // The default web page's description text value.
        public static final String DEFAULT_DESCRIPTION_TEXT = "No description provided.";

        // The default calling application's user ID text value.
        public static final String DEFAULT_USER_ID_TEXT = "000";

        // The default calling application's user name text value.
        public static final String DEFAULT_USER_NAME_TEXT = "User name";

        // The default calling application's user message text value.
        public static final String DEFAULT_USER_MESSAGE_TEXT = "User message";

        // The invalid url "IllegalStateException" exception message text value.
        public static final String INVALID_URL_VALUE_EXCEPTION_MESSAGE =
                "Unable to load the requested web page.\nPlease, provide a valid url.";
    }

    /**
     * The constant values of the {@link WebPageLoader} class.
     */
    public class WebPageLoaderConstants {
        // The initialization error "IllegalStateException" exception message text value.
        public static final String INITIALIZATION_ERROR_MESSAGE = "The Web Page Loader has not been initialized yet!";
    }
}
