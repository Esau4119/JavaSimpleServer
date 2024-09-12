package dto;

import org.bson.Document;

public class CashPayment extends BasePaymentDto {

    private  static String type = "cash";

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

  @Override
  public Document toDocument() {

    var document = new Document("_id", this.getUniqueId())
            .append("amount", this.amount)
            .append("type", type);
    return document;
  }

  public static CashPayment fromDocument(Document document) {


    return new CashPayment(document.get("_id").toString(),
            Double.parseDouble(document.get("amount").toString()));

  }
  public static String getType() {

    return type;
  }
}
