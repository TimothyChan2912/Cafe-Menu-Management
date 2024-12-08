import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum cafe {
	DB; // singleton design

	private List<MenuItem> dataMenu;
	private Map<String, User> dataUsers;

	private cafe() { // must be private
		dataMenu = new ArrayList<>();
		dataUsers = new HashMap<>();
	}

   	public static ArrayList<MenuItem> readMenu() {
		ArrayList<MenuItem> menuList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("src/resources/menuData.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 7) {
                    String type = parts[0];
                    String title = parts[1];
                    String itemID = parts[2];
                    String description = parts[3];
                    float price = Float.parseFloat(parts[4]);
                    int count = Integer.parseInt(parts[5]);
                    boolean available = Boolean.parseBoolean(parts[6]);

                    if (type.equals("Pancake")) {
                        menuList.add(new PancakeMenuItem(title, itemID, description, price, count, available));
                    } else if (type.equals("Diner")) {
                        menuList.add(new DinerMenuItem(title, itemID, description, price, count, available));
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return menuList;
	}

	public void writeMenu() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/resources/menuData.txt"))) {
			for (MenuItem item : dataMenu) {
				bw.write(item.toDataString());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<User> readUsers() {
		ArrayList<User> userList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("src/resources/userData.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 7) {
                    String type = parts[0];
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String email = parts[3];
                    String username = parts[4];
                    String password = parts[5];
                    boolean active = Boolean.parseBoolean(parts[6]);

                    if (type.equals("Admin")) {
                        userList.add(new Admin(firstName, lastName, email, username, password, active));
                    } else if (type.equals("Customer")) {
                        userList.add(new Customer(firstName, lastName, email, username, password, active));
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }

                if (parts.length > 7) {
                    String type = parts[0];
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String email = parts[3];
                    String username = parts[4];
                    String password = parts[5];
                    boolean active = Boolean.parseBoolean(parts[6]);

                    List<String> orderedItems = new ArrayList<>();
                    for (int i = 7; i < parts.length; i++) {
                        orderedItems.add(parts[i]);
                    }

                    if (type.equals("Admin")) {
                        userList.add(new Admin(firstName, lastName, email, username, password, active, orderedItems));
                    } else if (type.equals("Customer")) {
                        userList.add(new Customer(firstName, lastName, email, username, password, active, orderedItems));
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return userList;
	}

	public void writeUsers() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/resources/userData.txt"))) {
			for (User user : dataUsers.values()) {
				bw.write(user.toDataString());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
