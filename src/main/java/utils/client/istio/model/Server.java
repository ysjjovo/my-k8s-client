package utils.client.istio.model;

import utils.client.enums.IstioProto;

public class Server {
    private String name;
    private IstioProto proto;
    private String host;
    private Integer port;

    public String getName() {
        return name;
    }

    public Server setName(String name) {
        this.name = name;
        return this;
    }

    public IstioProto getProto() {
        return proto;
    }

    public Server setProto(IstioProto proto) {
        this.proto = proto;
        return this;
    }

    public String getHost() {
        return host;
    }

    public Server setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public Server setPort(Integer port) {
        this.port = port;
        return this;
    }
}
