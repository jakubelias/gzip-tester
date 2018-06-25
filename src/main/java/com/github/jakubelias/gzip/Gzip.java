package com.github.jakubelias.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;

public class Gzip {

  public static byte[] decompress(final byte[] compressed) throws IOException {
    ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
    GZIPInputStream gis = new GZIPInputStream(bis);
    return IOUtils.toByteArray(gis);
  }



  public static byte[] compress(byte[] data) throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
    GZIPOutputStream gzip = new GZIPOutputStream(bos);
    gzip.write(data);
    gzip.close();
    byte[] compressed = bos.toByteArray();
    bos.close();
    return compressed;
  }

}
