package Tables;

import Menu.MenuData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class represents a table in a restaurant with a number, tableSeats filled, and dishes
 */
public class Table {
    private final int tableNumber;
    private HashMap<Integer, String> tableSeats = new HashMap<>();
    MenuData menuData = new MenuData();

    boolean filled;

    /**
     * Constructs a new Tables.Table object with the given table number and initializes the dishes HashMap with empty strings.
     *
     * @param tableNumber the table number
     */
    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     * Returns the table number.
     *
     * @return the table number
     */
    public int getNumber() {
        return tableNumber;
    }

    /**
     * Sets the table's filled status to the given boolean value.
     *
     * @param filled the filled status of the table
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Returns whether the table is currently filled.
     *
     * @return true if the table is filled, false otherwise
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Adds a dish to the table at the given seat.
     *
     * @param seat the seat number
     * @param dishNumber the name of the dish
     */
    public void addDish(int seat, int dishNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(tableSeats.get(seat)).append(", ").append(dishNumber).append(", ").append(menuData.getName(dishNumber));
        tableSeats.remove(seat);
        tableSeats.put(seat, sb.toString());
    }

    /**
     * Returns a string representation of the dishes at the table.
     *
     * @return a string representation of the dishes at the table
     */
    public String getTableDishes() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : tableSeats.entrySet()) {
            int seat = entry.getKey();
            String dish = entry.getValue();
            sb.append("Seat ").append(seat).append(": ").append(dish).append("\n");
        }
        return sb.toString();
    }

    public String getSeatDishes(int seat){
        StringBuilder sb = new StringBuilder();
        sb.append("Seat ").append(seat).append(": ").append("Dishes: ").append(tableSeats.get(seat)).append("\n");
        return sb.toString();
    }

    public int getSeatsFilled(){
        return tableSeats.size();
    }


    /**
     * Returns the total bill due for the table.
     *
     * @return the total bill for the table
     */
    public double getTotal(){
        MenuData menuData = new MenuData();
        double price = 0;
        for (Map.Entry<Integer, String> entry : tableSeats.entrySet()){
            int seat = entry.getKey();
            String dishes = entry.getValue();
            String[] myArray = dishes.split(",");  // Split the string by commas
            List<String> numbersList = new ArrayList<>();

            // Loop through each item in the array and add numbers to the list
            for (String s : myArray) {
                String trimmed = s.trim();  // Remove leading/trailing whitespace
                if (trimmed.matches("\\d+")) {  // Check if the string contains only digits
                    price = price + menuData.getPrice(Integer.parseInt(s));
                }
            }
        }return price;
    }

}
