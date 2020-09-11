package one.digitalinnovation.java11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ClientHttpExample {

    static ExecutorService executorService = Executors.newFixedThreadPool(6, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);

            System.out.println("New thread created: " + (thread.isDaemon() ? "daemon" : "")
                    + "Thread group: " + thread.getThreadGroup());
            // "daemon" is a process that doesn't prevent JVM to be finalized, garbage collector is an example
            // of daemon process

            return thread;
        }
    });

    public static void main(String[] args) throws Exception {
        // connectWithUrlConnection();
        // connectWithHttpClient();
        connectAkamaiHttp1Client();
    }

    // Using Http Client with HTTP/1.1
    public static void connectAkamaiHttp1Client() throws Exception {
        System.out.println("Running HTTP/1.1 example...");
        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .proxy(ProxySelector.getDefault())
                    .build();

            long start = System.currentTimeMillis();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://http2.akamai.com/demo/h2_demo_frame.html"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status code: " + httpResponse.statusCode());
            System.out.println("Headers: " + httpResponse.headers());

            String responseBody = httpResponse.body();

            System.out.println(responseBody);

            List<Future<?>> futures = new ArrayList<>();

            responseBody.lines().filter(line -> line.trim().startsWith("<img height"))
                    .map(line -> line.substring(line.indexOf("src='") + 5, line.indexOf("'/>")))
                    .forEach(image -> {
                        Future<?> imgFuture = executorService.submit(() -> {
                            HttpRequest imgRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("http://http2.akamai.com" + image))
                                    .build();

                            try {
                                HttpResponse<String> imgResponse = httpClient
                                        .send(imgRequest, HttpResponse.BodyHandlers.ofString());
                                System.out.println("Loaded image: " + image + ", status code: " + imgResponse.statusCode());
                            } catch (IOException | InterruptedException e) {
                                System.out.println("Error during image request of image " + image);
                            }
                        });
                        futures.add(imgFuture);
                        System.out.println("Submitted a future for image: " + image);
                    });

            futures.forEach(f -> {
                try {
                    f.get();
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Error while loading image future");
                }
            });

            long end = System.currentTimeMillis();
            System.out.println("Total loading time: " + (end - start) + " ms");

        } finally {
            executorService.shutdown();
        }
    }

    // Using Http Client with HTTP/2
    public static void connectAkamaiHttp2Client() throws Exception {
        System.out.println("Running HTTP/2 example...");
        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .proxy(ProxySelector.getDefault())
                    .build();

            long start = System.currentTimeMillis();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://http2.akamai.com/demo/h2_demo_frame.html"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status code: " + httpResponse.statusCode());
            System.out.println("Headers: " + httpResponse.headers());

            String responseBody = httpResponse.body();

            System.out.println(responseBody);

            List<Future<?>> futures = new ArrayList<>();

            responseBody.lines().filter(line -> line.trim().startsWith("<img height"))
                    .map(line -> line.substring(line.indexOf("src='") + 5, line.indexOf("'/>")))
                    .forEach(image -> {
                        Future<?> imgFuture = executorService.submit(() -> {
                            HttpRequest imgRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("http://http2.akamai.com" + image))
                                    .build();

                            try {
                                HttpResponse<String> imgResponse = httpClient
                                        .send(imgRequest, HttpResponse.BodyHandlers.ofString());
                                System.out.println("Loaded image: " + image + ", status code: " + imgResponse.statusCode());
                            } catch (IOException | InterruptedException e) {
                                System.out.println("Error during image request of image " + image);
                            }
                        });
                        futures.add(imgFuture);
                        System.out.println("Submitted a future for image: " + image);
                    });

            futures.forEach(f -> {
                try {
                    f.get();
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Error while loading image future");
                }
            });

            long end = System.currentTimeMillis();
            System.out.println("Total loading time: " + (end - start) + " ms");

        } finally {
            executorService.shutdown();
        }
    }

    // Using URL Connection - old
    private static void connectWithUrlConnection() {
        try {
            var url = new URL("https://docs.oracle.com/javase/10/language/");
            var urlConnection = url.openConnection();

            try (var bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                System.out.println(bufferedReader.lines().collect(Collectors.joining())
                        .replaceAll(">", ">\n"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Using Client Http - new Java 11
    private static void connectWithHttpClient() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder().GET()
                .uri(URI.create("https://docs.oracle.com/javase/10/language/"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + httpResponse.statusCode());
        System.out.println("Headers: " + httpResponse.headers());
        System.out.println(httpResponse.body());
    }
}
