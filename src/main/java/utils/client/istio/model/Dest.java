package utils.client.istio.model;

public class Dest {
    private Integer weight;
    private String subset;
    private String innerHost;
    private Integer port;

    public Integer getWeight() {
        return weight;
    }

    public Dest setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public String getSubset() {
        return subset;
    }

    public Dest setSubset(String subset) {
        this.subset = subset;
        return this;
    }

    public String getInnerHost() {
        return innerHost;
    }

    public Dest setInnerHost(String innerHost) {
        this.innerHost = innerHost;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public Dest setPort(Integer port) {
        this.port = port;
        return this;
    }
}
