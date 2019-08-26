import control.UrlProcessor;
import model.ResponseDetails;
import model.ValidResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class UrlProcessorTest {
    String pathname;
    File file;
    List<String> urls;

    @Before
    public void setup() throws IOException {
        pathname = "testfile.txt";
        file = new File(pathname);

        if(!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        urls = new ArrayList<String>();
        urls.add("https://www.bbc.co.uk/");
        urls.add("invalid://url");
        urls.add("https://www.amazon.co.uk/");

        for(String url : urls) {
            bw.write(url + "\n");
        }

        bw.close();
    }

    @After
    public void cleanup() {
        file.delete();
    }

    @Test
    public void testUrlsReadFromFileCorrectly() throws IOException {
        List<String> urlsFromFile = UrlProcessor.getUrlsFromFile(pathname);

        assertEquals(urls, urlsFromFile);
    }
}