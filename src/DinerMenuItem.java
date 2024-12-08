import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DinerMenuItem implements MenuItem, Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    private String title;
    private String itemID;
    private String description;
    private float price;
    private int count;
    private boolean available;
    private boolean current;

    private static CompareBy compareByType = CompareBy.TITLE;
    private static boolean ascendingType = true;
    
    private ArrayList<MenuItem> menuItems = new ArrayList<>(); // Store menu items

    public DinerMenuItem(String title, String itemID, String description, float price, int count, boolean current) {
        this.title = title;
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.count = count;
        this.current = current;
        this.available = count > 0 && current;
    }

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

     @Override
    public String toString () {
        return this.title + ", $" + this.price;
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

    public static void setCompareBy (CompareBy compareBy, boolean ascending) {
        compareByType = compareBy;
        ascendingType = ascending;
    }

    public String extractString() {
        StringBuilder sb = new StringBuilder();
            sb.append(title);
            sb.append("-");
            sb.append(itemID);
            sb.append("-");
            sb.append(description);
            sb.append("-");
            sb.append(String.format("%.2f", price));
        return sb.toString();
    }

    public String extractString(MenuItemFields field) {
        String name = "";
        if(field.equals(MenuItemFields.TITLE)) {
            name = title;
        } else if(field.equals(MenuItemFields.ITEM_ID)) {
            name = itemID;
        } else if(field.equals(MenuItemFields.DESCRIPTION)) {
            name = description;
        } else if(field.equals(MenuItemFields.PRICE)) {
            name = String.format("%.2f", price);
        }
        return name;
    }

    @Override
    public int compareTo (MenuItem o) {
        int result = 0;
        switch(compareByType) {
            case TITLE:
                result = this.title.compareTo(o.getTitle());
                break;
            case ITEM_ID:
                result = this.itemID.compareTo(o.getItemID());
                break;
            case DESCRIPTION:
                result = this.description.compareTo(o.getDescription());
                break;
            case PRICE:
                if(price > o.getPrice()) {
                    result = 1;
                } else if(price < o.getPrice()) {
                    result = -1;
                }
                break;
        }
        if(!ascendingType) {
            result = -result;
        }

        return result;
    }
}

