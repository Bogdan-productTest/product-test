package Assert.Rusupermarket;

public class UrlForCheckCode {
    String url;
    String provider;
    boolean sendMessage = false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setSendMessage(boolean sendMessage) {
        this.sendMessage = sendMessage;
    }
}
