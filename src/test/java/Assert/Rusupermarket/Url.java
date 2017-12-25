package Assert.Rusupermarket;


import java.util.LinkedList;
import java.util.Queue;

public class Url {
    String url;
    boolean status = true;
    long timeUp;
    long timeDown;
    int dateLastReport = 0;
    int timeMessage;
    boolean messageIsSent = false;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

    public long getTimeDown() {
        return timeDown;
    }

    public long getTimeUp() {
        return timeUp;
    }

    public void setTimeDown(long timeDown) {
        this.timeDown = timeDown;
    }

    public void setTimeUp(long timeUp) {
        this.timeUp = timeUp;
    }

    public void setTimeMessage(int timeMessage) {
        this.timeMessage = timeMessage;
    }

    public int getTimeMessage() {
        return timeMessage;
    }

    public void setMessageIsSent(boolean messageIsSent) {
        this.messageIsSent = messageIsSent;
    }

}
