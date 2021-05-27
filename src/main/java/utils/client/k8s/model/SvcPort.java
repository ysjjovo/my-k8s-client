package utils.client.k8s.model;

import utils.client.enums.Proto;

public class SvcPort {
    private String name;
    private Proto protocol;
    private Integer port;
    private Integer targetPort;
    private Integer nodePort;

    public String getName() {
        return name;
    }

    public SvcPort setName(String name) {
        this.name = name;
        return this;
    }

    public Proto getProtocol() {
        return protocol;
    }

    public String getProtocolStr() {
        return protocol.name();
    }

    public SvcPort setProtocol(Proto protocol) {
        this.protocol = protocol;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public SvcPort setPort(Integer port) {
        this.port = port;
        return this;
    }

    public Integer getTargetPort() {
        return targetPort;
    }

    public SvcPort setTargetPort(Integer targetPort) {
        this.targetPort = targetPort;
        return this;
    }

    public Integer getNodePort() {
        return nodePort;
    }

    public SvcPort setNodePort(Integer nodePort) {
        this.nodePort = nodePort;
        return this;
    }
}
