package fhrms.entity;
public class Table {
    public enum TableStatus { FREE, OCCUPIED, RESERVED }
    private final int number; private final int seats; private TableStatus status;
    public Table(int number, int seats, TableStatus status){ this.number=number; this.seats=seats; this.status=status; }
    public int getNumber(){return number;} public int getSeats(){return seats;} public TableStatus getStatus(){return status;} public void setStatus(TableStatus s){this.status=s;}
    public String toString(){ return "Table " + number + " (" + status + ")"; }
}