package utils.client.istio.model;

import utils.client.enums.IstioProto;

import java.util.Map;

public class Gw {
    private String namespace;
    private String name;
    private Map<String,String> labels;
    private String host;
    private String portName;
    private Integer port;
    private IstioProto proto;

    public String getNamespace() {
        return namespace;
    }

    public Gw setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public String getName() {
        return name;
    }

    public Gw setName(String name) {
        this.name = name;
        return this;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public Gw setLabels(Map<String, String> labels) {
        this.labels = labels;
        return this;
    }

    public String getHost() {
        return host;
    }

    public Gw setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPortName() {
        return portName;
    }

    public Gw setPortName(String portName) {
        this.portName = portName;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public Gw setPort(Integer port) {
        this.port = port;
        return this;
    }

    public IstioProto getProto() {
        return proto;
    }

    public String getProtoStr() {
        return proto.getName();
    }

    public Gw setProto(IstioProto proto) {
        this.proto = proto;
        return this;
    }
}
