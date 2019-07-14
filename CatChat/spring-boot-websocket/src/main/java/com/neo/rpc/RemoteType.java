package com.neo.rpc;


public enum RemoteType {
    HESSIAN("HESSIAN"), HTTP("HTTP"),TCP("TCP"),UDP("UDP");

    private String name;
    RemoteType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String isRemoteType(RemoteType type) {
        switch (type) {
            case HESSIAN: return RemoteClientFactory.HESSIAN;
            case HTTP   : return RemoteClientFactory.HTTP;
            case TCP    : return RemoteClientFactory.TCP;
            case UDP    : return RemoteClientFactory.UDP;
            default     : return null;
        }
    }

    public static String getCleanRemoteType(RemoteType type) {
        switch (type) {
            case HESSIAN: return HESSIAN.getName();
            case HTTP   : return HTTP.getName();
            case TCP    : return TCP.getName();
            case UDP    : return UDP.getName();
            default     : return null;
        }

    }

    public static RemoteType getRemoteType(String type) {
        switch (type) {
            case RemoteClientFactory.HESSIAN : return HESSIAN;
            case RemoteClientFactory.HTTP    : return HTTP;
            case RemoteClientFactory.TCP     : return TCP;
            case RemoteClientFactory.UDP     : return UDP;
            default                          : return null;
        }
    }
}
