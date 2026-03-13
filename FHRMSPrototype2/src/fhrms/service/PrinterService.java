package fhrms.service;
import fhrms.entity.Order; import fhrms.entity.Payment; import javax.swing.JOptionPane;
public class PrinterService {
    public static void printReceipt(Order order, Payment payment){
        JOptionPane.showMessageDialog(null,
            "Receipt\nOrder #"+order.getId()+
            "\nTotal: £"+order.total()+
            "\nPayment: "+payment.getMethod()+
            "\n(Printed - simulated)",
            "Print Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
}