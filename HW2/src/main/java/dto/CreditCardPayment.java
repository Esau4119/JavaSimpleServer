package dto;

import org.bson.Document;

public class CreditCardPayment extends BasePaymentDto {

  private String number;
  private String securityCode;
  private static String type = "credit";

  public CreditCardPayment(Double amount, String number, String securityCode) {
    super();
    this.amount = amount;
    this.number = number;
    this.securityCode = securityCode;
  }

  @Override
  public Document toDocument() {
    var document = new Document("_id", this.getUniqueId())
            .append("amount", this.amount).append("number", this.number)
            .append("securityCode", this.securityCode).append("type", type);
    return document;
  }

  public static CreditCardPayment fromDocument(Document document){

    return new CreditCardPayment(Double.parseDouble(document.get("amount").toString()),
            document.get("number").toString(),
            document.get("securityCode").toString());
  }

  public String getNumber() {
    return number;
  }

  public String getSecurityCode() {
    return securityCode;
  }

  public static String getType() {
    return type;
  }
}
