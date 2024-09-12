package dao;

import dto.BasePaymentDto;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao implements BaseDao<BasePaymentDto> {
  private static List<BasePaymentDto> paymentList = new ArrayList<>();
  private static PaymentDao instance;


  public static PaymentDao getInstance(){
    if(instance == null){
      instance = new PaymentDao();
      return instance;
    }else{
      return instance;
    }

  }

  @Override
  public void put(BasePaymentDto basePaymentDto) {
    paymentList.add(basePaymentDto);
  }

  @Override
  public BasePaymentDto get(String id) {
    if(paymentList.contains(id)){
      int index = paymentList.indexOf(id);
     return paymentList.get(index);
    }else{
      return null;
    }
  }

  @Override
  public List<BasePaymentDto> getAll(){
    return paymentList;
  }
}
