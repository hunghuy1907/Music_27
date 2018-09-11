package com.framgia.music_27.utils;

import java.util.concurrent.TimeUnit;

public class StringUtils {
    public static String convertMinisecond(long milisec) {
        return String.format("%d : %02d", TimeUnit.MILLISECONDS.toMinutes(milisec),
                TimeUnit.MILLISECONDS.toSeconds(milisec) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(milisec)));
    }
}
