import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class DinerMenuItem implements MenuItem, Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    private String title;
    private String itemID;
    private String description;
    private float price;
    private int count;
    private boolean available;
    private boolean current;
    
    private ArrayList<MenuItem> menuItems = new ArrayList<>(); // Store menu items

    //getters
    @Override
    public String getTitle() {
        return this.title;
    }
    @Override
    public String getItemID() {
        return this.itemID;
    }
    @Override
    public String getDescription() {
        return this.description;
    }
    @Override
    public float getPrice() {
        return this.price;
    }
    @Override
    public int getCount() {
        return this.count;
    }
    @Override
    public String getMenuType() {
        return "Diner";
    }
    @Override
    public boolean isAvailable() {
        return available;
    }
    @Override
    public boolean isCurrent() {
        return current;
    }

    //setters
    @Override
    public void setCount(int count) {
        this.count = count;
    }
    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }
    @Override
    public void setCurrent(boolean current) {
        this.current = current;
    }
    
    //other methods
    @Override
    public String toDataString() {
        String dataString = "Diner;" + this.title + ";" + this.itemID + ";" + this.description + ";" + this.price + ";" + this.count + ";" + this.available;
        return dataString;
    }
    @Override
    public void addItem(MenuItem item) {
        menuItems.add(item);
    }
    @Override
    public void removeItem(String itemID) {
        menuItems.remove(itemID);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItems.iterator();
    }

    public int compareTo (MenuItem m) {
        return this.compareTo(m);
    }
}

