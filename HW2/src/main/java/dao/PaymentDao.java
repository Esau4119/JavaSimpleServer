package dao;

import com.mongodb.client.MongoCollection;
import dto.BasePaymentDto;

import java.util.ArrayList;
import java.util.List;

import dto.CashPayment;
import dto.CreditCardPayment;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class PaymentDao implements BaseDao<BasePaymentDto> {

    private static PaymentDao instance;
    public MongoCollection<Document> collection; // TODO instead of using a list, directly use mongo to load/store

    private PaymentDao(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public static PaymentDao getInstance() {
        if (instance == null) {
            instance = new PaymentDao(MongoConnection.getCollection("Payments"));
        }
        return instance;
    }

    public static PaymentDao getInstance(MongoCollection<Document> collection) {
        instance = new PaymentDao(collection);
        return instance;
    }


    @Override
    public void put(BasePaymentDto basePaymentDto) {
        collection.insertOne(basePaymentDto.toDocument());
    }

    public BasePaymentDto createCreditPayment(Document document) {
        return CreditCardPayment.fromDocument(document);
    }

    public BasePaymentDto createCashPayment(Document document) {
        return CashPayment.fromDocument(document);
    }


    @Override
    public BasePaymentDto get(String id) {
        var document = collection.find(eq("_id", id)).first();
        var type = document.get("type").toString();
        if (type == CreditCardPayment.getType()) {
            return createCreditPayment(document);
        } else if (type == CashPayment.getType()) {
            return createCashPayment(document);
        } else {
            return null;
        }
    }

    @Override
    public List<BasePaymentDto> getAll() {

        List<Document> documentList = collection.find().into(new ArrayList<>());
        List<BasePaymentDto> allPayments = new ArrayList();
        for (Document document : documentList) {

            var type = document.get("type").toString();
            if (type == CreditCardPayment.getType()) {
                allPayments.add(createCreditPayment(document));
            } else if (type == CashPayment.getType()) {
                allPayments.add(createCashPayment(document));
            }
        }
        return allPayments;
    }
}
