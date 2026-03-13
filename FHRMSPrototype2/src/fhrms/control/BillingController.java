package fhrms.control;
import fhrms.entity.*; import fhrms.service.PrinterService; import java.math.BigDecimal;
public class BillingController {
    public BigDecimal generateBill(Order order){ return order.total(); }
    public Payment takePayment(Order order, Payment.Method method){
        Payment payment=new Payment(order.getId(), order.total(), method);
        order.setStatus(Order.Status.PAID);
        PrinterService.printReceipt(order, payment);
        return payment;
    }
}