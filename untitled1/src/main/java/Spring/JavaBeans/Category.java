package Spring.JavaBeans;

public enum Category {
     Food(1),
     Electricity(2),
     Restaurant(3),
     Vacation(4);

     private final int categoryNumber;

     Category(int vlaue) {
          this.categoryNumber = vlaue;
     }

     public int getCategoryNumber() {
          return categoryNumber;
     }
     public static Category getCategoryByNumber(int number) {
          for (Category category : Category.values()) {
               if (category.getCategoryNumber() == number) {
                    return category;
               }
          }
          return null; // Return null if no category with the given number is found
     }


}
