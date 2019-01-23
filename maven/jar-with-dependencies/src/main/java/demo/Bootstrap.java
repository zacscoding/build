package demo;

import com.google.gson.Gson;
import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author zacconding
 * @Date 2019-01-23
 * @GitHub : https://github.com/zacscoding
 */
public class Bootstrap {

    private static Thread printer;

    private final CountDownLatch keepAliveLatch = new CountDownLatch(1);
    private final Thread keepAliveThread;

    public static void main(String[] args) {
        loadArchives();

        printer = new Thread(() -> {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Gson gson = new Gson();

                while (!Thread.currentThread().isInterrupted()) {
                    Map<String, Object> display = new HashMap<>();

                    display.put("UUID", UUID.randomUUID().toString());
                    display.put("TIMESTAMP", sdf.format(new Date()));
                    System.out.println("Running....\t" + gson.toJson(display));

                    TimeUnit.SECONDS.sleep(3L);
                }
            } catch (InterruptedException e) {
            }
        });
        printer.setDaemon(true);
        printer.start();

        new Bootstrap().start();
    }

    /**
     * Adding lib/*.jar to class path for maven dependency plugin
     */
    private static void loadArchives() {
        try {
            File jarFile = new File(Bootstrap.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            if (jarFile.isFile()) {
                System.out.println("Running in jar..");
                final JarFile jar = new JarFile(jarFile);
                final Enumeration<JarEntry> entries = jar.entries();
                List<URL> jarFileUrls = new ArrayList<>();

                String file = "jar:" + jarFile.toURI() + "!/";
                file = file.replace("file:////", "file://");

                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    final String name = entry.getName();
                    if (name.startsWith("BOOT-INF/lib") && name.endsWith(".jar")) {
                        URL jarUrl = new URL(file + name);
                        final JarURLConnection connection = (JarURLConnection) jarUrl.openConnection();
                        final URL url = connection.getJarFileURL();

                        System.out.println(
                            "Added jar : " + name + " >> " + (url == null ? "null" : url.getFile())
                        );
                        jarFileUrls.add(url);
                    }
                }

                if (!jarFileUrls.isEmpty()) {
                    System.out.println("Adding class loader");
                    Thread.currentThread().setContextClassLoader(new URLClassLoader(
                        jarFileUrls.toArray(new URL[jarFileUrls.size()]), Bootstrap.class.getClassLoader()
                    ));

                    Class<?> gsonClass = Thread.currentThread().getContextClassLoader()
                        .loadClass("com.google.gson.Gson");
                    System.out.println("## Check gson class :: " + gsonClass);
                }
                jar.close();
            } else {
                System.out.println("Running in IDE >> Skip adding lib/*.jar to class path");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.err.println("Terminate application");
            System.exit(-1);
        }
    }

    Bootstrap() {
        keepAliveThread = new Thread(() -> {
            try {
                System.out.println("Before keepAliveThread..");
                keepAliveLatch.await();
                System.out.println("Before keepAliveThread..");
            } catch (InterruptedException e) {
            }
        });
        keepAliveThread.setDaemon(false);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> keepAliveLatch.countDown()));
    }

    /**
     * Start this application
     */
    public void start() {
        try {
            keepAliveThread.start();
            this.keepAliveThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}