package Assert.Rusupermarket;


import static Assert.Rusupermarket.Methods.average;

public class Url {
    String url;
    boolean status = true;
    long timeUp;
    long timeDown;
    long timeMissOffers;
    long timeShowUpOffers;
    int dateLastReport = 0;
    int timeMessage;
    boolean messageIsSent = false;
    String selector;
    double averageOffers = 0;
    double averagePage = 0;
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

    public String getSelector() {
        return this.selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public void toAverage(double lastOffers, double lastPage) {
        this.averageOffers = average (this.averageOffers, this.amountOfNumbers, lastOffers);
        this.averagePage = average(this.averagePage, this.amountOfNumbers, lastPage);
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
}
