package com.hesh.inventryservice.common;

public class OrderInfo {
    private int orderId;
    private String txId;
    private boolean allocated;
    private boolean deliverySheduled;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }

    public boolean isDeliverySheduled() {
        return deliverySheduled;
    }

    public void setDeliverySheduled(boolean deliverySheduled) {
        this.deliverySheduled = deliverySheduled;
    }
}
