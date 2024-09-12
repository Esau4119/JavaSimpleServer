package dto;

public class CashPayment extends BasePaymentDto {

  public CashPayment() {
  }

  public CashPayment(String uniqueId, Double amount) {
    super(uniqueId);
    this.amount = amount;
  }

  public CashPayment(Double amount) {
    super();
    this.amount = amount;
  }
}
