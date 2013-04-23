package com.nevilon.firefly.utils;

import org.codehaus.groovy.grails.io.support.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: hudvin
 * Date: 4/23/13
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {

    public static String calculateMd5Hash(InputStream is) {
        try {
            String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
            return md5;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }







}
