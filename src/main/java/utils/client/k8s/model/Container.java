package utils.client.k8s.model;

public class Container {
    private String name;
    private String image;
    private Integer port;
    private ImagePullPolicy pullPolicy;

    public String getName() {
        return name;
    }

    public Container setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Container setImage(String image) {
        this.image = image;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public Container setPort(Integer port) {
        this.port = port;
        return this;
    }

    public ImagePullPolicy getPullPolicy() {
        return pullPolicy;
    }
    public String getPullPolicyStr() {
        return pullPolicy.name();
    }
    public Container setPullPolicy(ImagePullPolicy pullPolicy) {
        this.pullPolicy = pullPolicy;
        return this;
    }
}
