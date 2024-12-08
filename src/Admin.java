import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Admin implements User, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private boolean isActive;
    private List<String> orderedItems;
    private static final int MAX_ORDER_LIMIT = 10;

    public Admin(String firstName, String lastName, String email, String userName, String password, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.orderedItems = new ArrayList<>();
    }

    //getters
    @Override
    public String getFirstName () {
        return this.firstName;
    }

    @Override
    public String getLastName () {
        return this.lastName;
    }

    @Override
    public String getEmail () {
        return this.email;
    }

    @Override
    public String getUserName () {
        return this.userName;
    }

    @Override
    public String getPassword () {
        return this.password;
    }
    
    @Override
    public boolean isActive () {
        return this.isActive;
    }

    @Override
    public List<String> getOrderedItems () {
        return this.orderedItems;
    }

    @Override
    public String getRole () {
        return "Admin";
    }

    @Override
    public void orderItems (MenuItem item) {
        this.orderedItems.add(item.getTitle());
    }

    @Override
    public void setActive (boolean active) {
        this.isActive = active;
    }

    @Override
    public void setOrderedItems (List<String> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @Override
    public void setUserName (String userName) {
        this.userName = userName;
    }

    @Override
    public void cancelItem (MenuItem item) {
        this.orderedItems.remove(item.getTitle());
    }

    @Override
    public boolean canPlace () {
        return this.orderedItems.size() < MAX_ORDER_LIMIT;
    }

    @Override
    public String getDetails () {
        return "Name: " + this.firstName + " " + this.lastName + "\nEmail: " + this.email + "\nUsername: " + this.userName;
    }

    @Override
    public int compareTo (User o) {
        return this.compareTo(o);
    }

    @Override
    public String toDataString () {
        String dataString = "Customer;" + this.firstName + ";" + this.lastName + ";" + this.email + ";" + this.userName + ";" + this.password + ";" + this.isActive + ";";
        for (String s : this.orderedItems) {
            dataString = dataString + s + ";";
        }
        return dataString;
    }
    
}

