package com.example.android0128.trainingmvp.utils;

import com.example.android0128.trainingmvp.presentation.models.Character;
import com.example.android0128.trainingmvp.presentation.models.Comic;
import com.example.android0128.trainingmvp.presentation.models.Event;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by android0128 on 2/3/17.
 */

public class Utils {

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    //Get Lists
    public static String getListTextCharacter(List<Character.Options.Item> listItems){
        String result = "";
        for (Character.Options.Item item : listItems){
            result += "-" + item.getName() + "\n" ;
        }
        return result;
    }

    public static String getListTextComic(List<Comic.Options.Item> listItems){
        String result = "";
        for (Comic.Options.Item item : listItems){
            result += "-" + item.getName();
            if (item.getRole() != null) {
                result += "(" + item.getRole() + ")";
            }
            result += "\n";
        }
        return result;
    }

    public static String getListTextEvent(List<Event.Options.Item> listItems){
        String result = "";
        for (Event.Options.Item item : listItems){
            result += "-" + item.getName();
            if (item.getRole() != null) {
                result += "(" + item.getRole() + ")";
            }
            result += "\n";
        }
        return result;
    }
    //END Get Lists

    //Get Image url
    public static String getCharacterImagePath(Character model){
        return model.getThumbnail().getPath() + "." + model.getThumbnail().getExtension();
    }
    public static String getComicImagePath(Comic model){
        return model.getThumbnail().getPath() + "." + model.getThumbnail().getExtension();
    }
    public static String getEventImagePath(Event model){
        return model.getThumbnail().getPath() + "." + model.getThumbnail().getExtension();
    }
    //END Get Image url

}
