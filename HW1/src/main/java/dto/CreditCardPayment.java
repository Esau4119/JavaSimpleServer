package dto;

public class CreditCardPayment extends BasePaymentDto {

  private String number;
  private String securityCode;

  public CreditCardPayment(Double amount, String number, String securityCode) {
    super();
    this.amount = amount;
    this.number = number;
    this.securityCode = securityCode;
  }
}
