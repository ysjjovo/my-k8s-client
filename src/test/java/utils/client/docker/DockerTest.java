package utils.client.docker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DockerTest {
    private static DockerStub docker;
    private static String registry = "docker.io";
    private static String namespace = "ysjjovo";
    private String name = "hello-docker";
    private String ver = "1.0";

    @Test
    void getInfoTest(){
        assertEquals("x86_64", docker.info().getArchitecture());
    }
    @Test
    void buildTest(){
        assertTrue(docker.build(new File("src/test/resources/Dockerfile"), "example.com/hello-docker:1.0") != null);
    }
    @Test
    void tagTest(){
        docker.tag(registry + "/" + name + ":" + ver, namespace + "/" + name, ver);
    }
    @Test
    void pushTest() {
        docker.push(namespace + "/" + name, ver);
    }
    @Test
    void pullTest(){
        docker.pull("nginx", "1.10.0");
    }
    @BeforeAll
    static void setUp(){
        System.setProperty("registry.url", registry);
        System.setProperty("registry.username", namespace);
        System.setProperty("registry.password", "xxx");
        docker = new DockerStub();
    }
    @AfterAll
    static void destroy(){
    }
}
