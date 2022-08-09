package com.hesh.inventryservice.service;

import com.hesh.inventryservice.common.OrderInfo;
import com.hesh.inventryservice.common.TxResponse;
import com.hesh.inventryservice.entity.All_Orders;
import com.hesh.inventryservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private RestTemplate template;

    public All_Orders doPay(All_Orders order){
        order.setPaymentStatus(paymentStatus());
        order.setTxId(UUID.randomUUID().toString());
        order.setAllocated(false);
        order.setDeliverySheduled(false);
        return repository.save(order);
    }

    private String paymentStatus(){
        //mocking a 3rd party payment call like Gpay,Paytm,etc.
        return new Random().nextBoolean()?"success":"failure";
    }

    public TxResponse sendOrderInfo(int orderId, All_Orders order) {
        OrderInfo orderInfo = new OrderInfo();

        All_Orders depFromDB = repository.findById(orderId).get();

        orderInfo.setOrderId(orderId);
        orderInfo.setTxId(depFromDB.getTxId());
        orderInfo.setAllocated(depFromDB.isAllocated());
        orderInfo.setDeliverySheduled(depFromDB.isDeliverySheduled());


        OrderInfo info =
                template.postForObject("http://localhost:61111/order/save/",
                        orderInfo, OrderInfo.class);

        TxResponse txResponse = new TxResponse();
        txResponse.setOrder(orderInfo);
        return txResponse;

    }

    public  List<All_Orders> fetchOrderList() {
        return repository.findAll();
    }

    public All_Orders updateOrder(int orderId, All_Orders order) {
        All_Orders depFromDB = repository.findById(orderId).get();

        if(Objects.nonNull(order.isAllocated()) ){
            depFromDB.setAllocated(order.isAllocated());
        }

        if(Objects.nonNull(order.isDeliverySheduled()) ){
            depFromDB.setDeliverySheduled(order.isDeliverySheduled());
        }


        return repository.save(depFromDB);
    }
}
