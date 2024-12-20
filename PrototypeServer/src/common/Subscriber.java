package common;

public class Subscriber {
    private int subscriberId;
    private String subscriberName;
    private int detailedSubscriptionHistory;
    private String subscriberPhoneNumber;
    private String subscriberEmail;

    // Constructor
    public Subscriber(int subscriberId, String subscriberName, int detailedSubscriptionHistory, String subscriberPhoneNumber, String subscriberEmail) {
        this.subscriberId = subscriberId;
        this.subscriberName = subscriberName;
        this.detailedSubscriptionHistory = detailedSubscriptionHistory;
        this.subscriberPhoneNumber = subscriberPhoneNumber;
        this.subscriberEmail = subscriberEmail;
    }

    // Getter and setter for subscriberId
    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    // Getter and setter for subscriberName
    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    // Getter and setter for detailedSubscriptionHistory
    public int getDetailedSubscriptionHistory() {
        return detailedSubscriptionHistory;
    }

    public void setDetailedSubscriptionHistory(int detailedSubscriptionHistory) {
        this.detailedSubscriptionHistory = detailedSubscriptionHistory;
    }

    // Getter and setter for subscriberPhoneNumber
    public String getSubscriberPhoneNumber() {
        return subscriberPhoneNumber;
    }

    public void setSubscriberPhoneNumber(String subscriberPhoneNumber) {
        this.subscriberPhoneNumber = subscriberPhoneNumber;
    }

    // Getter and setter for subscriberEmail
    public String getSubscriberEmail() {
        return subscriberEmail;
    }

    public void setSubscriberEmail(String subscriberEmail) {
        this.subscriberEmail = subscriberEmail;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "subscriberId=" + subscriberId +
                ", subscriberName='" + subscriberName + '\'' +
                ", detailedSubscriptionHistory=" + detailedSubscriptionHistory +
                ", subscriberPhoneNumber='" + subscriberPhoneNumber + '\'' +
                ", subscriberEmail='" + subscriberEmail + '\'' +
                '}';
    }
}
