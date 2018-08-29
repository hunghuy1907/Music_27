package com.framgia.music_27.utils;

public class Constants {

    public class SoundClound{
        public static final String BASE_URL = "https://api-v2.soundcloud.com/";
        public static final String PARAM_QUERY_KIND = "charts?kind=top";
        public static final String PARAM_GENRE = "&genre=soundcloud";
        public static final String PARAM_CLIENT_ID = "?client_id=";
        public static final String PARAM_TYPE = "%3Agenres%3Atype";
        public static final String PARAM_LIMIT = "&limit=";
    }

    public class JsonTrackKey {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String URI = "url";
        public static final String ARTWORK_URL = "artwork_url";
        public static final String DURATION = "duration";
        public static final String GENRE = "genre";
        public static final String DOWNLOADABLE = "downloadable";
        public static final String DOWNLOAD_URL = "download_url";
        public static final String USER = "user";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
        public static final String REQUEST_METHOD_GET = "GET";
    }

    public class JsonKey {
        public static final String GENRE = "genre";
        public static final String RESULT = "result";
        public static final String ID_GENRE = "id_genre";
        public static final String NAME_GENRE = "name_genre";
    }

    public class JsonUserKey {
        public static final String USER_ID = "user_id";
        public static final String USER_FULLNAME = "user_fullname";
        public static final String USER_NAME = "user_name";
    }
}
