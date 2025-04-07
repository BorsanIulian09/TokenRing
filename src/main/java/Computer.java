class Computer {
    private String ipAddress;
    private String buffer;

    public Computer(String ipAddress) {
        this.ipAddress = ipAddress;
        this.buffer = null;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String message) {
        if (buffer == null) {
            this.buffer = message;
        } else {
            this.buffer += ";" + message;
        }
    }

    @Override
    public String toString() {
        return ipAddress + " -> " + (buffer == null ? "null" : buffer);
    }
}