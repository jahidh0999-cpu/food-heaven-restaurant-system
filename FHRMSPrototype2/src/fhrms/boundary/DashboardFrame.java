package fhrms.boundary;
import fhrms.control.BillingController; import fhrms.control.OrderController;
import fhrms.entity.MenuItem; import fhrms.entity.Payment; import fhrms.entity.Table;
import javax.swing.*; import java.awt.*; import java.math.BigDecimal; import java.text.NumberFormat; import java.util.Locale;
public class DashboardFrame extends JFrame {
    private final DefaultListModel<String> orderListModel=new DefaultListModel<>();
    private final JLabel subtotalLabel=new JLabel("Subtotal: £0.00");
    private final OrderController orderController=new OrderController();
    private final BillingController billingController=new BillingController();
    private final MenuItem burger=new MenuItem(1,"Classic Burger",new BigDecimal("8.50"),"Main",15);
    private final MenuItem pasta=new MenuItem(2,"Chicken Pasta",new BigDecimal("7.00"),"Main",12);
    private final MenuItem coke=new MenuItem(3,"Coca-Cola",new BigDecimal("2.00"),"Drink",30);
    public DashboardFrame(){
        setTitle("Food Heaven RMS - Dashboard"); setSize(800,500); setDefaultCloseOperation(EXIT_ON_CLOSE); setLocationRelativeTo(null);
        orderController.startNewOrder(new Table(1,4, Table.TableStatus.OCCUPIED));
        JButton addBurger=new JButton("Add Burger (£8.50)");
        JButton addPasta=new JButton("Add Pasta (£7.00)");
        JButton addCoke=new JButton("Add Coke (£2.00)");
        addBurger.addActionListener(e->addItem(burger,1));
        addPasta.addActionListener(e->addItem(pasta,1));
        addCoke.addActionListener(e->addItem(coke,1));
        JPanel menuPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(addBurger); menuPanel.add(addPasta); menuPanel.add(addCoke);
        JList<String> orderList=new JList<>(orderListModel); JScrollPane listScroll=new JScrollPane(orderList);
        JButton confirmOrderBtn=new JButton("Confirm Order"); JButton billingBtn=new JButton("Generate Bill / Pay");
        confirmOrderBtn.addActionListener(e->confirmOrder()); billingBtn.addActionListener(e->doBilling());
        JPanel bottomPanel=new JPanel(new BorderLayout());
        JPanel leftBottom=new JPanel(new FlowLayout(FlowLayout.LEFT)); leftBottom.add(subtotalLabel);
        JPanel rightBottom=new JPanel(new FlowLayout(FlowLayout.RIGHT)); rightBottom.add(confirmOrderBtn); rightBottom.add(billingBtn);
        bottomPanel.add(leftBottom,BorderLayout.WEST); bottomPanel.add(rightBottom,BorderLayout.EAST);
        setLayout(new BorderLayout()); add(menuPanel,BorderLayout.NORTH); add(listScroll,BorderLayout.CENTER); add(bottomPanel,BorderLayout.SOUTH);
    }
    private void addItem(MenuItem item,int qty){
        orderController.addItem(item,qty);
        String line=qty+" x "+item.getName()+" - "+currency(item.getPrice());
        orderListModel.addElement(line);
        subtotalLabel.setText("Subtotal: "+orderController.getSubtotalCurrency());
    }
    private String currency(BigDecimal value){ return NumberFormat.getCurrencyInstance(Locale.UK).format(value); }
    private void confirmOrder(){
        var order=orderController.confirm();
        JOptionPane.showMessageDialog(this,"Order Confirmed!\nOrder #"+order.getId()+"\nSubtotal: "+currency(orderController.getSubtotal()),
            "Order Placed",JOptionPane.INFORMATION_MESSAGE);
    }
    private void doBilling(){
        var order=orderController.getCurrentOrder();
        var total=billingController.generateBill(order);
        int choice=JOptionPane.showConfirmDialog(this,"Total: "+currency(total)+"\nProceed to payment?","Generate Bill",JOptionPane.OK_CANCEL_OPTION);
        if(choice==JOptionPane.OK_OPTION){
            var payment=billingController.takePayment(order, Payment.Method.CASH);
            JOptionPane.showMessageDialog(this,"Payment Successful!\n"+payment,"Billing",JOptionPane.INFORMATION_MESSAGE);
            orderListModel.clear(); subtotalLabel.setText("Subtotal: £0.00");
            orderController.startNewOrder(new Table(1,4, Table.TableStatus.OCCUPIED));
        }
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(()->new DashboardFrame().setVisible(true)); }
}