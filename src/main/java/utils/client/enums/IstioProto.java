package utils.client.enums;

public enum IstioProto {
    HTTP("HTTP"),
    HTTP2("HTTP2"),
    HTTPS("HTTPS"),
    TCP("TCP"),
    TLS("TLS"),
    GRPC("GRPC"),
    GRPC_WEB("gRPC-Web"),
    Mongo("Mongo"),
    MySQL("MySQL"),
    Redis("Redis"),
    UDP("UDP"),
    ;

    IstioProto(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
}
