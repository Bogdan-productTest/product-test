package Assert.CheckOffersBot;


import java.math.BigDecimal;
import java.math.RoundingMode;

import static Assert.CheckOffersBot.Methods.average;

public class Url {
    String url;
    boolean status = true;
    boolean sendMessageToChat;
    long timeUp;
    long timeDown;
    long timeMissOffers;
    long timeShowUpOffers;
    int dateLastReport = 0;
    int timeMessage;
    boolean messageIsSent = false;
    String selectorOffers;
    String selectorGeo;
    double averageOffers = 0;
    double averagePage = 0;
    double averageGeo = 0;
    int amountOfNumbers;
    int numCheckPass = 0;
    int numCheckFail = 0;
    String nadaviIdChild;
    String mobiguruId;
    String nadaviIdParent;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getUrl() {
        return this.url;
    }

    public long getTimeDown() {
        return this.timeDown;
    }

    public long getTimeUp() {
        return this.timeUp;
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
        return this.timeMessage;
    }

    public void setMessageIsSent(boolean messageIsSent) {
        this.messageIsSent = messageIsSent;
    }

    public String getSelectorOffers() {
        return this.selectorOffers;
    }

    public void setSelectorOffers(String selectorOffers) {
        this.selectorOffers = selectorOffers;
    }

    public void toAverage(double lastOffers, double lastPage, double lastGeo) {
        this.averageOffers = new BigDecimal(average (this.averageOffers, this.amountOfNumbers, lastOffers)).setScale(2, RoundingMode.CEILING).doubleValue();
        this.averagePage = new BigDecimal(average(this.averagePage, this.amountOfNumbers, lastPage)).setScale(2, RoundingMode.CEILING).doubleValue();
        this.averageGeo = new BigDecimal(average(this.averageGeo, this.amountOfNumbers, lastGeo)).setScale(2, RoundingMode.CEILING).doubleValue();
        this.amountOfNumbers++;
    }

    public int getNumCheckPass() {
        return this.numCheckPass;
    }

    public void setNumCheckPass(int numCheckPass) {
        this.numCheckPass = numCheckPass;
    }

    public int getNumCheckFail() {
        return numCheckFail;
    }

    public void setNumCheckFail(int numCheckFail) {
        this.numCheckFail = numCheckFail;
    }

    public long getTimeMissOffers() {
        return timeMissOffers;
    }

    public void setTimeMissOffers(long timeMissOffers) {
        this.timeMissOffers = timeMissOffers;
    }

    public long getTimeShowUpOffers() {
        return timeShowUpOffers;
    }

    public void setTimeShowUpOffers(long timeShowUpOffers) {
        this.timeShowUpOffers = timeShowUpOffers;
    }

    public String getNadaviIdChild() {
        return nadaviIdChild;
    }

    public void setNadaviIdChild(String nadaviIdChild) {
        this.nadaviIdChild = nadaviIdChild;
    }

    public String getMobiguruId() {
        return mobiguruId;
    }

    public void setMobiguruId(String mobiguruId) {
        this.mobiguruId = mobiguruId;
    }

    public String getNadaviIdParent() {
        return nadaviIdParent;
    }

    public void setNadaviIdParent(String nadaviIdParent) {
        this.nadaviIdParent = nadaviIdParent;
    }

    public String getSelectorGeo() {
        return selectorGeo;
    }

    public void setSelectorGeo(String selectorGeo) {
        this.selectorGeo = selectorGeo;
    }

    public boolean isSendMessageToChat() {
        return sendMessageToChat;
    }

    public void setSendMessageToChat(boolean sendMessageToChat) {
        this.sendMessageToChat = sendMessageToChat;
    }
}
