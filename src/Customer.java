import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements User, Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private boolean isActive;
    private List<String> orderedItems;
    private static final int MAX_ORDER_LIMIT = 10;

    private static CompareBy compareByType = CompareBy.FIRST_NAME;
    private static boolean ascendingType = true;

    public Customer(String firstName, String lastName, String email, String userName, String password, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.orderedItems = new ArrayList<>();
    }

    public Customer(String firstName, String lastName, String email, String userName, String password, boolean isActive, List<String> orderedItems) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.orderedItems = orderedItems;
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
    public List<String> getOrderedItems () {
        return this.orderedItems;
    }

    @Override
    public String getRole () {
        return "Customer";
    }

    @Override
    public String getDetails () {
        return "";
    }

    @Override
    public boolean isActive () {
        return this.isActive;
    }

    @Override
    public boolean canPlace () {
        return true;
    }

    //setters
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
    public void orderItems(MenuItem item) throws CustomExceptions.ItemNotAvailableException {

    }

    @Override
    public void cancelItem(MenuItem item) {
        
    }

    @Override
    public String toDataString () {
        String dataString = "Customer;" + this.firstName + ";" + this.lastName + ";" + this.email + ";" + this.userName + ";" + this.password + ";" + this.isActive;
        for (String s : this.orderedItems) {
            dataString =  dataString + ";" + s;
        }
        return dataString;
    }
    
    @Override
    public String toString () {
        return this.lastName + ", " + this.firstName;
    }

    public String extractString() {
        StringBuilder sb = new StringBuilder();
            sb.append(firstName);
            sb.append("-");
            sb.append(lastName);
            sb.append("-");
            sb.append(email);
            sb.append("-");
            sb.append(userName);
        return sb.toString();
    }

    public String extractString(UserFields field) {
        String name = "";
        if(field.equals(UserFields.FIRST_NAME)) {
            name = firstName;
        } else if(field.equals(UserFields.LAST_NAME)) {
            name = lastName;
        } else if(field.equals(UserFields.EMAIL)) {
            name = email;
        } else if(field.equals(UserFields.USERNAME)) {
            name = userName;
        }
        return name;
    }

    public static void setCompareBy (CompareBy compareBy, boolean ascending) {
        compareByType = compareBy;
        ascendingType = ascending;
    }

    @Override
    public int compareTo (User o) {
        int result = 0;
        switch(compareByType) {
            case FIRST_NAME:
                result = this.firstName.compareTo(o.getFirstName());
                break;
            case LAST_NAME:
                result = this.lastName.compareTo(o.getLastName());
                break;
            case EMAIL:
                result = this.email.compareTo(o.getEmail());
                break;
            case USERNAME:
                result = this.userName.compareTo(o.getUserName());
                break;
        }
        if(!ascendingType) {
            result = -result;
        }

        return result;
    }
}
