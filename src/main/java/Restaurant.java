import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        // Part 2 : Solution
        this.menu.add(new Item("Lemon Pasta", 120));
        this.menu.add(new Item("Classic Lasagna", 135));
    }

    // Part 2 : Solution
    public boolean isRestaurantOpen() {
        LocalTime time = LocalTime.now();
        int isOpen = time.compareTo(openingTime);
        int isClose = time.compareTo(closingTime);
        if(isClose < 0 && isOpen >= 0) {
            return true;
        }
        return false;
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    // Part 2: Solution
    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    // Part 2: Solution
    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public List<Item> getMenu() {
        return this.menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    /**
     * @method getOrderValue
     * @param itemList
     * @return int
     * @description get orderValue from itemsList
     * Part 3: Solution
     */
    public int getOrderValue(List<Item> itemList) {
        int totalValue = 0;
        for(Item thisItem : itemList) {
            totalValue += thisItem.getPrice();
        }
        return totalValue;
    }
}
