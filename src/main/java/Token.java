
class Token {
    private String sourceIP;
    private String destinationIP;
    private String message;
    private boolean isFree;
    private boolean hasArrived;

    public Token() {
        this.sourceIP = "";
        this.destinationIP = "";
        this.message = "";
        this.isFree = true;
        this.hasArrived = false;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public String getDestinationIP() {
        return destinationIP;
    }

    public void setDestinationIP(String destinationIP) {
        this.destinationIP = destinationIP;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public boolean hasArrived() {
        return hasArrived;
    }

    public void setHasArrived(boolean hasArrived) {
        this.hasArrived = hasArrived;
    }

    public void reset() {
        this.sourceIP = "";
        this.destinationIP = "";
        this.message = "";
        this.isFree = true;
        this.hasArrived = false;
    }
}