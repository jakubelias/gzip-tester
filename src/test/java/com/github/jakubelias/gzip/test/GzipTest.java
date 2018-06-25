package com.github.jakubelias.gzip.test;

import com.github.jakubelias.gzip.Gzip;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class GzipTest {


  @Test
  public void compressDecompressTest() throws IOException {
    Resource cpResource = new ClassPathResource("HVDCLinkDocument_Constraints.xml");
    byte[] compressed = Gzip.compress(IOUtils.toByteArray(cpResource.getInputStream()));
    byte[] decompressed = Gzip.decompress(compressed);
    String deCompressedString = new String(decompressed, "UTF-8");
    Assert.assertEquals(IOUtils.toString(cpResource.getInputStream(), "UTF-8"), deCompressedString);
  }

  @Test
  public void compressTest() throws IOException {
    Resource cpResource = new ClassPathResource("HVDCLinkDocument_Constraints.xml");
    System.out.println("Testing compression of file of length (bytes): " + cpResource.getFile().length());
    byte[] plain = IOUtils.toByteArray(cpResource.getInputStream());

    List<Long> compressTimes = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      long start = System.nanoTime();
      Gzip.compress(plain);
      long end = System.nanoTime();
      long duration = end - start;
      compressTimes.add(duration);
    }

    for (Long compressTime : compressTimes) {
      System.out.println("Compression time: " + TimeUnit.NANOSECONDS.toMillis(compressTime) + "miliseconds, " + TimeUnit.NANOSECONDS.toMicros(compressTime) + "+ microseconds:");
    }

  }

  @Test
  public void decompressTest() throws IOException {
    Resource cpResource = new ClassPathResource("HVDCLinkDocument_Constraints.xml");
    System.out.println("Testing compression of file of length (bytes): " + cpResource.getFile().length());
    byte[] plain = IOUtils.toByteArray(cpResource.getInputStream());
    byte[] compressed = Gzip.compress(plain);

    List<Long> decompressTimes = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      long start = System.nanoTime();
      Gzip.decompress(compressed);
      long end = System.nanoTime();
      long duration = end - start;
      decompressTimes.add(duration);
    }

    for (Long decompressTime : decompressTimes) {
      System.out.println("Decompression time: " + TimeUnit.NANOSECONDS.toMillis(decompressTime) + "miliseconds, " + TimeUnit.NANOSECONDS.toMicros(decompressTime) + " microseconds:");
    }

  }


}
