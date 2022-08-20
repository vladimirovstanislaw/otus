package ru.otus.homework;

import com.sun.jdi.request.InvalidRequestStateException;

public class Business {
    //смысл в том, что чтобы делать бизнес - нужно быть готовым делать бизнес.
    private boolean readyForBusiness;


    public Business() {
        this.readyForBusiness = true;
    }

    public boolean isReadyForBusiness() {
        return readyForBusiness;
    }

    public void setReadyForBusiness(boolean readyForBusiness) {
        this.readyForBusiness = readyForBusiness;
    }

    public void makeMoney() throws InterruptedException {
        if (readyForBusiness == true) {
            //making money
            System.out.println("Starting making money");
            Thread.sleep(1000);
            System.out.println("Money talk");
            this.readyForBusiness = false;
        } else {
            throw new IllegalStateException("We can't make money while we're tired");
        }
    }

    public void relieveFatigue() {
        this.readyForBusiness = true;
    }


    public static void main(String[] args) throws InterruptedException {
        Business business = new Business();
        System.out.println("business.isReadyForBusiness()");
        System.out.println(business.isReadyForBusiness());
        business.makeMoney();

        System.out.println("business.isReadyForBusiness()");
        System.out.println(business.isReadyForBusiness());
    }
}
