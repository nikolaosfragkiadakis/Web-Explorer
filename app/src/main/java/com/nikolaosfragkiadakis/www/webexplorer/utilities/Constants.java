package com.nikolaosfragkiadakis.www.webexplorer.utilities;

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

import com.nikolaosfragkiadakis.www.webexplorer.ui.MainActivity;

/**
 * The {@link Constants} class is a container of all the constant values that are used in the
 * "Web Explorer" application.
 */
public class Constants {
    ////////////////////////////////////////////////////
    //////////////////// Activities ////////////////////
    ////////////////////////////////////////////////////

    /**
     * The constant values of the {@link MainActivity} class.
     */
    public class MainActivityConstants {
        // The current web page's Url. Also represents "Param 1".
        public static final String NEW_WEB_PAGE_URL = "https://www.nikolaosfragkiadakis.com";

        // The current web page's Description. Also represents "Param 2".
        public static final String NEW_WEB_PAGE_DESCRIPTION = "My personal website.";

        // The client's app current user Id. Also represents "Param 3".
        public static final String CURRENT_USER_ID = "001";

        // The client's app current user Name. Also represents "Param 4".
        public static final String CURRENT_USER_NAME = "Nikolaos Fragkiadakis";

        // The client's app current user Message. Also represents "Param 5".
        public static final String CURRENT_USER_MESSAGE = "This is my very first Android SDK!";

        // The prefix of the overlay layout opening toast message.
        public static final String OVERLAY_LAYOUT_OPEN_TAG = "The web page has been loaded!";

        // The prefix of the overlay layout closing toast message.
        public static final String OVERLAY_LAYOUT__CLOSE_TAG = "Current user's details";

        // The user's Id tag of the overlay layout closing toast message.
        public static final String USER_ID_TAG = "Id: ";

        // The user's Name tag of the overlay layout closing toast message.
        public static final String USER_NAME_TAG = "Name: ";

        // The user's Message tag of the overlay layout closing toast message.
        public static final String USER_MESSAGE_TAG = "Message: ";
    }
}
