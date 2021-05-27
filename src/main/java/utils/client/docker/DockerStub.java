package utils.client.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.PushImageResultCallback;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;


public class DockerStub {
    private final DockerClient client ;
    public DockerStub() {
        client  = DockerClientBuilder.getInstance(DefaultDockerClientConfig.createDefaultConfigBuilder().build()).build();
    }
    public Info info(){
        return client.infoCmd().exec();
    }
    public String build(File dockerfile, String image){
        return client.buildImageCmd()
                .withDockerfile(dockerfile)
                .withPull(true)
                .withNoCache(true)
                .withTags(new HashSet<>(Collections.singletonList(image)))
                .exec(new BuildImageResultCallback())
                .awaitImageId();
    }
    public void tag(String image, String repo, String tag) {
        client.tagImageCmd(image, repo, tag).exec();
    }
    public void push(String name, String tag) {
        try {
            client.pushImageCmd(name).withTag(tag).exec(new PushImageResultCallback()).awaitCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //async call
    public void pull(String image, String tag){
        client.pullImageCmd(image)
                .withTag(tag)
                .exec(new PullImageResultCallback());
    }
    //sync call
    public void exists(String name, String tag){
//        client.inspectImageCmd();
    }
}
