package com.hesh.inventryservice.controller;

import com.hesh.inventryservice.common.OrderInfo;
import com.hesh.inventryservice.common.TxResponse;
import com.hesh.inventryservice.entity.All_Orders;
import com.hesh.inventryservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("/payment/")
public class PaymentController {
    @Autowired
    public PaymentService service;

    @GetMapping("/allOrders")
    public List<All_Orders> fetchOrderList(){
        return service.fetchOrderList();
    }

    @PostMapping("/doPay/")
    public All_Orders doPayment(@RequestBody All_Orders payment){
        return service.doPay(payment);
    }

    @PostMapping("/send/{id}")
    public TxResponse sendOrderInfo(@PathVariable("id")  int orderId, @RequestBody All_Orders payment){
        return service.sendOrderInfo(orderId, payment);
    }

    @PutMapping("/allOrders/{id}")
    public All_Orders updateDepartment(@PathVariable("id")  int orderId, @RequestBody All_Orders order){
        return service.updateOrder(orderId, order);
    }

}
