package common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Subscriber {
	private int subscriberId;
    private String subscriberName;
    private int detailedSubscriptionHistory;
    private String subscriberPhoneNumber;
    private String subscriberEmail;

    public Subscriber(Subscriber subscriber) {
    	if(subscriber != null) {
        	this.subscriberId = subscriber.subscriberId;
        	this.subscriberName = subscriber.subscriberName;
        	this.detailedSubscriptionHistory = subscriber.detailedSubscriptionHistory;
        	this.subscriberPhoneNumber = subscriber.subscriberPhoneNumber;
        	this.subscriberEmail = subscriber.subscriberEmail;
    	}

    }
    
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
        return new String("["+subscriberId+","
        					+subscriberName+","
        					+detailedSubscriptionHistory+","
        					+subscriberPhoneNumber+","
        					+subscriberEmail+"]");
    }
    
    public static Subscriber subscriberFromString(String str) {
    	str = str.substring(1, str.length()-1); //remove toString []
    	StringTokenizer tokenizer = new StringTokenizer(str, ",");
    	try{
    		int subscriberId = Integer.parseInt(tokenizer.nextToken().trim());
    		String subscriberName = tokenizer.nextToken().trim();
            int detailedSubscriptionHistory = Integer.parseInt(tokenizer.nextToken().trim());
            String subscriberPhoneNumber = tokenizer.nextToken().trim();
            String subscriberEmail = tokenizer.nextToken().trim();
            Subscriber subscriber = new Subscriber(subscriberId, subscriberName, detailedSubscriptionHistory, subscriberPhoneNumber, subscriberEmail);
            return subscriber;
    	}
    	catch(NoSuchElementException e) {
    		return null;
    	}
    }
    public static String subscriberListToString(List<Subscriber> subscriberList) {
    	String output = "{";
    	Iterator<Subscriber> iterator = subscriberList.iterator();
    	while(iterator.hasNext()) {
    		output += iterator.next();
    		if(iterator.hasNext()) {
    			output += ";";
    		}
    	}
    	output += "}";
    	return output;
    }
    
    public static List<Subscriber> subscriberListFromString(String str){
		Subscriber subscriber;
    	List<Subscriber> subscriberList = new ArrayList<Subscriber>();
    	str = str.substring(1, str.length()-1); //remove {}
    	StringTokenizer tokenizer = new StringTokenizer(str, ";");
    	if(!tokenizer.hasMoreTokens()) {
    		subscriber = subscriberFromString(str);
    		if(subscriber == null) {
    			return null;
    		}
    		subscriberList.add(subscriber);
    	}
    	else {
        	while(tokenizer.hasMoreTokens()) {
        		subscriberList.add(subscriberFromString(tokenizer.nextToken().trim()));
        	}
    	}
    	return subscriberList;
    }
}
