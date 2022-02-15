package lordgarrish.business;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CreditCard implements Serializable {

    private String creditCardNumber;
    private String creditCardExpirationDate;
    private String cvv;

    public CreditCard() {}

    public CreditCard(String creditCardNumber, String creditCardExpirationDate, String cvv) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpirationDate = creditCardExpirationDate;
        this.cvv = cvv;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardExpirationDate() {
        return creditCardExpirationDate;
    }

    public void setCreditCardExpirationDate(String date) {
        this.creditCardExpirationDate = date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", creditCardExpirationDate='" + creditCardExpirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
